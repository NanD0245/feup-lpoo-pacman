package g50.controller;

import g50.controller.ghost_strategy.*;
import g50.controller.states.GameState;
import g50.controller.states.GhostState;
import g50.gui.GUI;
import g50.model.Game;
import g50.model.element.movable.ghost.Ghost;
import g50.gui.GUIObserver;
import g50.model.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.Collectable;
import g50.model.element.fixed.collectable.CollectableTriggers;
import g50.model.element.fixed.nonCollectable.EmptySpace;
import g50.model.element.movable.ghost.*;
import g50.model.map.GameMap;
import g50.view.GameMapViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class GameController implements Controller {
    private final Game game;
    private final List<GhostController> ghostsController;
    private final PacManController pacManController;
    private final GameMapViewer viewer;
    private final GUI gui;
    private final GameStateController gameState;
    private final List<Class<? extends GhostStrategy>> priorities;
    private int bonus;
    private int frameRate;

    public GameController(GUI gui, Game game, int frameRate) {
        this.game = game;
        this.ghostsController = new ArrayList<>();
        this.frameRate = frameRate;
        this.pacManController = new PacManController(game.getMap(), this);
        this.viewer = new GameMapViewer();
        this.gui = gui;
        this.gameState = new GameStateController();
        this.gameState.addObserver(this);
        setUpGhosts();

        this.priorities = Arrays.asList(
                BlinkyStrategy.class,
                PinkyStrategy.class,
                InkyStrategy.class,
                ClydeStrategy.class);

        this.bonus = 200;
    }

    public void setUpGhosts(){

        BlinkyGhost currentBlinky = null;

        for(Ghost ghost: game.getMap().getGhosts())
            if(ghost instanceof BlinkyGhost) currentBlinky = (BlinkyGhost) ghost;

        for(Ghost ghost: game.getMap().getGhosts()) {
            GhostController newGhostController;
            if(ghost instanceof InkyGhost)
                newGhostController = new GhostController(this.game.getMap(), ghost, GhostState.INCAGE, new InkyStrategy(this.game.getMap(), ghost, currentBlinky));
            else if(ghost instanceof ClydeGhost)
                newGhostController = new GhostController(this.game.getMap(), ghost, GhostState.INCAGE, new ClydeStrategy(this.game.getMap(), ghost));
            else if(ghost instanceof PinkyGhost)
                newGhostController = new GhostController(this.game.getMap(), ghost, GhostState.INCAGE, new PinkyStrategy(this.game.getMap(), ghost));
            else {
                newGhostController = new GhostController(this.game.getMap(), ghost, GhostState.CHASE, new BlinkyStrategy(this.game.getMap(), ghost));
                currentBlinky = (BlinkyGhost) ghost;
            }

            this.ghostsController.add(newGhostController);
            this.gameState.addObserver(newGhostController);
        }
    }


    public void addPendingAction(GUI.ACTION action) {
        pacManController.addPendingAction(action);
    }

    @Override
    public void update(int frame) {
        gameState.update(frame, frameRate);
        controlGhosts(frame);
        pacManController.update(frame);
        checkPacmanGhostCollision();

        try {
            viewer.draw(gui, this.game.getMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void controlGhosts(int frame) {
        for(GhostController ghostController: ghostsController){
            ghostController.update(frame);
        }
    }

    public void consumeMapElement(Position pos){
        FixedElement currentElement = game.getMap().getElement(pos);

        if(currentElement.isCollectable()){
            Position newPos = new Position(game.getMap().getPacman().getPosition());
            game.getMap().setElement(new EmptySpace(newPos), newPos);

            Collectable collectable = (Collectable) currentElement;
            CollectableTriggers action = collectable.triggersEffect();

            switch (action) {
                case COLLECT:
                    decreaseDotsOnHighestPriorityGhost();
                    break;
                case FRIGHTEN:
                    gameState.setCurrentState(GameState.GameFrightned);
                    break;
                default: break;
            }

            // points += currentElement.collect();
        }
    }

    private void decreaseDotsOnHighestPriorityGhost(){
        for(Class classType: this.priorities){
            for(GhostController ghostController: this.ghostsController){
                if(classType.equals(ghostController.getStrategy().getClass())
                        && ghostController.getState().equals(GhostState.INCAGE)){
                    ghostController.decrementStrategyDotLimit();
                    return;
                }
            }
        }
    }

    private void checkPacmanGhostCollision() {
        for(GhostController ghostController: ghostsController){
            if(ghostController.getControllablePosition().equals(pacManController.getControllablePosition())){
                if(ghostController.getState().equals(GhostState.FRIGHTENED)){
                    ghostController.consumeGhost();
                    // points += this.bonus;
                    // this.bonus *= 2;
                }
                else if(!ghostController.getState().equals(GhostState.DEAD)){
                    // dead pacman
                }
            }
        }
    }

    @Override
    public void notify(GameState state) {
        this.bonus = 200;
    }
}

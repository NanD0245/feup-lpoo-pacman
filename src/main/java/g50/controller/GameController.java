package g50.controller;

import g50.controller.ghost_strategy.*;
import g50.controller.states.GameState;
import g50.controller.states.GhostState;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.Collectable;
import g50.model.element.fixed.collectable.CollectableTriggers;
import g50.model.element.fixed.nonCollectable.EmptySpace;
import g50.model.element.movable.ghost.*;
import g50.model.map.GameMap;
import g50.view.GameMapViewer;
import g50.view.GameViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class GameController implements Controller, GUIObserver{
    private final GameMap map;
    private final List<GhostController> ghostsController;
    private final PacManController pacManController;
    private final GameViewer viewer;
    private int framerate = 60;
    private TimerTask updater;
    private Timer timer;

    private int score;
    private final GUI gui;

    private final GameStateController gameState;
    private final List<Class<? extends GhostStrategy>> priorities;
    private int bonus;

    public GameController(GUI gui, GameMap map, int score) {
        this.map = map;
        this.gui = gui;
        this.viewer = new GameViewer();
        this.ghostsController = new ArrayList<>();
        this.gameState = new GameStateController();
        this.gameState.addObserver(this);
        setUpGhosts();

        this.pacManController = new PacManController(map, this);
        this.priorities = Arrays.asList(
                BlinkyStrategy.class,
                PinkyStrategy.class,
                InkyStrategy.class,
                ClydeStrategy.class);

        this.bonus = 200;
    }

    public void setUpGhosts(){

        BlinkyGhost currentBlinky = null;

        for(Ghost ghost: map.getGhosts())
            if(ghost instanceof BlinkyGhost) currentBlinky = (BlinkyGhost) ghost;

        for(Ghost ghost: map.getGhosts()) {
            GhostController newGhostController;
            if(ghost instanceof InkyGhost)
                newGhostController = new GhostController(this.map, ghost, GhostState.INCAGE, new InkyStrategy(this.map, ghost, currentBlinky));
            else if(ghost instanceof ClydeGhost)
                newGhostController = new GhostController(this.map, ghost, GhostState.INCAGE, new ClydeStrategy(this.map, ghost));
            else if(ghost instanceof PinkyGhost)
                newGhostController = new GhostController(this.map, ghost, GhostState.INCAGE, new PinkyStrategy(this.map, ghost));
            else {
                newGhostController = new GhostController(this.map, ghost, GhostState.CHASE, new BlinkyStrategy(this.map, ghost));
                currentBlinky = (BlinkyGhost) ghost;
            }

            this.ghostsController.add(newGhostController);
            this.gameState.addObserver(newGhostController);
        }
    }

    public void setUp(){
        setUp(framerate);
    }

    public void setUp(int framerate){
        this.framerate = framerate;
        updater = new TimerTask() {
            int frame = -1;
            @Override
            public void run() {
                frame++;
                update(frame);
            }
        };
        timer = new Timer();
        timer.schedule(updater, 1000/framerate, 1000/framerate);
    }

    public void terminate() {
        timer.cancel();
    }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        if(action.equals(GUI.KBD_ACTION.QUIT)) terminate();
        pacManController.addPendingKBDAction(action);
    }


    @Override
    public void update(int frame) {
        gameState.update(frame, framerate);
        controlGhosts(frame);
        pacManController.update(frame);
        checkPacmanGhostCollision();
        try {
            viewer.draw(gui, map);
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
        FixedElement currentElement = map.getElement(pos);

        if(currentElement.isCollectable()){
            Position newPos = new Position(map.getPacman().getPosition());
            map.setElement(new EmptySpace(newPos), newPos);

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

            score += ((Collectable) currentElement).collect();
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

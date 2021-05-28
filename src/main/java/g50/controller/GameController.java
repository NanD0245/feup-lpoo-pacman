package g50.controller;

import g50.controller.ghost.*;
import g50.states.GameState;
import g50.states.GameStateHandler;
import g50.states.GhostState;
import g50.Application;
import g50.gui.GUI;
import g50.model.Game;
import g50.model.element.fixed.collectable.PacDot;
import g50.model.element.movable.ghost.Ghost;
import g50.model.element.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.Collectable;
import g50.model.element.fixed.collectable.CollectableTriggers;
import g50.model.element.fixed.nonCollectable.EmptySpace;
import g50.model.element.movable.ghost.*;
import g50.model.element.movable.ghost.strategy.*;
import g50.view.menu.GameViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class GameController extends Controller<Game> {
    private final List<GhostController> ghostsController;
    private final PacManController pacManController;
    private final GameViewer viewer;
    private final GUI gui;
    private final GameStateHandler gameStateHandler;
    private int bonusPoints;

    private static final List<Class<? extends GhostStrategy>> priorities = Arrays.asList(
            BlinkyStrategy.class,
            PinkyStrategy.class,
            InkyStrategy.class,
            ClydeStrategy.class);

    public GameController(GUI gui, Game game) {
        super(game);
        this.ghostsController = new ArrayList<>();
        this.pacManController = new PacManController(this);
        this.viewer = new GameViewer(game, this);
        this.gui = gui;
        this.gameStateHandler = new GameStateHandler(this);
        setUpGhosts();
        this.bonusPoints = 200;
    }

    public void setUpGhosts(){
        for(Ghost ghost: super.getModel().getGameMap().getGhosts()) {
            GhostController newGhostController;
            if(ghost instanceof InkyGhost)
                newGhostController = new GhostController(ghost);
            else if(ghost instanceof ClydeGhost)
                newGhostController = new GhostController(ghost);
            else if(ghost instanceof PinkyGhost)
                newGhostController = new GhostController(ghost);
            else {
                newGhostController = new GhostController(ghost);
            }
            this.ghostsController.add(newGhostController);
        }
    }


    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        pacManController.addPendingKBDAction(action);
    }

    @Override
    public void update(Application application, int frame) {
        gameStateHandler.update(frame, application.getFrameRate());
        controlGhosts(application, frame);
        pacManController.update(application, frame);
        try {
            checkPacmanGhostCollision();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            viewer.draw(gui);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void controlGhosts(Application application, int frame) {
        for(GhostController ghostController: ghostsController){
            ghostController.update(application, frame);
        }
    }

    public void consumeMapElement(Position pos){
        FixedElement currentElement = super.getModel().getGameMap().getElement(pos);

        if(currentElement.isCollectable()){
            Position newPos = new Position(super.getModel().getGameMap().getPacman().getPosition());
            super.getModel().getGameMap().setElement(new EmptySpace(newPos), newPos);

            Collectable collectable = (Collectable) currentElement;
            CollectableTriggers action = collectable.triggersEffect();

            switch (action) {
                case COLLECT:
                    decreaseDotsOnHighestPriorityGhost();
                    break;
                case FRIGHTEN:
                    gameStateHandler.setCurrentState(GameState.GAME_FRIGHTENED);
                    break;
                default: break;
            }
            this.getModel().incrementScore(((Collectable) currentElement).collect());
        }
    }

    private void decreaseDotsOnHighestPriorityGhost(){
        for(Class<? extends GhostStrategy> classType: priorities){
            for(GhostController ghostController: ghostsController){
                if(classType.equals(ghostController.getModel().getStrategy().getClass())
                        && ghostController.getModel().getState().equals(GhostState.IN_CAGE)){
                    ghostController.decrementStrategyDotLimit();
                    return;
                }
            }
        }
    }

    private void checkPacmanGhostCollision() throws InterruptedException {
        for(GhostController ghostController: ghostsController){
            if(ghostController.getModel().getPosition().equals(pacManController.getModel().getPosition())){
                if(ghostController.getModel().getState().equals(GhostState.FRIGHTENED)){
                    ghostController.consumeGhost();
                    this.getModel().incrementScore(this.bonusPoints);
                    this.bonusPoints *= 2;
                }
                else if(!ghostController.getModel().getState().equals(GhostState.DEAD)){
                    getModel().getGameMap().getPacman().decreaseLives();
                    if (getModel().getGameMap().getPacman().isAlive()) {
                        Thread.sleep(1000);
                        getModel().resetPositions();
                    }
                    else {
                        Thread.sleep(1000);
                    }
                }
            }
        }
    }

    public boolean isGameOver() {
        if (!getModel().getGameMap().getPacman().isAlive()) return true;
        for (List<FixedElement> line : getModel().getGameMap().getMap()){
            for (FixedElement element : line) if (element instanceof PacDot) return false;
        }
        return true;
    }

    public List<GhostController> getGhostsController() { return ghostsController; }

    public PacManController getPacManController() { return pacManController; }

    public GameStateHandler getGameStateHandler() {
        return gameStateHandler;
    }
}

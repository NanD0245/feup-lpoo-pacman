package g50.controller;

import g50.controller.ghost_strategy.*;
import g50.controller.states.GameState;
import g50.controller.states.GhostState;
import g50.Application;
import g50.controller.states.app_states.AppState;
import g50.gui.GUI;
import g50.model.Game;
import g50.model.LevelInfo;
import g50.model.element.fixed.collectable.PacDot;
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
import g50.view.GameViewer;
import g50.view.Viewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;


public class GameController extends Controller<Game> {
    private final List<GhostController> ghostsController;
    private final PacManController pacManController;
    private final GameViewer viewer;
    private final GUI gui;
    private final GameStateHandler gameState;
    private final List<Class<? extends GhostStrategy>> priorities;
    private int currBonus;
    private static int bonus = 200;
    private int frameRate;
    private boolean pause;
    private boolean started;


    public GameController(GUI gui, Game game, int frameRate) {
        super(game);
        this.ghostsController = new ArrayList<>();
        this.pacManController = new PacManController(this);
        this.viewer = new GameViewer(game, this);
        this.gui = gui;
        this.gameState = new GameStateHandler(this.getModel().getLevelInfo().getGameStateIntervals());
        this.gameState.addObserver(this);
        setUpGhosts();
        this.started = true;

        this.priorities = Arrays.asList(
                BlinkyStrategy.class,
                PinkyStrategy.class,
                InkyStrategy.class,
                ClydeStrategy.class);

        this.bonus = 200;
        this.frameRate = frameRate;
    }

    public void setUpGhosts(){

        BlinkyGhost currentBlinky = null;

        for(Ghost ghost: super.getModel().getGameMap().getGhosts())
            if(ghost instanceof BlinkyGhost) currentBlinky = (BlinkyGhost) ghost;

        for(Ghost ghost: super.getModel().getGameMap().getGhosts()) {
            GhostController newGhostController;
            if(ghost instanceof InkyGhost)
                newGhostController = new GhostController(this, ghost, GhostState.INCAGE, new InkyStrategy(super.getModel().getGameMap(), ghost, currentBlinky));
            else if(ghost instanceof ClydeGhost)
                newGhostController = new GhostController(this, ghost, GhostState.INCAGE, new ClydeStrategy(super.getModel().getGameMap(), ghost));
            else if(ghost instanceof PinkyGhost)
                newGhostController = new GhostController(this, ghost, GhostState.INCAGE, new PinkyStrategy(super.getModel().getGameMap(), ghost));
            else {
                newGhostController = new GhostController(this, ghost, GhostState.CHASE, new BlinkyStrategy(super.getModel().getGameMap(), ghost));
                currentBlinky = (BlinkyGhost) ghost;
            }

            this.ghostsController.add(newGhostController);
            this.gameState.addObserver(newGhostController);
        }
    }


    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        if (action.equals(GUI.KBD_ACTION.ESQ))
            pause = true;
        else {
            pacManController.addPendingKBDAction(action);
        }
    }

    public boolean isPause() {
        return pause;
    }

    @Override
    public void update(Application application, int frame) {
        gameState.update(frame, frameRate);
        controlGhosts(application, frame);
        pacManController.update(application, frame);
        try {
            checkPacmanGhostCollision();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            viewer.draw(gui, this.getModel().getGameMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(this.started){
            try {
                Application.playSound("pacman_beginning.wav");
                Thread.sleep(4000);
                this.started = false;
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
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
                    gameState.setCurrentState(GameState.GameFrightned);
                    break;
                default: break;
            }
            this.getModel().incrementScore(((Collectable) currentElement).collect());
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

    private void checkPacmanGhostCollision() throws InterruptedException {
        for(GhostController ghostController: ghostsController){
            if(ghostController.getControllablePosition().equals(pacManController.getModel().getPosition())){
                if(ghostController.getState().equals(GhostState.FRIGHTENED)){
                    ghostController.consumeGhost();
                    this.getModel().incrementScore(this.currBonus);
                    this.currBonus *= 2;
                    Application.playSound("pacman_eatghost.wav");
                }
                else if(!ghostController.getState().equals(GhostState.DEAD)){
                    getModel().getGameMap().getPacman().decreaseLives();
                    if (getModel().getGameMap().getPacman().isAlive()) {
                        Application.playSound("pacman_death.wav");
                        Thread.sleep(1500);
                        getModel().resetPositions();
                        resetStates();
                    }
                    else {
                        Thread.sleep(1000);
                    }
                }
            }
        }
    }

    private void resetStates() {
        this.gameState.resetCurrentState();
        for(GhostController controller: ghostsController)
            controller.resetState();
    }

    @Override
    public void notify(GameState state) {
        resetBonus();
    }

    private void resetBonus() { this.currBonus = bonus; }

    public boolean isGameOver() {
        return getModel().getGameMap().getMap()
                .stream().flatMap(Collection::stream).collect(Collectors.toList())
                .stream().noneMatch(x -> x instanceof PacDot)
        || !getModel().getGameMap().getPacman().isAlive();
    }

    public List<GhostController> getGhostsController() { return ghostsController; }

    public PacManController getPacManController() { return pacManController; }

    public LevelInfo getLevelInfo() {
        return this.getModel().getLevelInfo();
    }
}

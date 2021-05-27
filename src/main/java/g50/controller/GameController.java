package g50.controller;

import g50.controller.ghost.*;
import g50.controller.pacman.PacManController;
import g50.controller.states.GameState;
import g50.controller.states.GhostState;
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
    private final GameStateHandler gameState;
    private final List<Class<? extends GhostStrategy>> priorities;
    private int bonus;
    private int frameRate;
    private boolean pause;


    public GameController(GUI gui, Game game, int frameRate) {
        super(game);
        this.ghostsController = new ArrayList<>();
        this.pacManController = new PacManController(this);
        this.viewer = new GameViewer(game, this);
        this.gui = gui;
        this.gameState = new GameStateHandler();
        this.gameState.addObserver(this);
        setUpGhosts();

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
                newGhostController = new GhostController(this, ghost, GhostState.INCAGE, new InkyStrategy(currentBlinky));
            else if(ghost instanceof ClydeGhost)
                newGhostController = new GhostController(this, ghost, GhostState.INCAGE, new ClydeStrategy());
            else if(ghost instanceof PinkyGhost)
                newGhostController = new GhostController(this, ghost, GhostState.INCAGE, new PinkyStrategy());
            else {
                newGhostController = new GhostController(this, ghost, GhostState.CHASE, new BlinkyStrategy());
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
                if(classType.equals(ghostController.getModel().getStrategy().getClass())
                        && ghostController.getModel().getState().equals(GhostState.INCAGE)){
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
                    this.getModel().incrementScore(this.bonus);
                    this.bonus *= 2;
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

    @Override
    public void notify(GameState state) {
        this.bonus = 200;
    }


    public boolean isGameOver() {
        List<List<FixedElement>> v = getModel().getGameMap().getMap();
        boolean check = true;
        for (List<FixedElement> fixedElements : v)
            for (FixedElement fixedElement : fixedElements)
                if (fixedElement instanceof PacDot) {
                    check = false;
                    break;
                }

        return check || !getModel().getGameMap().getPacman().isAlive();
    }

    public List<GhostController> getGhostsController() { return ghostsController; }

    public PacManController getPacManController() { return pacManController; }
}

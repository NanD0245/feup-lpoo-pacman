package g50.controller;

import g50.controller.menu.PauseMenuController;
import g50.model.menu.PauseMenu;
import g50.states.AppState;
import g50.states.GameState;
import g50.states.GhostState;
import g50.Application;
import g50.gui.GUI;
import g50.model.Game;
import g50.model.element.fixed.collectable.PacDot;
import g50.model.element.fixed.collectable.fruit.Fruit;
import g50.model.element.movable.ghost.Ghost;
import g50.model.element.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.Collectable;
import g50.model.element.fixed.collectable.CollectableTriggers;
import g50.model.element.fixed.noncollectable.EmptySpace;
import g50.model.element.movable.ghost.strategy.*;
import g50.view.GameViewer;

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
    private final GameStateHandler gameStateHandler;
    private int currentBonus;
    private static final int bonusMultiplier = 200;
    private final PauseMenu pauseMenu;
    private final PauseMenuController pauseMenuController;
    private GUI.KBD_ACTION lastAction;
    private int fruitDotLimit = 70;
    private int gameFrames = 0;
    private boolean started;

    private static final List<Class<? extends GhostStrategy>> priorities = Arrays.asList(
            BlinkyStrategy.class,
            PinkyStrategy.class,
            InkyStrategy.class,
            ClydeStrategy.class);

    public GameController(GUI gui, Game game) {
        super(game);
        this.ghostsController = new ArrayList<>();
        this.pacManController = new PacManController(this);
        this.viewer = new GameViewer(game);
        this.gui = gui;
        this.gameStateHandler = new GameStateHandler(this, this.getModel().getLevel());
        setUpGhosts();
        this.currentBonus = 200;
        this.pauseMenu = new PauseMenu(game.getScore());
        this.pauseMenuController = new PauseMenuController(gui,pauseMenu);
        this.started = true;
        this.lastAction = GUI.KBD_ACTION.NONE;
    }


    public void setUpGhosts(){
        for (Ghost ghost: getModel().getGameMap().getGhosts()) {
            this.ghostsController.add(new GhostController(ghost));
            ghost.setFramesAndDefaultFramesPerPosition(getModel().getLevel().getGhostFramesPerMovement());
        }
    }

    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        lastAction = action;
    }

    public void handleKBDAction(Application application, GUI.KBD_ACTION action) {
        if (action.equals(GUI.KBD_ACTION.ESQ)) {
            application.setState(AppState.PAUSE_MENU);
            this.pauseMenu.setScore(getModel().getScore());
        }
        if (application.getState().equals(AppState.PAUSE_MENU)) {
            pauseMenuController.addPendingKBDAction(action);
        }
        else {
            pacManController.addPendingKBDAction(action);
        }

    }

    @Override
    public void update(Application application, int frame) throws IOException {
        if (isGameOver()) {
            application.checkHighScore();
            application.setState(AppState.GAME_OVER);
            return;
        }
        else if (isNextLevel()) {
            application.setState(AppState.NEXT_LEVEL_MENU);
        }
        if (lastAction != GUI.KBD_ACTION.NONE){
            handleKBDAction(application, lastAction);
            lastAction = GUI.KBD_ACTION.NONE;
        }

        if (application.getState().equals(AppState.PAUSE_MENU)) {
            pauseMenuController.update(application,frame);
        }
        else {
            gameFrames++;
            gameStateHandler.update(application.getFrameRate());
            pacManController.update(application, gameFrames);
            try {
                checkPacmanGhostCollision();
                controlGhosts(application, gameFrames);
                if(frame % 15 == 0) Application.playSound("pacman_chomp.wav");
                viewer.draw(gui);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            if (this.started) {
                try {
                    Application.playSound("pacman_beginning.wav");
                    Thread.sleep(4000);
                    this.started = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void controlGhosts(Application application, int frame) {
        for(GhostController ghostController: ghostsController){
            ghostController.update(application, frame);
        }
        if(!gameStateHandler.getState().equals(GameState.GAME_FRIGHTENED)) resetBonus();
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
                    this.fruitDotLimit--;
                    break;
                case FRIGHTEN:
                    gameStateHandler.setCurrentState(GameState.GAME_FRIGHTENED);
                case BONUS:
                    Application.playSound("pacman_eatfruit.wav");
                default: break;
            }
            this.getModel().incrementScore(((Collectable) currentElement).collect());
        }

        if (this.fruitDotLimit == 0) {
            try {
                Fruit fruit = this.getModel().getLevel().getFruit(this.getModel().getGameMap().getFruitPosition());
                super.getModel().getGameMap().setElement(fruit, this.getModel().getGameMap().getFruitPosition());
                this.fruitDotLimit = 70;
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void decreaseDotsOnHighestPriorityGhost(){
        for (Class<? extends GhostStrategy> classType: priorities){
            for (GhostController ghostController: ghostsController){
                if (classType.equals(ghostController.getModel().getStrategy().getClass())
                        && ghostController.getModel().getState().equals(GhostState.IN_CAGE)){
                    ghostController.decrementStrategyDotLimit();
                    return;
                }
            }
        }
    }

    private void checkPacmanGhostCollision() throws InterruptedException {
        for (GhostController ghostController: ghostsController){
            if (ghostController.getModel().getPosition().equals(pacManController.getModel().getPosition())){
                if (ghostController.getModel().getState().equals(GhostState.FRIGHTENED)){
                    ghostController.consumeGhost();
                    this.getModel().incrementScore(this.currentBonus);
                    this.currentBonus *= 2;
                    Application.playSound("pacman_eatghost.wav");
                }
                else if (!ghostController.getModel().getState().equals(GhostState.DEAD)){
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
        this.gameStateHandler.resetCurrentState();
        for(GhostController controller: ghostsController)
            controller.getModel().reset();
        resetBonus();
    }

    private void resetBonus() { this.currentBonus = bonusMultiplier; }

    public boolean isGameOver() {
        return !getModel().getGameMap().getPacman().isAlive();
    }

    public boolean isNextLevel()  {
        return getModel().getGameMap().getMap()
                .stream().flatMap(Collection::stream).collect(Collectors.toList())
                .stream().noneMatch(x -> x instanceof PacDot);
    }

    public List<GhostController> getGhostsController() { return ghostsController; }

    public PacManController getPacManController() { return pacManController; }

    public GameStateHandler getGameStateHandler() {
        return gameStateHandler;
    }
}

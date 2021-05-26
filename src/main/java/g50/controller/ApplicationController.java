package g50.controller;

import g50.controller.menu.*;
import g50.controller.states.GameState;
import g50.controller.states.GameState;
import g50.Application;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.Game;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;
import g50.model.map.mapbuilder.DefaultGameMapBuilder;
import g50.model.map.mapbuilder.GameMapBuilder;
import g50.model.menu.*;
import g50.model.menu.Menu;
import g50.view.GameMapViewer;
import g50.view.GameViewer;
import g50.view.menu.*;
import g50.view.Viewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ApplicationController extends Controller<Application> implements GUIObserver {

    private int frameRate = 60;
    private Timer timer;
    private final GameController gameController;
    private final MenuController menuController;
    private final GUI gui;

    public ApplicationController(Application application, GUI gui) throws IOException {
        super(application);

        MainMenu menu = new MainMenu();
        //GameOverMenu menu = new GameOverMenu();
        //PauseMenu menu = new PauseMenu();
        //ControlsMenu menu = new ControlsMenu();
        //CreditsMenu menu = new CreditsMenu();
        GameMap map = new DefaultGameMapBuilder().getBuild();
        this.gui = gui;
        gui.addObserver(this);
        this.gameController = new GameController(gui, new Game(), frameRate);
        this.menuController = new MainMenuController(gui, menu);
        //this.menuController = new GameOverMenuController(gui, new GameOverViewer(), menu);
        //this.menuController = new PauseMenuController(gui, new PauseMenuViewer(), menu);
        //this.menuController = new ControlsMenuController(gui, new ControlsMenuViewer(), menu);
        //this.menuController = new CreditsMenuController(gui, new CreditsMenuViewer(), menu);
    }

    public void setUp(){
        setUp(frameRate);
    }

    public void setUp(int frameRate){
        this.frameRate = frameRate;
        TimerTask task = new TimerTask() {
            int frame = 0;
            @Override
            public void run() {
                frame++;
                update(getModel(), frame);
            }
        };
        timer = new Timer();
        timer.schedule(task, 1000/frameRate, 1000/frameRate);
    }

    public void terminate() throws IOException {
        timer.cancel();
        this.gui.close();
    }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) throws IOException {
        if(action == GUI.KBD_ACTION.QUIT) terminate();
        gameController.addPendingKBDAction(action);
        //this.menuController.addPendingKBDAction(action);
    }

    @Override
    public void update(Application application, int frame) {
        this.gameController.update(application, frame);
        System.out.println("here");
        //this.menuController.update(application, frame);
    }

    @Override
    public void notify(GameState state) {}

    public GameController getGameController() { return this.gameController; }
}

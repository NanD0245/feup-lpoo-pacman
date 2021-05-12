package g50.controller;

import g50.controller.menu.GameOverMenuController;
import g50.controller.menu.MainMenuController;
import g50.controller.menu.MenuController;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;
import g50.model.map.mapbuilder.DefaultGameMapBuilder;
import g50.model.map.mapbuilder.GameMapBuilder;
import g50.model.menu.GameOverMenu;
import g50.model.menu.MainMenu;
import g50.model.menu.Menu;
import g50.view.GameMapViewer;
import g50.view.menu.GameOverViewer;
import g50.view.menu.MainMenuViewer;
import g50.view.menu.MenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ApplicationController implements GUIObserver, Controller {

    private int frameRate = 60;
    private Timer timer;
    private final GameController gameController;
    private final MenuController menuController;
    private final GUI gui;

    public ApplicationController(GUI gui) throws IOException, URISyntaxException, FontFormatException {
        //MainMenu menu = new MainMenu();
        GameOverMenu menu = new GameOverMenu();
        GameMap map = new DefaultGameMapBuilder().getBuild();
        this.gui = gui;
        this.gui.addObserver(this);
        this.gameController = new GameController(gui, new GameMapViewer(), map, 0);
        //this.menuController = new MainMenuController(new MainMenuViewer(gui), menu);
        this.menuController = new GameOverMenuController(new GameOverViewer(gui), menu);
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
                update(frame);
            }
        };
        timer = new Timer();
        timer.schedule(task, 1000/frameRate, 1000/frameRate);
    }

    public void terminate() {
        timer.cancel();
    }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        if(action == GUI.KBD_ACTION.QUIT) terminate();
        //gameController.addPendingAction(action);
        menuController.addPendingKBDAction(action);
    }

    @Override
    public void update(int frame) {
        //this.gameController.update(frame);
        this.menuController.update(frame);
    }
}

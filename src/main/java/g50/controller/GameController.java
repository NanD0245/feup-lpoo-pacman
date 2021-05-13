package g50.controller;

import g50.controller.ghost_strategy.*;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.*;
import g50.model.map.GameMap;
import g50.view.GameMapViewer;

import java.io.IOException;
import java.util.*;

public class GameController implements GUIObserver, Controller {

    private final GameMap map;
    private final List<GhostController> ghostsController;
    private final PacManController pacManController;
    private int framerate = 60;
    private Timer timer;
    private TimerTask updater;
    private final GameMapViewer viewer;

    public GameController(GameMap map, GameMapViewer viewer){
        this.map = map;
        this.viewer = viewer;
        this.ghostsController = new ArrayList<>();
        setUpGhosts();
        this.pacManController = new PacManController(map);
    }

    public void setUpGhosts(){

        for(Ghost ghost: map.getGhosts()) {
            this.ghostsController.add(new GhostController(this.map, ghost, new BlinkyStrategy(this.map, ghost)));
        }
    }

    public void setUp(){
        setUp(framerate);
    }

    public void setUp(int framerate){
        this.framerate = framerate;
        updater = new TimerTask() {
            int frame = 0;
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
    public void addPendingAction(GUI.ACTION action) {
        if(action == GUI.ACTION.QUIT) terminate();
        pacManController.addPendingAction(action);
    }


    @Override
    public void update(int frame) {
        pacManController.update(frame);
        for(GhostController ghostController: ghostsController) ghostController.update(frame);
        try {
            viewer.draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package g50.controller;

import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;
import g50.view.GameMapViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
        for(Ghost ghost: map.getGhosts()) this.ghostsController.add(new GhostController(this.map, ghost));
        this.pacManController = new PacManController(map);
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
    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        if(action == GUI.KBD_ACTION.QUIT) terminate();
        pacManController.addPendingKBDAction(action);
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

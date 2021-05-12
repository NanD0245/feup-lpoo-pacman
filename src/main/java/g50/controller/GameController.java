package g50.controller;

import g50.gui.GUI;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;
import g50.view.GameMapViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController implements Controller{
    private final GameMap map;
    private final List<GhostController> ghostsController;
    private final PacManController pacManController;
    private final GameMapViewer viewer;
    private int score;
    private final GUI gui;

    public GameController(GUI gui, GameMapViewer viewer, GameMap map, int score) {
        this.map = map;
        this.ghostsController = new ArrayList<>();
        for(Ghost ghost: map.getGhosts()){
            this.ghostsController.add(new GhostController(this.map, ghost));
        }
        this.pacManController = new PacManController(map);
        this.score = score;
        this.viewer = viewer;
        this.gui = gui;
    }

    @Override
    public void update(int frame) {
        pacManController.update(frame);
        for (GhostController ghostController: ghostsController){
            ghostController.update(frame);
        }
        try {
            viewer.draw(gui, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPendingAction(GUI.KBD_ACTION action){
        pacManController.addPendingKBDAction(action);
    }
}

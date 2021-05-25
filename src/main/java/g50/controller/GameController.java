package g50.controller;

import g50.Application;
import g50.gui.GUI;
import g50.model.Game;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;
import g50.view.GameMapViewer;
import g50.view.Viewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController extends Controller<Game> {
    private final List<GhostController> ghostsController;
    private final PacManController pacManController;
    private final GameMapViewer viewer;
    private final GUI gui;

    public GameController(GUI gui, GameMapViewer viewer, Game game) {
        super(game);
        this.ghostsController = new ArrayList<>();
        for(Ghost ghost: this.getModel().getMap().getGhosts()){
            this.ghostsController.add(new GhostController(ghost));
        }
        this.pacManController = new PacManController(this.getModel().getMap());
        this.viewer = viewer;
        this.gui = gui;
    }

    public void addPendingAction(GUI.ACTION action){
        pacManController.addPendingAction(action);
    }

    @Override
    public void update(Application application, int frame) {
        pacManController.update(application, frame);
        for (GhostController ghostController: ghostsController){
            ghostController.update(application, frame);
        }
        try {
            viewer.draw(gui, this.getModel().getMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Viewer<Game> getViewer() {
        return null;
    }
}

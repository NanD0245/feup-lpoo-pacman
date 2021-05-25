package g50.controller;

import g50.Application;
import g50.gui.GUI;
import g50.model.Game;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;
import g50.view.GameMapViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController extends Controller<Game> {
    private final Game game;
    private final List<GhostController> ghostsController;
    private final PacManController pacManController;
    private final GameMapViewer viewer;
    private final GUI gui;

    public GameController(GUI gui, GameMapViewer viewer, Game game) {
        super(game);
        this.game = game;
        this.ghostsController = new ArrayList<>();
        for(Ghost ghost: this.game.getMap().getGhosts()){
            this.ghostsController.add(new GhostController(ghost));
        }
        this.pacManController = new PacManController(this.game.getMap());
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
            viewer.draw(gui, this.game.getMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

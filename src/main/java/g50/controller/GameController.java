package g50.controller;

import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

import java.util.ArrayList;
import java.util.List;

public class GameController implements GUIObserver {

    private final GameMap map;
    private final List<GhostController> ghostsController;
    private final PacManController pacManController;

    GameController(GameMap map, List<Ghost> ghosts, PacMan pacman){
        this.map = map;
        this.ghostsController = new ArrayList<GhostController>();
        for(Ghost ghost: ghosts) this.ghostsController.add(new GhostController(map, ghost));
        this.pacManController = new PacManController(map, pacman);
    }

    @Override
    public void addPendingAction(GUI.ACTION action) {
        pacManController.addPendingAction(action);
    }
}

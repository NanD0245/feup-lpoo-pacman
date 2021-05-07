package g50.controller;

import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.PacMan;
import g50.model.map.GameMap;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class PacManController {

    private final PacMan controllabe;
    private final GameMap map;
    private Orientation nextBufferedOrientation;
    private static final Map<GUI.ACTION, Orientation> actionToOrientation = Map.of(
            GUI.ACTION.UP, Orientation.UP,
            GUI.ACTION.DOWN, Orientation.DOWN,
            GUI.ACTION.LEFT, Orientation.LEFT,
            GUI.ACTION.RIGHT, Orientation.RIGHT,
            GUI.ACTION.OTHER, null,
            GUI.ACTION.QUIT, null
    );

    public PacManController(GameMap map, PacMan controllabe){
        this.map = map;
        this.controllabe = controllabe;
    }

    public void addPendingAction(GUI.ACTION action) {
        List<Orientation> oris = map.getAvailableOrientations(controllabe.getPosition());
        Orientation actionOrientation = actionToOrientation.get(action);
        if(actionOrientation == null || actionOrientation == controllabe.getOrientation()) return;

        if(oris.contains(actionOrientation)) controllabe.setOrientation(actionOrientation);
        else this.nextBufferedOrientation = actionOrientation;
    }
}

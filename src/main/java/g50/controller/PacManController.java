package g50.controller;

import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.Collectable;
import g50.model.element.fixed.nonCollectable.EmptySpace;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.PacMan;
import g50.model.map.GameMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class PacManController implements Controller{

    private final PacMan controllable;
    private final GameMap map;
    private Orientation nextBufferedOrientation;
    private int velocity = 15;

    private static final Map<GUI.KBD_ACTION, Orientation> actionToOrientation = new HashMap<>() {{
                put(GUI.KBD_ACTION.UP, Orientation.UP);
                put(GUI.KBD_ACTION.DOWN, Orientation.DOWN);
                put(GUI.KBD_ACTION.LEFT, Orientation.LEFT);
                put(GUI.KBD_ACTION.RIGHT, Orientation.RIGHT);
                put(GUI.KBD_ACTION.OTHER, null);
                put(GUI.KBD_ACTION.QUIT, null);
    }};

    public PacManController(GameMap map){
        this.map = map;
        this.controllable = map.getPacman();
    }

    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        List<Orientation> oris = map.getAvailableOrientations(controllable.getPosition());
        Orientation actionOrientation = actionToOrientation.get(action);
        if(actionOrientation == null || actionOrientation == controllable.getOrientation()) return;

        if(oris.contains(actionOrientation)){
            controllable.setOrientation(actionOrientation);
            if(nextBufferedOrientation == actionOrientation.getOpposite()) nextBufferedOrientation = null;
        }
        else this.nextBufferedOrientation = actionOrientation;
    }

    @Override
    public void update(int frame) {
        if(frame % velocity != 0) return;

        Position currentPos = controllable.getPosition();
        FixedElement currentElement = map.getElement(currentPos);

        if(currentElement instanceof Collectable)
            map.setElement(new EmptySpace(currentPos), currentPos);

        moveToNewPosition(map.getAvailableOrientations(controllable.getPosition()));
    }

    private void moveToNewPosition(List<Orientation> oris){
        if(oris.contains(nextBufferedOrientation)) {
            controllable.move(nextBufferedOrientation, map.getColumns(), map.getLines());
            nextBufferedOrientation = null;
        } else if(oris.contains(controllable.getOrientation()))
            controllable.move(controllable.getOrientation(), map.getColumns(), map.getLines());
    }
}

package g50.controller;

import g50.Application;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.Collectable;
import g50.model.element.fixed.nonCollectable.EmptySpace;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.PacMan;
import g50.model.map.GameMap;
import g50.view.Viewer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class PacManController extends Controller<PacMan> {
    private final GameMap map;
    private Orientation nextBufferedOrientation;
    private int velocity = 15;

    private static final Map<GUI.ACTION, Orientation> actionToOrientation = new HashMap<>() {{
                put(GUI.ACTION.UP, Orientation.UP);
                put(GUI.ACTION.DOWN, Orientation.DOWN);
                put(GUI.ACTION.LEFT, Orientation.LEFT);
                put(GUI.ACTION.RIGHT, Orientation.RIGHT);
                put(GUI.ACTION.OTHER, null);
                put(GUI.ACTION.QUIT, null);
    }};

    public PacManController(GameMap map){
        super(map.getPacman());
        this.map = map;
    }

    public void addPendingAction(GUI.ACTION action) {
        List<Orientation> oris = map.getAvailableOrientations(getModel().getPosition());
        Orientation actionOrientation = actionToOrientation.get(action);
        if(actionOrientation == null || actionOrientation == getModel().getOrientation()) return;

        if(oris.contains(actionOrientation)){
            getModel().setOrientation(actionOrientation);
            if(nextBufferedOrientation == actionOrientation.getOpposite()) nextBufferedOrientation = null;
        }
        else this.nextBufferedOrientation = actionOrientation;
    }

    private void moveToNewPosition(List<Orientation> oris){
        if(oris.contains(nextBufferedOrientation)) {
            getModel().move(nextBufferedOrientation, map.getColumns(), map.getLines());
            nextBufferedOrientation = null;
        } else if(oris.contains(getModel().getOrientation()))
            getModel().move(getModel().getOrientation(), map.getColumns(), map.getLines());
    }

    @Override
    public void update(Application application, int frame) {
        if(frame % velocity != 0) return;

        Position currentPos = getModel().getPosition();
        FixedElement currentElement = map.getElement(currentPos);

        if(currentElement instanceof Collectable)
            map.setElement(new EmptySpace(currentPos), currentPos);

        moveToNewPosition(map.getAvailableOrientations(getModel().getPosition()));
    }

    @Override
    public Viewer<PacMan> getViewer() {
        return null;
    }
}

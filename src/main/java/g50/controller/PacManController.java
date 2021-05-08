package g50.controller;

import g50.gui.GUI;
import g50.gui.GUIObserver;
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

    private static final Map<GUI.ACTION, Orientation> actionToOrientation = new HashMap<>() {{
                put(GUI.ACTION.UP, Orientation.UP);
                put(GUI.ACTION.DOWN, Orientation.DOWN);
                put(GUI.ACTION.LEFT, Orientation.LEFT);
                put(GUI.ACTION.RIGHT, Orientation.RIGHT);
                put(GUI.ACTION.OTHER, null);
                put(GUI.ACTION.QUIT, null);
    }};

    public PacManController(GameMap map){
        this.map = map;
        this.controllable = map.getPacman();
    }

    public void addPendingAction(GUI.ACTION action) {
        List<Orientation> oris = map.getAvailableOrientations(controllable.getPosition());
        Orientation actionOrientation = actionToOrientation.get(action);
        if(actionOrientation == null || actionOrientation == controllable.getOrientation()) return;

        if(oris.contains(actionOrientation)) controllable.setOrientation(actionOrientation);
        else this.nextBufferedOrientation = actionOrientation;
    }

    @Override
    public void update(int frame) {
        if(frame % velocity != 0) return;
        List<Orientation> oris = map.getAvailableOrientations(controllable.getPosition());
        System.out.println(oris);
        if(oris.contains(nextBufferedOrientation)) {
            controllable.move(nextBufferedOrientation);
            nextBufferedOrientation = null;
        } else if(oris.contains(controllable.getOrientation()))
            controllable.move(controllable.getOrientation());
    }
}

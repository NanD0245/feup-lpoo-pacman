package g50.controller;

import g50.controller.states.GameState;
import g50.Application;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.Collectable;
import g50.model.element.fixed.nonCollectable.Door;
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
    private final GameController gameController;
    private final GameMap gameMap;
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


    public PacManController(GameController gameController){
        super(gameController.getModel().getGameMap().getPacman());
        this.gameController = gameController;
        this.gameMap = gameController.getModel().getGameMap();
    }


    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        List<Orientation> oris =
                gameMap.getAvailableOrientations(getModel().getPosition());

        for(Orientation orientation: oris){
            if(gameController.getModel().getGameMap().getElement(getModel().getPosition().getAdjacent(orientation)) instanceof Door){
                oris.remove(orientation);
                break;
            }
        }

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
            getModel().move(nextBufferedOrientation, gameMap.getColumns(), gameMap.getLines());
            nextBufferedOrientation = null;
        } else if(oris.contains(getModel().getOrientation()))
            getModel().move(getModel().getOrientation(), gameMap.getColumns(), gameMap.getLines());
    }

    @Override
    public void update(Application application, int frame) {
        if(frame % 20 == 0) Application.playSound("pacman_chomp.wav");
        if(frame % velocity != 0) return;

        gameController.consumeMapElement(super.getModel().getPosition());

        Position currentPos = super.getModel().getPosition();
        moveToNewPosition(gameMap.getAvailableOrientations(super.getModel().getPosition()), currentPos);
    }

    @Override
    public void notify(GameState state) { }

    private void moveToNewPosition(List<Orientation> oris, Position currentPos){
        if(oris.contains(nextBufferedOrientation)
        && !(gameMap.getElement(currentPos.getAdjacent(nextBufferedOrientation)) instanceof Door)){
            super.getModel().move(nextBufferedOrientation, gameMap.getColumns(), gameMap.getLines());
            nextBufferedOrientation = null;
        } else if(oris.contains(super.getModel().getOrientation()))
            super.getModel().move(super.getModel().getOrientation(), gameMap.getColumns(), gameMap.getLines());
    }

    public Position getControllablePosition() { return super.getModel().getPosition(); }

}

package g50.controller;

import g50.Application;
import g50.gui.GUI;
import g50.model.element.Position;
import g50.model.element.fixed.noncollectable.Door;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.PacMan;
import g50.model.map.GameMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacManController extends Controller<PacMan> {
    private final GameController gameController;
    private final GameMap gameMap;
    private Orientation nextOrientation;
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
        this.getModel().setFramesAndDefaultFramesPerPosition(gameController.getModel().getLevel().getPacManFramesPerMovement());
    }

    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        List<Orientation> orientations =
                gameMap.getAvailableOrientations(getModel().getPosition());
        for(Orientation orientation: orientations){
            if(gameController.getModel().getGameMap().getElement(getModel().getPosition().getAdjacent(orientation)) instanceof Door){
                orientations.remove(orientation);
                break;
            }
        }
        Orientation actionOrientation = actionToOrientation.get(action);
        if (actionOrientation == null || actionOrientation == getModel().getOrientation()) return;
        if (orientations.contains(actionOrientation)){
            getModel().setOrientation(actionOrientation);
            if (nextOrientation == actionOrientation.getOpposite()) nextOrientation = null;
        }
        else this.nextOrientation = actionOrientation;
    }

    @Override
    public void update(Application application, int frame) {
        if(frame % getModel().getFramesPerPosition() != 0) return;
        gameController.consumeMapElement(getModel().getPosition());
        moveToNewPosition(gameMap.getAvailableOrientations(getModel().getPosition()), getModel().getPosition());
    }

    private void moveToNewPosition(List<Orientation> orientations, Position currentPos){
        if (orientations.contains(nextOrientation)
        && !(gameMap.getElement(currentPos.getAdjacent(nextOrientation)) instanceof Door)){
            getModel().move(nextOrientation, gameMap.getColumns(), gameMap.getLines());
            nextOrientation = null;
        } else if (orientations.contains(getModel().getOrientation()))
            getModel().move(super.getModel().getOrientation(), gameMap.getColumns(), gameMap.getLines());
    }

    public Orientation getNextOrientation() {
        return nextOrientation;
    }

}

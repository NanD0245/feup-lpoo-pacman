package g50.controller;

import g50.states.GameState;
import g50.states.GhostState;
import g50.gui.GUI;
import g50.model.element.movable.Orientation;
import g50.Application;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

import java.io.IOException;
import java.util.List;

public class GhostController extends Controller<Ghost> {
    private Orientation nextBufferedOrientation;

    public GhostController(Ghost ghost){
        super(ghost);
    }

    @Override
    public void update(Application application, int frame) {
        GameController gameController = (GameController) application.getController();
        updateGhostState(gameController);
        updateGhostSpeed(gameController);
        if (frame % getModel().getFramesPerPosition() == 0)
            updatePositions(gameController);
    }

    private void updateGhostSpeed(GameController gameController) {
        if(getModel().getState().equals(GhostState.FRIGHTENED))
            getModel().setFramesPerPosition(gameController.getModel().getLevel().getFrightenedGhostFramesPerMovement());
        else
            getModel().setDefaultFramesPerPosition();
    }

    private void updateGhostState(GameController gameController) {
        if (isDeadInSpawnPosition())
            getModel().setState(GhostState.IN_CAGE);
        else if (isReadyToLeaveCage())
            getModel().setState(GhostState.LEAVING_CAGE);
        else if (isRegularState() || isReadyToEnterRegularState(gameController))
            updateGhostState(gameController.getGameStateHandler().getState());
    }

    private void updatePositions(GameController gameController){
        Orientation newOrientation = getModel().getStrategy().getNextOrientation(gameController.getModel().getGameMap(),
                getModel(), getModel().getState());

        if (newOrientation == null) return;
        else getModel().setOrientation(newOrientation);
        moveToNewPosition(gameController.getModel().getGameMap(), gameController.getModel().getGameMap().
                getAvailableOrientations(getModel().getPosition()));

    }

    private void moveToNewPosition(GameMap gameMap, List<Orientation> orientations){
        if (orientations.contains(nextBufferedOrientation)){
            getModel().move(nextBufferedOrientation, gameMap.getColumns(), gameMap.getLines());
            nextBufferedOrientation = null;
        } else if (orientations.contains(getModel().getOrientation()))
            getModel().move(getModel().getOrientation(), gameMap.getColumns(), gameMap.getLines());
    }

    private void updateGhostState(GameState gameState) {
        switch (gameState) {
            case GAME_CHASE: {
                getModel().setState(GhostState.CHASE);
                break;
            }
            case GAME_SCATTER: {
                getModel().setState(GhostState.SCATTER);
                break;
            }
            case GAME_FRIGHTENED:
                getModel().setState(GhostState.FRIGHTENED);
                break;
        }
    }

    public void decrementStrategyDotLimit() {
        if (getModel().getStrategy().getDotLimit() > 0) this.getModel().getStrategy().decrementDotLimit();
    }

    public void setNextBufferedOrientation(Orientation orientation){
        this.nextBufferedOrientation = orientation;
    }

    public void consumeGhost() {
        getModel().setState(GhostState.DEAD);
        this.getModel().getStrategy().resetDotLimit();
    }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) throws IOException {}

    public void reverseOrientation(){
        if (this.getModel().getState() != GhostState.LEAVING_CAGE &&
                this.getModel().getState() != GhostState.IN_CAGE && this.getModel().getState() != GhostState.DEAD)
            setNextBufferedOrientation(getModel().getOrientation().getOpposite());
    }

    private boolean isDeadInSpawnPosition(){
        return getModel().getState() == GhostState.DEAD && getModel().getPosition().equals(getModel().getSpawnPosition());
    }

    private boolean isReadyToLeaveCage() {
        return getModel().getState() == GhostState.IN_CAGE && getModel().getStrategy().getDotLimit() == 0;
    }

    private boolean isRegularState(){
        GhostState state = getModel().getState();
        return state != GhostState.IN_CAGE && state != GhostState.DEAD
                && state != GhostState.LEAVING_CAGE;
    }

    private boolean isReadyToEnterRegularState(GameController gameController) {
        return getModel().getState() == GhostState.LEAVING_CAGE && getModel().getPosition().
                equals(gameController.getModel().getGameMap().getGhostSpawnPosition());
    }
}

package g50.controller.ghost;

import g50.controller.Controller;
import g50.controller.GameController;
import g50.controller.states.GameState;
import g50.controller.states.GhostState;
import g50.gui.GUI;
import g50.model.element.Position;
import g50.model.element.movable.Orientation;
import g50.Application;
import g50.model.element.movable.ghost.Ghost;
import g50.model.element.movable.ghost.strategy.GhostStrategy;

import java.io.IOException;
import java.util.List;

public class GhostController extends Controller<Ghost> {
    private final GameController gameController;
    private Orientation nextBufferedOrientation;
    private GameState gameState;
    private int speed = 15;

    public GhostController(GameController gameController, Ghost ghost, GhostState state, GhostStrategy strategy){
        super(ghost);
        this.gameController = gameController;
        this.gameState = GameState.GameScatter;
    }

    @Override
    public void update(Application application, int frame) {
        if (frame % speed != 0) return;

        if(getModel().getState() == GhostState.DEAD && getModel().getPosition().equals(getModel().getSpawnPosition()))
            getModel().setState(GhostState.INCAGE);
        else if (getModel().getState() == GhostState.INCAGE && getModel().getStrategy().getDotLimit() == 0)
            getModel().setState(GhostState.LEAVINGCAGE);
        // state must be updated whenever the ghost isn't in cage
        // or whenever the ghost reaches the exit of spawn
        // start position (right after LEAVINGCAGE state)
        else if(getModel().getState() != GhostState.INCAGE && getModel().getState() != GhostState.DEAD
                && getModel().getState() != GhostState.LEAVINGCAGE ||
                (getModel().getState() == GhostState.LEAVINGCAGE && getModel().getPosition().
                        equals(gameController.getModel().getGameMap().getGhostStartPos())))
            updateStateFromGameState();

        Orientation newOrientation = getModel().getStrategy().getNextOrientation(application.getGame().getGameMap(), getModel(), getModel().getState());
        if(newOrientation == null) return;
        else getModel().setOrientation(newOrientation);
        moveToNewPosition(gameController.getModel().getGameMap().getAvailableOrientations(getModel().getPosition()),
                getModel().getPosition());
    }

    private void moveToNewPosition(List<Orientation> oris, Position currentPos){
        if(oris.contains(nextBufferedOrientation)){
            getModel().move(nextBufferedOrientation, gameController.getModel().getGameMap().getColumns(),
                    gameController.getModel().getGameMap().getLines());
            nextBufferedOrientation = null;
        } else if(oris.contains(getModel().getOrientation()))
            getModel().move(getModel().getOrientation(), gameController.getModel().getGameMap().getColumns(),
                    gameController.getModel().getGameMap().getLines());
    }

    private void updateStateFromGameState() {
        switch (gameState) {
            case GameChase: {
                getModel().setState(GhostState.CHASE);
                break;
            }
            case GameScatter: {
                getModel().setState(GhostState.SCATTER);
                break;
            }
            case GameFrightned:
                getModel().setState(GhostState.FRIGHTENED);
                break;
        }
    }

    public void decrementStrategyDotLimit() {
        if (getModel().getStrategy().getDotLimit() > 0) this.getModel().getStrategy().decrementDotLimit();
    }

    public void setNextBufferedOrientation(Orientation ori){
        this.nextBufferedOrientation = ori;
    }

    @Override
    public void notify(GameState state) {
        this.gameState = state;
        if (getModel().getState() != GhostState.LEAVINGCAGE
                && getModel().getState() != GhostState.INCAGE && getModel().getState() != GhostState.DEAD)
            setNextBufferedOrientation(getModel().getOrientation().getOpposite());
    }

    public void consumeGhost() {
        getModel().setState(GhostState.DEAD);
        this.getModel().getStrategy().resetDotLimit();
    }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) throws IOException {

    }
}

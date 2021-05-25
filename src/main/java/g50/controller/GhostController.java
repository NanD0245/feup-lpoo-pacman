package g50.controller;

import g50.controller.ghost_strategy.GhostStrategy;
import g50.controller.states.GameState;
import g50.controller.states.GhostState;
import g50.model.Position;
import g50.model.element.movable.Orientation;
import g50.Application;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;
import g50.view.Viewer;
import java.util.List;

public class GhostController extends Controller<Ghost> {

    private final Ghost controllable;
    private final GameController gameController;
    private final GameMap map;
    private GhostState state;
    private GameState gameState;
    private GhostStrategy strategy;
    private Orientation nextBufferedOrientation;
    private int velocity = 25;


    public GhostController(GameController gameController, Ghost ghost, GhostState state, GhostStrategy strategy){
        super(ghost);
        this.gameController = gameController;
        this.map = gameController.getModel().getMap();
        this.controllable = ghost;
        this.state = state;
        this.strategy = strategy;
        this.gameState = GameState.GameScatter;
    }

    @Override
    public void update(Application application, int frame) {
        if (frame % velocity != 0) return;

        if(state == GhostState.DEAD && controllable.getPosition().equals(controllable.getStartPosition()))
            state = GhostState.INCAGE;

        if(state == GhostState.INCAGE && this.strategy.getDotLimit() == 0)
            state = GhostState.LEAVINGCAGE;
        // state must be updated whenever the ghost isn't in cage
        // or whenever the ghost reaches the exit of spawn
        // start position (right after LEAVINGCAGE state)
        else if(state != GhostState.INCAGE && state != GhostState.DEAD && state != GhostState.LEAVINGCAGE ||
                (state == GhostState.LEAVINGCAGE && controllable.getPosition().equals(map.getGhostStartPos())))
            updateStateFromGameState();

        Orientation newOrientation = strategy.getNextOrientation(state);
        if(newOrientation == null) return;
        else controllable.setOrientation(newOrientation);
        moveToNewPosition(map.getAvailableOrientations(controllable.getPosition()), controllable.getPosition());
    }

    private void moveToNewPosition(List<Orientation> oris, Position currentPos){
        if(oris.contains(nextBufferedOrientation)){
            controllable.move(nextBufferedOrientation, map.getColumns(), map.getLines());
            nextBufferedOrientation = null;
        } else if(oris.contains(controllable.getOrientation()))
            controllable.move(controllable.getOrientation(), map.getColumns(), map.getLines());
    }

    private void updateStateFromGameState() {
        switch (gameState) {
            case GameChase: {
                state = GhostState.CHASE;
                return;
            }
            case GameScatter: {
                state = GhostState.SCATTER;
                return;
            }
            case GameFrightned: state = GhostState.FRIGHTENED;
        }
    }

    public void decrementStrategyDotLimit(){
        if(this.strategy.getDotLimit() > 0) this.strategy.decrementDotLimit();
    }

    public void setNextBufferedOrientation(Orientation ori){
        this.nextBufferedOrientation = ori;
    }

    @Override
    public void notify(GameState state) {
        this.gameState = state;
        if(this.state != GhostState.LEAVINGCAGE && this.state != GhostState.INCAGE && this.state != GhostState.DEAD)
            setNextBufferedOrientation(controllable.getOrientation().getOpposite());
    }

    public GhostState getState() {
        return this.state;
    }

    public GhostStrategy getStrategy(){
        return this.strategy;
    }

    public Position getControllablePosition() { return this.controllable.getPosition(); }

    public void consumeGhost() {
        state = GhostState.DEAD;
        this.strategy.resetDotLimit();
    }
}

package g50.controller;

import g50.controller.ghost_strategy.GhostStrategy;
import g50.model.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.nonCollectable.Door;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

import java.util.List;

public class GhostController implements Controller{

    private final Ghost controllable;
    private final GameMap map;
    private GhostState state;
    private GameState.CurrentState gameState;
    private GhostStrategy strategy;
    private Orientation nextBufferedOrientation;
    private int velocity = 25;

    public GhostController(GameMap map, Ghost ghost, GhostState state, GhostStrategy strategy){
        this.map = map;
        this.controllable = ghost;
        this.state = state;
        this.strategy = strategy;
        this.gameState = GameState.CurrentState.GameScatter;
    }

    @Override
    public void update(int frame) {
        if (frame % velocity != 0) return;

        if(state == GhostState.INCAGE && this.strategy.getDotLimit() == 0)
            state = GhostState.LEAVINGCAGE;
        // state must be updated whenever the ghost isn't in cage
        // or whenever the ghost reaches the exit of spawn
        // start position (right after LEAVINGCAGE state)
        else if((state != GhostState.INCAGE && state != GhostState.LEAVINGCAGE) ||
        controllable.getPosition().equals(map.getGhostStartPos()))
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

    public void setNextBufferedOrientation(Orientation ori){
        this.nextBufferedOrientation = ori;
    }

    @Override
    public void notify(GameState.CurrentState state) {
        this.gameState = state;
        setNextBufferedOrientation(controllable.getOrientation().getOpposite());
    }

}

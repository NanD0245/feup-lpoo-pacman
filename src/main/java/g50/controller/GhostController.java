package g50.controller;

import g50.controller.ghost_strategy.GhostStrategy;
import g50.model.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

import java.util.List;

public class GhostController implements Controller{

    private final Ghost controllable;
    private final GameMap map;
    private GhostState state;
    private GhostStrategy strategy;
    private int velocity = 25;

    public GhostController(GameMap map, Ghost ghost, GhostStrategy strategy){
        this.map = map;
        this.controllable = ghost;
        this.state = GhostState.CHASE;
        this.strategy = strategy;
    }

    @Override
    public void update(int frame) {
        if (frame % velocity != 0) return;

        Orientation newOrientation = null;
        if (map.isIntersection(controllable.getPosition())) {
            controllable.setOrientation(strategy.getNextOrientation(state));
        } else {
            List<Orientation> oris = map.getAvailableOrientations(controllable.getPosition());
            oris.remove(controllable.getOrientation().getOpposite());
            if(oris.size() > 0) controllable.setOrientation(oris.get(0));
            else return;
        }
        controllable.move(controllable.getOrientation(), map.getColumns(), map.getLines());
    }
}

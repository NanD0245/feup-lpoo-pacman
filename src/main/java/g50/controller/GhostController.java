package g50.controller;

import g50.controller.ghost_strategy.GhostStrategy;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

public class GhostController implements Controller{

    private final Ghost controllabe;
    private final GameMap map;
    private GhostState state;
    private GhostStrategy strategy;

    public GhostController(GameMap map, Ghost ghost){
        this.map = map;
        this.controllabe = ghost;
        this.state = GhostState.CHASE;
    }

    @Override
    public void update(int frame) {

    }
}

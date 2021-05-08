package g50.controller;

import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

public class GhostController {

    private final Ghost controllabe;
    private final GameMap map;

    public GhostController(GameMap map, Ghost ghost){
        this.map = map;
        this.controllabe = ghost;
    }
}

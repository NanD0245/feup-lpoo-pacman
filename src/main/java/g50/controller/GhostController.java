package g50.controller;

import g50.Application;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

public class GhostController extends Controller<Ghost> {
    public GhostController(Ghost ghost){
        super(ghost);
    }

    @Override
    public void update(Application application, int frame) {

    }
}

package g50.controller;

import g50.Application;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;
import g50.view.Viewer;

public class GhostController extends Controller<Ghost> {
    public GhostController(Ghost ghost){
        super(ghost);
    }

    @Override
    public void update(Application application, int frame) {

    }

    @Override
    public Viewer<Ghost> getViewer() {
        return null;
    }
}

package g50.view.pacmanViewerFactory;

import g50.controller.GameController;
import g50.model.element.movable.Orientation;
import g50.view.ViewProperty;

public class OrientationPacManViewerBuilder extends PacManViewerBuilder{

    public OrientationPacManViewerBuilder(GameController gameController) {
        super(gameController);

        this.properties.put(Orientation.UP, new ViewProperty("#FFFF00", ' ' ));
        this.properties.put(Orientation.LEFT, new ViewProperty("#FFFF00", ' ' ));
        this.properties.put(Orientation.DOWN, new ViewProperty("#FFFF00", ' ' ));
        this.properties.put(Orientation.RIGHT, new ViewProperty("#FFFF00", ' ' ));
    }
}

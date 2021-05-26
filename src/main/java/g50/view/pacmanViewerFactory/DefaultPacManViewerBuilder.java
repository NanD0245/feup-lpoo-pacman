package g50.view.pacmanViewerFactory;

import g50.controller.GameController;
import g50.model.element.movable.Orientation;
import g50.view.ViewProperty;

public class DefaultPacManViewerBuilder extends PacManViewerBuilder{

    public DefaultPacManViewerBuilder(GameController gameController) {
        super(gameController);

        this.properties.put(Orientation.UP, new ViewProperty("#FFFF00", 'À' ));
        this.properties.put(Orientation.LEFT, new ViewProperty("#FFFF00", 'Á' ));
        this.properties.put(Orientation.DOWN, new ViewProperty("#FFFF00", 'Â' ));
        this.properties.put(Orientation.RIGHT, new ViewProperty("#FFFF00", 'Ã' ));
    }
}

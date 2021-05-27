package g50.view;

import g50.controller.GameController;
import g50.gui.GUI;
import g50.model.element.Element;
import g50.model.element.fixed.FixedElement;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;
import g50.view.ghostViewerFactory.DefaultGhostViewerBuilder;
import g50.view.ghostViewerFactory.GhostViewerBuilder;
import g50.view.pacmanViewerFactory.DefaultPacManViewerBuilder;
import g50.view.pacmanViewerFactory.PacManViewerBuilder;

import java.io.IOException;
import java.util.List;

public class GameMapViewer extends Viewer<GameMap>{
    private final ElementViewerBuilder elementViewerBuilder;
    private final PacManViewerBuilder pacManViewerBuilder;
    private final GhostViewerBuilder ghostViewerBuilder;

    public GameMapViewer(GameMap gameMap, GameController gameController){
        super(gameMap);
        this.elementViewerBuilder = new DefaultElementViewerBuilder();
        this.pacManViewerBuilder = new DefaultPacManViewerBuilder(gameController);
        this.ghostViewerBuilder = new DefaultGhostViewerBuilder(gameController);
    }

    public ElementViewerBuilder getElementViewerBuilder() {
        return elementViewerBuilder;
    }

    public PacManViewerBuilder getPacManViewerBuilder() {
        return pacManViewerBuilder;
    }

    public GhostViewerBuilder getGhostViewerBuilder() {
        return ghostViewerBuilder;
    }

    @Override
    public void draw(GUI gui) throws IOException {
        final List<List<FixedElement>> map = getModel().getMap();
        for (int line = 0; line < getModel().getLines(); line++){
            for (int column = 0; column < getModel().getColumns(); column++){
                Element element = map.get(line).get(column);
                this.elementViewerBuilder.getViewer(element).draw(gui);
            }
        }

        for (Ghost ghost : getModel().getGhosts()){
            this.ghostViewerBuilder.getViewer(ghost).draw(gui);
        }
        this.pacManViewerBuilder.getViewer(getModel().getPacman()).draw(gui);
    }
}

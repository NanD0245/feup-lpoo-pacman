package g50.view;

import g50.gui.GUI;
import g50.model.element.Element;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.noncollectable.Wall;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;
import g50.view.builders.*;

import java.io.IOException;
import java.util.List;

public class GameMapViewer extends Viewer<GameMap> {
    private final ElementViewerBuilder elementViewerBuilder;
    private final PacManViewerBuilder pacManViewerBuilder;
    private final GhostViewerBuilder ghostViewerBuilder;
    private final WallViewerBuilder wallViewerBuilder;

    public GameMapViewer(GameMap gameMap){
        super(gameMap);
        this.elementViewerBuilder = new DefaultElementViewerBuilder();
        this.pacManViewerBuilder = new DefaultPacManViewerBuilder();
        this.ghostViewerBuilder = new DefaultGhostViewerBuilder();
        this.wallViewerBuilder = new DefaultWallViewerBuilder();
    }

    @Override
    public void draw(GUI gui) throws IOException {
        final List<List<FixedElement>> map = getModel().getMap();
        for (int line = 0; line < getModel().getLines(); line++){
            for (int column = 0; column < getModel().getColumns(); column++){
                Element element = map.get(line).get(column);
                if (element instanceof Wall) {
                    this.wallViewerBuilder.getViewer((Wall)element).draw(gui);
                } else {
                    this.elementViewerBuilder.getViewer(element).draw(gui);
                }
            }
        }

        for (Ghost ghost : getModel().getGhosts()){
            this.ghostViewerBuilder.getViewer(ghost).draw(gui);
        }
        this.pacManViewerBuilder.getViewer(getModel().getPacman()).draw(gui);
    }

    public PacManViewerBuilder getPacManViewerBuilder() {
        return pacManViewerBuilder;
    }

    public GhostViewerBuilder getGhostViewerBuilder() {
        return ghostViewerBuilder;
    }

    public WallViewerBuilder getWallViewerBuilder() {
        return wallViewerBuilder;
    }
}

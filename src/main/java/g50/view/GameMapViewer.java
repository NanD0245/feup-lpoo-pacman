package g50.view;

import g50.controller.GameController;
import g50.gui.GUI;
import g50.model.Position;
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

public class GameMapViewer {
    private final ElementViewerBuilder elementViewerBuilder;
    private final PacManViewerBuilder pacManViewerBuilder;
    private final GhostViewerBuilder ghostViewerBuilder;

    public GameMapViewer(GameController gameController){
        this.elementViewerBuilder = new DefaultElementViewerBuilder();
        this.pacManViewerBuilder = new DefaultPacManViewerBuilder(gameController);
        this.ghostViewerBuilder = new DefaultGhostViewerBuilder(gameController);
    }

    public void draw(GUI gui, GameMap gameMap) throws IOException {
        final List<List<FixedElement>> map = gameMap.getMap();
        for (int line = 0; line < gameMap.getLines(); line++){
            for (int column = 0; column < gameMap.getColumns(); column++){
                Position position = new Position(column, line);
                Element element = map.get(line).get(column);
                this.elementViewerBuilder.getViewer(element).draw(gui);
            }
        }

        this.pacManViewerBuilder.getViewer(gameMap.getPacman()).draw(gui);
        for (Ghost ghost : gameMap.getGhosts()){
            this.ghostViewerBuilder.getViewer(ghost).draw(gui);
        }
    }
}

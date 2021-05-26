package g50.view;

import g50.gui.GUI;
import g50.model.Position;
import g50.model.element.Element;
import g50.model.element.fixed.FixedElement;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

import java.io.IOException;
import java.util.List;

public class GameMapViewer {
    private final ElementViewerBuilder viewerBuilder;

    public GameMapViewer(){
        this.viewerBuilder = new DefaultElementViewerBuilder();
    }

    public void draw(GUI gui, GameMap gameMap) throws IOException {
        final List<List<FixedElement>> map = gameMap.getMap();
        for (int line = 0; line < gameMap.getLines(); line++){
            for (int column = 0; column < gameMap.getColumns(); column++){
                Position position = new Position(column, line);
                Element element = map.get(line).get(column);
                this.viewerBuilder.getViewer(element).draw(gui);
            }
        }

        this.viewerBuilder.getViewer(gameMap.getPacman()).draw(gui);
        for (Ghost ghost : gameMap.getGhosts()){
            this.viewerBuilder.getViewer(ghost).draw(gui);
        }
    }
}

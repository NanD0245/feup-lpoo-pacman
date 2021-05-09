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
    private final GUI gui;
    private final GameMap gameMap;
    private final ElementViewerBuilder viewerFactory;

    public GameMapViewer(GUI gui, GameMap gameMap){
        this.gui = gui;
        this.gameMap = gameMap;
        this.viewerFactory = new DefaultElementViewerBuilder();
    }

    public void draw() throws IOException {
        this.gui.clear();

        final List<List<FixedElement>> map = this.gameMap.getMap();
        for (int line = 0; line < this.gameMap.getLines(); line++){
            for (int column = 0; column < this.gameMap.getColumns(); column++){
                Position position = new Position(column, line);
                Element element = map.get(line).get(column);
                this.viewerFactory.getViewer(this.gui, element).draw(position);
            }
        }

        this.viewerFactory.getViewer(this.gui, this.gameMap.getPacman()).draw(this.gameMap.getPacman().getPosition());
        for (Ghost ghost : this.gameMap.getGhosts()){
            this.viewerFactory.getViewer(this.gui, ghost).draw(ghost.getPosition());
        }

        this.gui.refresh();
    }
}

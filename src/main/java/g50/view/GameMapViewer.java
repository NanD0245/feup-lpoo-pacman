package g50.view;

import g50.gui.GUI;
import g50.model.Position;
import g50.model.element.Element;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.PacDot;
import g50.model.element.fixed.collectable.fruit.Fruit;
import g50.model.element.fixed.nonCollectable.Wall;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

import java.io.IOException;
import java.util.List;

public class GameMapViewer {
    private final GUI gui;
    private final GameMap gameMap;

    public GameMapViewer(GUI gui, GameMap gameMap){
        this.gui = gui;
        this.gameMap = gameMap;
    }

    public void draw() throws IOException {
        this.gui.clear();

        final PacManViewer pacManViewer = new PacManViewer(this.gui);
        final WallViewer wallViewer = new WallViewer(this.gui);
        final PacDotViewer pacDotViewer = new PacDotViewer(this.gui);
        final PowerPelletViewer powerPelletViewer = new PowerPelletViewer(this.gui);
        final FruitViewer fruitViewer = new FruitViewer(this.gui);

        final List<List<FixedElement>> map = this.gameMap.getMap();
        for (int line = 0; line < this.gameMap.getLines(); line++){
            for (int column = 0; column < this.gameMap.getColumns(); column++){
                Position position = new Position(column, line);
                Element element = map.get(line).get(column);

                // REWORK WITH A FACTORY/FILTER
                if (element instanceof Wall){
                    new WallViewer(this.gui).draw(position);
                }
                if (element instanceof PacDot) new PacDotViewer(this.gui).draw(position);
            }
        }

        new PacManViewer(this.gui).draw(this.gameMap.getPacman().getPosition());
        for (Ghost ghost : this.gameMap.getGhosts()){
            new GhostViewer(this.gui).draw(ghost.getPosition());
        }

        //this.gui.close();
    }
}

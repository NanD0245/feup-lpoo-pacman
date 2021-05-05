import g50.gui.GUI;
import g50.gui.LanternaGUI;
import g50.model.map.GameMap;
import g50.model.map.mapbuilder.DefaultGameMapBuilder;
import g50.model.map.mapbuilder.GameMapBuilder;
import g50.view.GameMapViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    public void init() throws URISyntaxException, FontFormatException {
        GameMapBuilder builder = new DefaultGameMapBuilder();
        GameMap gameMap;
        try {
            gameMap = builder.getBuild();
            System.out.println(gameMap.getMap());
            System.out.println(gameMap.getGhosts());
            System.out.println(gameMap.getPacman());
            GUI gui = new LanternaGUI(gameMap.getColumns(),gameMap.getLines());
            GameMapViewer viewer = new GameMapViewer(gui, gameMap);
            viewer.draw();
            gui.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

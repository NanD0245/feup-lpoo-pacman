import g50.gui.GUI;
import g50.gui.LanternaGUI;
import g50.model.map.GameMap;
import g50.model.map.mapbuilder.DefaultGameMapBuilder;
import g50.model.map.mapbuilder.GameMapBuilder;
import g50.view.GameMapViewer;

import java.io.IOException;

public class Game {

    public void init(){
        GameMapBuilder builder = new DefaultGameMapBuilder();
        GameMap gameMap;
        try {
            gameMap = builder.getBuild();
            System.out.println(gameMap.getMap());
            System.out.println(gameMap.getGhosts());
            System.out.println(gameMap.getPacman());
            GUI gui = new LanternaGUI(80,40);
            GameMapViewer viewer = new GameMapViewer(gui, gameMap);
            viewer.draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

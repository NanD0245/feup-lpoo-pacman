import g50.model.map.GameMap;
import g50.model.map.mapbuilder.DefaultGameMapBuilder;
import g50.model.map.mapbuilder.GameMapBuilder;

import java.io.IOException;

public class Game {

    public void init(){
        GameMapBuilder builder = new DefaultGameMapBuilder();
        GameMap gameMap;
        try{
            gameMap = builder.getBuild();
            System.out.println(gameMap.getMap());
            System.out.println(gameMap.getGhosts());
            System.out.println(gameMap.getPacman());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

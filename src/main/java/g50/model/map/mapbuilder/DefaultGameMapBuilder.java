package g50.model.map.mapbuilder;

import g50.model.map.GameMap;

import java.io.IOException;

public class DefaultGameMapBuilder extends GameMapBuilder {
    public DefaultGameMapBuilder(){
        super("src/main/resources/maps/default.txt");
    }
}

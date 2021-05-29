package g50.model.map.mapbuilder;

import g50.model.map.GameMap;
import g50.model.menu.Menu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DefaultGameMapBuilder extends GameMapBuilder {
    public DefaultGameMapBuilder(){
        super("src/main/resources/maps/default.txt");
    }
}

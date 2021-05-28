package g50.model.map.mapbuilder;

import g50.model.map.GameMap;
import g50.model.menu.Menu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DefaultGameMapBuilder extends GameMapBuilder {

    private static final String defaultFilename = "src/main/resources/maps/default.txt";

    public DefaultGameMapBuilder(){
        super(defaultFilename);
    }
}

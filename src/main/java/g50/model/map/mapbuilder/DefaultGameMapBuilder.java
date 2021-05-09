package g50.model.map.mapbuilder;

import g50.model.map.GameMap;

import java.io.IOException;

public class DefaultGameMapBuilder extends GameMapBuilder {

    private static final String defaultFilename = "src/main/resources/maps/default.txt";

    public DefaultGameMapBuilder(){
        super(defaultFilename);
    }
}

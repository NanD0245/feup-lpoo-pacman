package g50.model.map.mapbuilder;

import g50.model.map.GameMap;

import java.io.IOException;

public interface GameMapBuilder {
    public GameMap getBuild() throws IOException;
}

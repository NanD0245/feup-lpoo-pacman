package g50.model.map.mapbuilder;

import g50.model.map.GameMap;

import java.io.IOException;

public class DefaultGameMapBuilder implements GameMapBuilder {

    private static final String defaultFilename = "src/main/resources/maps/default.txt";
    private static final FileGameMapBuilder builder = new FileGameMapBuilder(defaultFilename);

    @Override
    public GameMap getBuild() throws IOException {
        return builder.getBuild();
    }
}

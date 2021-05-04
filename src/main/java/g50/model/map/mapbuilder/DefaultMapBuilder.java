package g50.model.map.mapbuilder;

import g50.model.element.fixed.FixedElement;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;

import java.io.IOException;
import java.util.List;

public class DefaultMapBuilder implements MapBuilder {

    private static final String defaultFilename = "src/main/resources/maps/default.txt";
    private static final FileMapBuilder builder = new FileMapBuilder(defaultFilename);

    @Override
    public List<List<FixedElement>> getBuild(PacMan pacman, List<Ghost> ghosts) throws IOException {
        return builder.getBuild(pacman, ghosts);
    }
}

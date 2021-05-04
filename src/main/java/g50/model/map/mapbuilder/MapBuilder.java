package g50.model.map.mapbuilder;

import g50.model.element.Element;
import g50.model.element.fixed.FixedElement;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface MapBuilder{
    public List<List<FixedElement>> getBuild(PacMan pacman, List<Ghost> ghosts) throws IOException;
}

package g50.model.map;

import g50.model.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.nonCollectable.Target;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.mapbuilder.FileGameMapBuilder;
import g50.model.map.mapbuilder.GameMapBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameMapTest {
    @Test
    public void mapIntegrity() throws IOException {
        List<List<FixedElement>> newList = new ArrayList<>();
        newList.add(new ArrayList<>());

        List<Ghost> ghosts = new ArrayList<>();

        Ghost newGhost = new Ghost("Test", 0, 0, Orientation.UP, new Target(0, 0, "Test"));

        ghosts.add(newGhost);

        GameMap map = new GameMap(newList, ghosts, new PacMan(1, 1));

        Assertions.assertEquals(map.getPacman().getPosition(), new Position(1,1));
        Assertions.assertEquals(map.getGhosts().size(), 1);
        Assertions.assertEquals(map.getGhosts().get(0).getOrientation(), Orientation.UP);
        Assertions.assertEquals(map.getGhosts().get(0).getName(), "Test");
        Assertions.assertEquals(map.getMap().size(), 1);
    }
}

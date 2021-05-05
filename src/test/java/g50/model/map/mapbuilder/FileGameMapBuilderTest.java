package g50.model.map.mapbuilder;

import g50.model.Position;
import g50.model.map.GameMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;

public class FileGameMapBuilderTest {

    @Test
    public void wrongPathFile() throws IOException {
        Assertions.assertThrows(IOException.class, () -> {final GameMap map = new FileGameMapBuilder("src/main/resources/maps/nonexistent.txt").getBuild();});

    }

    @Test
    public void mapIntegrity() throws IOException {
        GameMapBuilder mapBuilder = new FileGameMapBuilder("src/main/resources/maps/test.txt");
        GameMap map = mapBuilder.getBuild();
        Assertions.assertEquals(map.getMap().size(), 4);
        Assertions.assertEquals(map.getMap().get(0).size(), 4);
        Assertions.assertEquals(map.getGhosts().get(0).getName(), "Blinky");
        Assertions.assertEquals(map.getGhosts().size(), 2);
        Assertions.assertEquals(map.getPacman().getPosition(), new Position(2,3));
    }
}

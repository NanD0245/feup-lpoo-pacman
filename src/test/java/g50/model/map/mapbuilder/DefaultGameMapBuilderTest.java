package g50.model.map.mapbuilder;

import g50.model.element.Position;
import g50.model.map.GameMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DefaultGameMapBuilderTest {
    @Test
    public void mapIntegrity() throws IOException {
        GameMapBuilder mapBuilder = new FileGameMapBuilder("src/main/resources/maps/default.txt");
        GameMap map = mapBuilder.getBuild();
        Assertions.assertEquals(map.getMap().size(), 35);
        Assertions.assertEquals(map.getMap().get(0).size(), 28);
        Assertions.assertEquals(map.getGhosts().get(2).getName(), "Inky");
        Assertions.assertEquals(map.getGhosts().size(), 4);
        Assertions.assertEquals(map.getPacman().getPosition(), new Position(13,25));
    }
}

package g50.model.element.movable.ghost.strategy;

import g50.model.element.Position;
import g50.model.element.fixed.noncollectable.Target;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.BlinkyGhost;
import g50.model.element.movable.ghost.ClydeGhost;
import g50.model.element.movable.ghost.InkyGhost;
import g50.model.element.movable.ghost.PinkyGhost;
import g50.model.map.GameMap;
import g50.model.map.mapbuilder.FileGameMapBuilder;
import g50.states.GhostState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ClydeStrategyTest {
    private Position p;
    private GameMap gameMap;

    @BeforeEach
    public void initTest() throws IOException {
        int x = 10;
        int y = 15;
        p = new Position(x, y);
        gameMap = new FileGameMapBuilder("src/main/resources/maps/default.txt").getBuild();
    }

    @Test
    public void testNextOrientationCall(){
        ClydeStrategy clydeStrategy = new ClydeStrategy();
        ClydeGhost clydeGhost = new ClydeGhost("clyde", new Position(12, 25), Orientation.DOWN, new Target(new Position(5, 5), "target"));;

        gameMap.getPacman().setPosition(new Position(15, 25));

        Assertions.assertEquals(clydeStrategy.inChase(gameMap, clydeGhost), Orientation.LEFT);

        gameMap.getPacman().setPosition(new Position(21, 25));

        Assertions.assertEquals(clydeStrategy.inChase(gameMap, clydeGhost), Orientation.RIGHT);

    }
}
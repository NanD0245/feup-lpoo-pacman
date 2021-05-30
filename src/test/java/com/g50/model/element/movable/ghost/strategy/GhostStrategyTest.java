package com.g50.model.element.movable.ghost.strategy;

import com.g50.model.element.Position;
import com.g50.model.element.fixed.noncollectable.Target;
import com.g50.model.element.movable.Orientation;
import com.g50.model.element.movable.ghost.*;
import com.g50.model.map.GameMap;
import com.g50.model.map.mapbuilder.FileGameMapBuilder;
import com.g50.states.GhostState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

public class GhostStrategyTest {

    private Position p;
    private GameMap gameMap;
    private BlinkyGhost blinkyGhost;

    @BeforeEach
    public void initTest() throws IOException {
        int x = 10;
        int y = 15;
        p = new Position(x, y);
        blinkyGhost = new BlinkyGhost("blinky", new Position(13, 13), Orientation.LEFT, new Target(new Position(5, 5), "target"));
        gameMap = new FileGameMapBuilder("src/main/resources/maps/default.txt").getBuild();
    }

    @Test
    public void testGetOrientationTo() {
        PinkyGhost pinkyGhost = new PinkyGhost("pinky", new Position(3, 1), Orientation.DOWN, null);

        Assertions.assertEquals(
                pinkyGhost.getStrategy().getOrientationTo(
                        pinkyGhost, Arrays.asList(
                                Orientation.RIGHT,
                                Orientation.DOWN,
                                Orientation.LEFT,
                                Orientation.UP),
                        new Position(2, 1)),
                Orientation.LEFT);

        ClydeGhost clydeGhost = new ClydeGhost("clyde", new Position(5, 5), Orientation.DOWN, null);

        Assertions.assertEquals(
                clydeGhost.getStrategy().getOrientationTo(
                        pinkyGhost, Arrays.asList(),
                        new Position(2, 1)),
                null);

        Assertions.assertEquals(
                clydeGhost.getStrategy().getOrientationTo(
                        pinkyGhost, Arrays.asList(
                                Orientation.DOWN,
                                Orientation.UP,
                                Orientation.LEFT
                        ),
                        new Position(17, 25)),
                Orientation.DOWN);

    }

    @Test
    public void testNextOrientationCall(){
        BlinkyStrategy blinkyStrategy = new BlinkyStrategy();

        Assertions.assertEquals(blinkyStrategy.getNextOrientation(gameMap,blinkyGhost, GhostState.CHASE), Orientation.LEFT);
        Assertions.assertEquals(blinkyStrategy.getNextOrientation(gameMap,blinkyGhost, GhostState.DEAD), Orientation.LEFT);

        blinkyGhost.move(Orientation.LEFT, 25, 25);

        Assertions.assertEquals(blinkyStrategy.getNextOrientation(gameMap,blinkyGhost, GhostState.SCATTER), Orientation.UP);

        blinkyGhost.setOrientation(Orientation.UP);

        Assertions.assertEquals(blinkyStrategy.getNextOrientation(gameMap,blinkyGhost, GhostState.LEAVING_CAGE), Orientation.RIGHT);

        blinkyGhost.setPosition(new Position(13, 15));

        Assertions.assertEquals(blinkyStrategy.getNextOrientation(gameMap,blinkyGhost, GhostState.IN_CAGE), Orientation.DOWN);

        gameMap.getPacman().setPosition(new Position(3, 27));

        blinkyGhost.setPosition(new Position(6, 22));
        blinkyGhost.setOrientation(Orientation.LEFT);

        Assertions.assertEquals(blinkyStrategy.getNextOrientation(gameMap,blinkyGhost, GhostState.CHASE), Orientation.DOWN);

        blinkyGhost.setOrientation(Orientation.RIGHT);
        Assertions.assertEquals(blinkyStrategy.getBestOrientation(gameMap, blinkyGhost, new Position(12, 28)), Orientation.DOWN);

        blinkyGhost.setPosition(new Position(13, 13));
        blinkyGhost.setOrientation(Orientation.DOWN);

        Orientation result = blinkyStrategy.getNextOrientation(gameMap,blinkyGhost, GhostState.FRIGHTENED);
        Assertions.assertTrue(result == Orientation.LEFT || result == Orientation.RIGHT);

    }

}

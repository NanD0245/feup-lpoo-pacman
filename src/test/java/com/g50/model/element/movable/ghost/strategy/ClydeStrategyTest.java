package com.g50.model.element.movable.ghost.strategy;

import com.g50.model.element.Position;
import com.g50.model.element.fixed.noncollectable.Target;
import com.g50.model.element.movable.Orientation;
import com.g50.model.element.movable.ghost.ClydeGhost;
import com.g50.model.map.GameMap;
import com.g50.model.map.mapbuilder.FileGameMapBuilder;
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

        gameMap.getPacman().setPosition(new Position(15, 23));

        Assertions.assertEquals(clydeStrategy.inChase(gameMap, clydeGhost), Orientation.LEFT);

        gameMap.getPacman().setPosition(new Position(21, 25));

        Assertions.assertEquals(clydeStrategy.inChase(gameMap, clydeGhost), Orientation.RIGHT);

    }
}
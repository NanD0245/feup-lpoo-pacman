package com.g50.model.map;

import com.g50.model.element.Position;
import com.g50.model.element.fixed.FixedElement;
import com.g50.model.element.fixed.noncollectable.Target;
import com.g50.model.element.movable.Orientation;
import com.g50.model.element.movable.PacMan;
import com.g50.model.element.movable.ghost.BlinkyGhost;
import com.g50.model.element.movable.ghost.Ghost;
import com.g50.model.map.mapbuilder.DefaultGameMapBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameMapTest {

    private GameMap map1, map2;

    @BeforeEach
    public void initTest() throws IOException {
        List<List<FixedElement>> newList = new ArrayList<>();
        newList.add(new ArrayList<>());

        List<Ghost> ghosts = new ArrayList<>();

        Ghost newGhost = new BlinkyGhost("Test", new Position(0, 0), Orientation.UP, new Target(new Position(0, 0), "Test"));

        ghosts.add(newGhost);

        map1 = new GameMap(newList, ghosts, new PacMan(new Position(1, 1)), new Position(1,1), new Position(1,2));
        map2 = new DefaultGameMapBuilder().getBuild();
    }
    @Test
    public void mapIntegrity() throws IOException {

        Assertions.assertEquals(map1.getPacman().getPosition(), new Position(1,1));
        Assertions.assertEquals(map1.getGhosts().size(), 1);
        Assertions.assertEquals(map1.getGhosts().get(0).getOrientation(), Orientation.UP);
        Assertions.assertEquals(map1.getGhosts().get(0).getName(), "Test");
        Assertions.assertEquals(map1.getMap().size(), 1);
        Assertions.assertEquals(map1.getFruitPosition(), new Position(1, 2));

    }

    @Test
    public void mapReset(){
        for(Ghost ghost: map2.getGhosts())
            ghost.move(Orientation.DOWN, 25, 25);

        map2.getPacman().setPosition(new Position(50, 50));
        map2.getPacman().setOrientation(Orientation.DOWN);

        Assertions.assertEquals(map2.getGhosts().get(0).getPosition(), new Position(map2.getGhosts().get(0).getSpawnPosition().getX(), map2.getGhosts().get(0).getSpawnPosition().getY() + 1));
        Assertions.assertEquals(map2.getPacman().getPosition(), new Position(50, 50));
        Assertions.assertEquals(map2.getPacman().getOrientation(), Orientation.DOWN);

        map2.resetPositions();

        Assertions.assertEquals(map2.getGhosts().get(0).getPosition(), map2.getGhosts().get(0).getSpawnPosition());
        Assertions.assertEquals(map2.getPacman().getPosition(),  map2.getPacman().getSpawnPosition());
        Assertions.assertEquals(map2.getPacman().getOrientation(), Orientation.LEFT);
    }

    @Test
    public void cantFindPath(){
        Orientation res = map2.getOrientationOfShortestPath(new Position(12,13), new Position(11,13), Orientation.DOWN);

        Assertions.assertEquals(res, Orientation.LEFT);
    }
}

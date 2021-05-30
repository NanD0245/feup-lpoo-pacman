package com.g50.model.element.movable;

import com.g50.model.element.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MovableElementTest {

    @Test
    public void moveTest(){
        PacMan pacman = new PacMan(new Position(1,1));
        pacman.move(Orientation.UP, 5, 5);
        Assertions.assertEquals(pacman.getPosition().getX(), 1);
        Assertions.assertEquals(pacman.getPosition().getY(), 0);

        pacman.move(Orientation.DOWN, 5, 5);
        Assertions.assertEquals(pacman.getPosition().getX(), 1);
        Assertions.assertEquals(pacman.getPosition().getY(), 1);

        pacman.move(Orientation.LEFT, 5, 5);
        Assertions.assertEquals(pacman.getPosition().getX(), 0);
        Assertions.assertEquals(pacman.getPosition().getY(), 1);

        pacman.move(Orientation.LEFT, 5, 5);
        Assertions.assertEquals(pacman.getPosition().getX(), 4);
        Assertions.assertEquals(pacman.getPosition().getY(), 1);

        pacman.move(Orientation.RIGHT, 5, 5);
        Assertions.assertEquals(pacman.getPosition().getX(), 0);
        Assertions.assertEquals(pacman.getPosition().getY(), 1);

        pacman.move(Orientation.RIGHT, 5, 5);
        Assertions.assertEquals(pacman.getPosition().getX(), 1);
        Assertions.assertEquals(pacman.getPosition().getY(), 1);
    }
}

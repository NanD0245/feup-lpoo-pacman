package com.g50.model;

import com.g50.model.element.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    private Position p;

    @BeforeEach
    public void initTest() {
        int x = 10;
        int y = 15;
        p = new Position(x, y);
    }

    @Test
    public void equalPositionTest() {
        assertFalse(p.equals(new Position(11, 15)));
        assertTrue(p.equals(new Position(10,15)));
    }

    @Test
    public void getPositionTest() {
        assertEquals(p.getUp(), new Position(10,14));
        assertEquals(p.getDown(), new Position(10,16));
        assertEquals(p.getLeft(), new Position(9,15));
        assertEquals(p.getRight(), new Position(11,15));
    }

    @Test
    public void randomPositionTest() {
        Position random = p.getRandom();
        assertTrue(random.equals(new Position(10,14)) ||
                random.equals(new Position(10,16)) ||
                random.equals(new Position(9,15)) ||
                random.equals(new Position(11,15)));
    }
}

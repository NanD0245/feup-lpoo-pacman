package g50.model.element.movable.ghost.strategy;

import g50.model.element.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GhostStrategyTest {

    private Position p;

    @BeforeEach
    public void initTest() {
        int x = 10;
        int y = 15;
        p = new Position(x, y);
    }

    @Test
    public void equalPositionTest() {

    }
}

package g50.model.element.movable;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrientationTest {
    Orientation up, down, left, right;

    @BeforeEach
    public void initTest() {
        this.up = Orientation.UP;
        this.down = Orientation.DOWN;
        this.left = Orientation.LEFT;
        this.right = Orientation.RIGHT;
    }

    @Test
    public void getOppositeTest() {
        assertEquals(up.getOpposite() ,Orientation.DOWN);
        assertEquals(down.getOpposite() ,Orientation.UP);
        assertEquals(left.getOpposite() ,Orientation.RIGHT);
        assertEquals(right.getOpposite() ,Orientation.LEFT);
    }
}

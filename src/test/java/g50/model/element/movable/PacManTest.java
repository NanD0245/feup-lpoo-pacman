package g50.model.element.movable;

import g50.model.element.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PacManTest {
    private PacMan pacMan;

    @BeforeEach
    public void initTest() {
        this.pacMan = new PacMan(new Position(0,0));
    }

    @Test
    public void isAliveTest() {
        pacMan.decreaseLives();
        pacMan.decreaseLives();
        assertTrue(pacMan.isAlive());
        assertEquals(pacMan.getLives(), 1);
        pacMan.increaseLives();
        pacMan.decreaseLives();
        assertTrue(pacMan.isAlive());
        pacMan.decreaseLives();
        assertFalse(pacMan.isAlive());
    }
}

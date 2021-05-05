package g50.model.element.movable;

import g50.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PacManTest {
    private PacMan pacMan;

    @BeforeEach
    public void initTest() {
        this.pacMan = new PacMan(0,0);
    }

    @Test
    public void isAliveTest() {
        pacMan.decreaseLives();
        pacMan.decreaseLives();
        assertTrue(pacMan.isAlive());
        pacMan.increaseLives();
        pacMan.decreaseLives();
        assertTrue(pacMan.isAlive());
        pacMan.decreaseLives();
        assertFalse(pacMan.isAlive());
    }
}

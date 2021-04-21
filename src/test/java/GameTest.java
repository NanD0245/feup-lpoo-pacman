import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    public void testInit(){
        Game pacman = new Game();

        Assertions.assertTrue(pacman.init());
    }
}

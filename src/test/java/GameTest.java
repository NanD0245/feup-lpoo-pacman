import g50.model.map.Map;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class GameTest {

    @Test
    public void testInit(){
        Game pacman = new Game();
        Assertions.assertDoesNotThrow(pacman::init);
    }
}

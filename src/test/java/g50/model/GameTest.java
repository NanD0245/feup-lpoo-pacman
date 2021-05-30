package g50.model;
import g50.model.element.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void changeScore() throws IOException {
        Game game = new Game(0,1);
        game.incrementScore(500);

        Assertions.assertEquals(game.getScore(), 500);
        Assertions.assertNotEquals(game.getHighScore(), 500);

        game.resetScore();
        Assertions.assertNotEquals(game.getScore(), 500);
        Assertions.assertEquals(game.getScore(), 0);

        Game game2 = new Game(500, 1000, 10);

        Assertions.assertEquals(game2.getScore(), 10);
        Assertions.assertEquals(game2.getHighScore(), 500);
        Assertions.assertEquals(game2.getLevel().getLevelNumber(), 1000);
    }
}

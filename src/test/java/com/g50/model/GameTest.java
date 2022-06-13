package com.g50.model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.IOException;

public class GameTest {

    @Test
    public void changeScore() throws IOException {
        Game game = new Game(null, 0,1);
        game.incrementScore(500);

        Assertions.assertEquals(game.getScore(), 500);
        Assertions.assertNotEquals(game.getHighScore(), 500);

        game.resetScore();
        Assertions.assertNotEquals(game.getScore(), 500);
        Assertions.assertEquals(game.getScore(), 0);

        Game game2 = new Game(null,500, 1000, 10);

        Assertions.assertEquals(game2.getScore(), 10);
        Assertions.assertEquals(game2.getHighScore(), 500);
        Assertions.assertEquals(game2.getLevel().getLevelNumber(), 1000);
    }
}

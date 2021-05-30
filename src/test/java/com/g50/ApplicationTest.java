package com.g50;

import com.g50.Application;
import com.g50.controller.GameController;
import com.g50.gui.LanternaGUI;
import com.g50.model.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.timeout;

class ApplicationTest {

    Application app;

    @BeforeEach
    public void initTest() throws FileNotFoundException {
        app = new Application(Mockito.mock(LanternaGUI.class));
    }

    @Test
    public void testHighscore() throws FileNotFoundException {
        app.setHighScore(app.readHighScore("src/test/resources/highscore/test_highscore.txt"));
        Assertions.assertEquals(app.getHighScore(), 999);
    }

    @Test
    public void testHighScoreSave() throws IOException {
        app.setHighScore(app.readHighScore("src/test/resources/highscore/test_highscore.txt") + 1);
        app.writeHighScore("src/test/resources/highscore/test_highscore_write.txt");
        app.setHighScore(app.readHighScore("src/test/resources/highscore/test_highscore_write.txt"));
        Assertions.assertEquals(app.getHighScore(), 1000);
    }

    @Test
    public void testScoreChange() throws IOException {
        app.setHighScore(app.readHighScore("src/test/resources/highscore/test_highscore.txt"));
        app.setGame(new Game(null, app.getHighScore(), 1));
        app.getGame().incrementScore(500);
        app.checkHighScore();
        Assertions.assertEquals(app.getHighScore(), 999);
        app.getGame().incrementScore(500);
        app.checkHighScore();
        Assertions.assertEquals(app.getHighScore(), 1000);
    }

    @Test
    public void checkUpdateFrequency() throws InterruptedException, IOException {
        GameController gameController = Mockito.mock(GameController.class);
        app.setController(gameController);
        Mockito.doNothing().when(gameController).update(isA(Application.class), isA(Integer.class));
        app.setUp(1);
        Mockito.verify(gameController, timeout(1100).atLeastOnce()).update(isA(Application.class), isA(Integer.class));
        app.terminate();
    }
}

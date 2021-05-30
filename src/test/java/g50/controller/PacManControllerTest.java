package g50.controller;

import g50.Application;
import g50.gui.GUI;
import g50.model.Game;
import g50.model.Level;
import g50.model.element.Position;
import g50.model.element.movable.Orientation;
import g50.model.map.GameMap;
import g50.model.map.mapbuilder.FileGameMapBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PacManControllerTest {
    private Game game;
    private Application application;

    @BeforeEach
    private void initEntities() throws IOException {
        GameMap gameMap = new FileGameMapBuilder("src/main/resources/maps/small.txt").getBuild();
        game = Mockito.mock(Game.class);
        Mockito.when(game.getGameMap()).thenReturn(gameMap);
        Mockito.when(game.getLevel()).thenReturn(new Level(1));
        application = Mockito.mock(Application.class);
        Mockito.when(application.getFrameRate()).thenReturn(1);
    }

    @Test
    public void possibleDirectionChange() {
        GameController gameController = new GameController(null, game);
        PacManController pacManController = new PacManController(gameController);
        pacManController.addPendingKBDAction(GUI.KBD_ACTION.DOWN);
        pacManController.update(application, 0);
        assertEquals(gameController.getModel().getGameMap().getPacman().getPosition(), new Position(3,2));
    }

    @Test
    public void impossibleDirectionChange() {
        GameController gameController = new GameController(null, game);
        PacManController pacManController = new PacManController(gameController);
        pacManController.addPendingKBDAction(GUI.KBD_ACTION.RIGHT);
        pacManController.update(application, 0);
        assertEquals(gameController.getModel().getGameMap().getPacman().getPosition(), new Position(4,1));
        pacManController.addPendingKBDAction(GUI.KBD_ACTION.RIGHT);
        pacManController.update(application, 0);
        assertEquals(gameController.getModel().getGameMap().getPacman().getPosition(), new Position(4,1));
    }

    @Test
    public void possibleBufferedDirectionChange() {
        GameController gameController = new GameController(null, game);
        PacManController pacManController = new PacManController(gameController);
        pacManController.getModel().setOrientation(Orientation.LEFT);
        pacManController.update(application, 0);
        assertEquals(gameController.getModel().getGameMap().getPacman().getPosition(), new Position(2,1));
        pacManController.addPendingKBDAction(GUI.KBD_ACTION.DOWN);
        pacManController.update(application, 0);
        assertEquals(gameController.getModel().getGameMap().getPacman().getPosition(), new Position(1,1));
        pacManController.update(application, 0);
        assertEquals(gameController.getModel().getGameMap().getPacman().getPosition(), new Position(1,2));
    }

    @Test
    public void impossibleBufferedDirectionChange() {
        GameController gameController = new GameController(null, game);
        PacManController pacManController = new PacManController(gameController);
        pacManController.getModel().setOrientation(Orientation.LEFT);
        pacManController.update(application, 0);
        assertEquals(gameController.getModel().getGameMap().getPacman().getPosition(), new Position(2,1));
        pacManController.addPendingKBDAction(GUI.KBD_ACTION.UP);
        pacManController.update(application, 0);
        assertEquals(gameController.getModel().getGameMap().getPacman().getPosition(), new Position(1,1));
        pacManController.update(application, 0);
        assertEquals(gameController.getModel().getGameMap().getPacman().getPosition(), new Position(1,1));
    }
}
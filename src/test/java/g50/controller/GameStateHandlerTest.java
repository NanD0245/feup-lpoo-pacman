package g50.controller;

import g50.model.Level;
import g50.states.GameState;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;

class GameStateHandlerTest {
    Level level;
    GameStateHandler gameStateHandler;
    @BeforeEach
    void mockLevel(){
        level = Mockito.mock(Level.class);
        Mockito.when(level.getGameStateIntervals()).thenReturn(Arrays.asList(1,2,3));
        Mockito.when(level.getGhostFrightenedTime()).thenReturn(2);
        gameStateHandler = new GameStateHandler(level);
    }

    @Test
    void updateState() {
        gameStateHandler.update(1);
        Assertions.assertEquals(gameStateHandler.getState(), GameState.GAME_CHASE);
        gameStateHandler.update(1);
        gameStateHandler.update(1);
        Assertions.assertEquals(gameStateHandler.getState(), GameState.GAME_SCATTER);
    }

    @Test
    void frightenedState(){
        gameStateHandler.update(1);
        Assertions.assertEquals(gameStateHandler.getState(), GameState.GAME_CHASE);
        gameStateHandler.setCurrentState(GameState.GAME_FRIGHTENED);
        Assertions.assertEquals(gameStateHandler.getState(), GameState.GAME_FRIGHTENED);
        gameStateHandler.update(1);
        Assertions.assertEquals(gameStateHandler.getState(), GameState.GAME_FRIGHTENED);
        gameStateHandler.update(1);
        Assertions.assertEquals(gameStateHandler.getState(), GameState.GAME_SCATTER);
        gameStateHandler.update(1);
        gameStateHandler.update(1);
        gameStateHandler.update(1);
        Assertions.assertEquals(gameStateHandler.getState(), GameState.GAME_CHASE);
    }
}

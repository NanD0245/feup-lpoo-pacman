package com.g50.controller;

import com.g50.model.Game;
import com.g50.model.Level;
import com.g50.model.element.Position;
import com.g50.model.element.fixed.FixedElement;
import com.g50.model.element.fixed.noncollectable.EmptySpace;
import com.g50.model.element.movable.ghost.ClydeGhost;
import com.g50.model.element.movable.ghost.PinkyGhost;
import com.g50.model.map.GameMap;
import com.g50.model.map.mapbuilder.FileGameMapBuilder;
import com.g50.states.GameState;
import com.g50.states.GhostState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class GameControllerTest {
    private GameController gameController;

    @BeforeEach
    void mockLevel() {
        Level level = Mockito.mock(Level.class);
        Mockito.when(level.getGameStateIntervals()).thenReturn(Arrays.asList(1,2,3));
        Mockito.when(level.getGhostFrightenedTime()).thenReturn(2);
    }

    @BeforeEach
    void mockGame() throws IOException {
        GameMap mockMap = new FileGameMapBuilder("src/main/resources/maps/small.txt").getBuild();
        Game game = new Game(mockMap, 0, 1);
        gameController = new GameController(null, game);
    }

    private int ghostsDotLimitSum() {
        int total = 0;
        for (GhostController ghostController : gameController.getGhostsController()){
            total += ghostController.getModel().getStrategy().getDotLimit();
        }
        return total;
    }

    private PinkyGhost getPinky() {
        for (GhostController ghostController : gameController.getGhostsController()){
            if (ghostController.getModel() instanceof PinkyGhost){
                return (PinkyGhost) ghostController.getModel();
            }
        }
        return null;
    }

    private ClydeGhost getClyde() {
        for (GhostController ghostController : gameController.getGhostsController()){
            if (ghostController.getModel() instanceof ClydeGhost){
                return (ClydeGhost) ghostController.getModel();
            }
        }
        return null;
    }

    private void forcePinkyToLeaveCage() {
        Objects.requireNonNull(getPinky()).setState(GhostState.LEAVING_CAGE);
    }

    @Test
    void consumeMapElementDecreaseDots() {
        int beforeDots = ghostsDotLimitSum();
        forcePinkyToLeaveCage();
        gameController.consumeMapElement(new Position(2,3));
        Assertions.assertNotEquals(gameController.getGameStateHandler().getState(), GameState.GAME_FRIGHTENED);
        Assertions.assertEquals(gameController.getModel().getGameMap().getElement(new Position(2,3)),
                new EmptySpace(new Position(2,3)));
        int afterDots = ghostsDotLimitSum();
        Assertions.assertTrue(beforeDots > afterDots);
    }

    @Test
    void consumeMapElementFrightenGame() {
        gameController.consumeMapElement(new Position(1,2));
        Assertions.assertEquals(gameController.getGameStateHandler().getState(), GameState.GAME_FRIGHTENED);
        Assertions.assertEquals(gameController.getModel().getGameMap().getElement(new Position(1,2)),
                new EmptySpace(new Position(1,2)));
    }

    @Test
    void checkPacmanGhostCollisionChase() throws InterruptedException {
        ClydeGhost clyde = getClyde();
        assert clyde != null;
        clyde.moveUp(100);
        gameController.checkPacmanGhostCollision();
        Assertions.assertEquals(3, gameController.getPacManController().getModel().getLives());
        gameController.getPacManController().getModel().moveDown(100);
        gameController.checkPacmanGhostCollision();
        Assertions.assertEquals(2, gameController.getPacManController().getModel().getLives());
    }

    @Test
    void checkPacmanGhostCollisionFrightened() throws InterruptedException {
        ClydeGhost clyde = getClyde();
        assert clyde != null;
        clyde.setState(GhostState.FRIGHTENED);
        clyde.moveUp(100);
        gameController.checkPacmanGhostCollision();
        Assertions.assertEquals(3, gameController.getPacManController().getModel().getLives());
        gameController.getPacManController().getModel().moveDown(100);
        gameController.checkPacmanGhostCollision();
        Assertions.assertEquals(3, gameController.getPacManController().getModel().getLives());
        Assertions.assertTrue(gameController.getModel().getScore() > 0);
    }

    @Test
    void isGameOver() {
        Assertions.assertTrue(gameController.getPacManController().getModel().isAlive());
        gameController.getPacManController().getModel().decreaseLives();
        gameController.getPacManController().getModel().decreaseLives();
        Assertions.assertTrue(gameController.getPacManController().getModel().isAlive());
        gameController.getPacManController().getModel().decreaseLives();
        Assertions.assertFalse(gameController.getPacManController().getModel().isAlive());
        Assertions.assertTrue(gameController.isGameOver());
    }

    @Test
    void isNextLevel() {
        Assertions.assertFalse(gameController.isNextLevel());
        for (List<FixedElement> l : gameController.getModel().getGameMap().getMap()){
            for (FixedElement e : l){
                gameController.consumeMapElement(e.getPosition());
            }
        }
        Assertions.assertTrue(gameController.isNextLevel());
    }
}

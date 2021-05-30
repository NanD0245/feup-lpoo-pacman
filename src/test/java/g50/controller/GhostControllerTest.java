package g50.controller;

import g50.model.element.Position;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.BlinkyGhost;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;
import g50.model.map.mapbuilder.FileGameMapBuilder;
import g50.states.GameState;
import g50.states.GhostState;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GhostControllerTest {
    private GameMap getMockMap() throws IOException {
        return new FileGameMapBuilder("src/main/resources/maps/small.txt").getBuild();
    }

    @Test
    public void blinkyStrategyCall() throws IOException {
        BlinkyGhost blinky = new BlinkyGhost("Blinky", new Position(1,1), Orientation.LEFT, null);
        GhostController ghostController = new GhostController(blinky);
        ghostController.updatePositions(getMockMap());
        assertEquals(1, blinky.getPosition().getX());
        assertEquals(2, blinky.getPosition().getY());
    }

    private GameController getMockGameController(){
        GameStateHandler gameStateHandler = Mockito.mock(GameStateHandler.class);
        Mockito.when(gameStateHandler.getState()).thenReturn(GameState.GAME_SCATTER);
        GameController gameController = Mockito.mock(GameController.class);
        Mockito.when(gameController.getGameStateHandler()).thenReturn(gameStateHandler);
        return gameController;
    }

    @Test
    public void stateChangeDeadInSpawnPosition() {
        Ghost ghost = new BlinkyGhost("Blinky", new Position(1,1), Orientation.UP, null);
        ghost.setState(GhostState.DEAD);
        GhostController ghostController = new GhostController(ghost);
        ghostController.updateGhostState(getMockGameController());
        assertEquals(ghost.getState(), GhostState.IN_CAGE);
    }

    @Test
    public void stateChangeGameStateUpdate() {
        Ghost ghost = new BlinkyGhost("Blinky", new Position(2,2), Orientation.UP, null);
        ghost.setState(GhostState.FRIGHTENED);
        GhostController ghostController = new GhostController(ghost);
        ghostController.updateGhostState(getMockGameController());
        assertEquals(ghost.getState(), GhostState.SCATTER);
    }
}

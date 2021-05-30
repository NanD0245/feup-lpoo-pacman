package g50.controller;

import g50.model.Game;
import g50.model.element.Position;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.BlinkyGhost;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;
import g50.model.map.mapbuilder.FileGameMapBuilder;
import g50.states.GameState;
import g50.states.GhostState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GhostControllerTest {
    private GameMap mockMap;
    private Game mockGame;

    @BeforeEach
    private void mockGame() throws IOException {
        mockMap = new FileGameMapBuilder("src/main/resources/maps/small.txt").getBuild();
        mockGame = Mockito.mock(Game.class);
        Mockito.when(mockGame.getGameMap()).thenReturn(mockMap);
    }

    @Test
    public void blinkyStrategyCall() throws IOException {
        BlinkyGhost blinky = new BlinkyGhost("Blinky", new Position(1,1), Orientation.LEFT, null);
        GhostController ghostController = new GhostController(blinky);
        ghostController.updatePositions(mockMap);
        assertEquals(1, blinky.getPosition().getX());
        assertEquals(2, blinky.getPosition().getY());
    }

    private GameController getMockGameController(){
        GameStateHandler gameStateHandler = Mockito.mock(GameStateHandler.class);
        Mockito.when(gameStateHandler.getState()).thenReturn(GameState.GAME_SCATTER);
        GameController gameController = Mockito.mock(GameController.class);
        Mockito.when(gameController.getGameStateHandler()).thenReturn(gameStateHandler);
        Mockito.when(gameController.getModel()).thenReturn(mockGame);
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
    public void stateChangeGameStateUpdate() throws IOException {
        Ghost ghost = new BlinkyGhost("Blinky", new Position(1,2), Orientation.UP, null);
        ghost.setState(GhostState.FRIGHTENED);
        Orientation currentOrientation = ghost.getOrientation();
        GhostController ghostController = new GhostController(ghost);
        GameController mockGameController = getMockGameController();
        ghostController.updateGhostState(mockGameController);
        assertEquals(ghost.getState(), GhostState.SCATTER);
        ghostController.updatePositions(mockMap);
        assertEquals(ghost.getOrientation(), currentOrientation.getOpposite());
    }

    @Test
    public void stateChangeReadyToEnterRegularState() throws IOException {
        Ghost ghost = new BlinkyGhost("Blinky", new Position(1,2), Orientation.UP, null);
        ghost.setState(GhostState.LEAVING_CAGE);
        GhostController ghostController = new GhostController(ghost);
        GameController mockGameController = getMockGameController();
        Game mockGame = mockGameController.getModel();
        Mockito.when(mockGame.getGameMap()).thenReturn(mockMap);
        mockMap.setGhostSpawnPosition(new Position(1,2));
        ghostController.updateGhostState(mockGameController);
        assertEquals(ghost.getState(), GhostState.SCATTER);
    }

    @Test
    public void bufferedOrientation() throws IOException {
        Ghost ghost = new BlinkyGhost("Blinky", new Position(1,2), Orientation.UP, null);
        GhostController ghostController = new GhostController(ghost);
        ghostController.updatePositions(mockMap);
        ghostController.setNextBufferedOrientation(Orientation.DOWN);
        ghostController.updatePositions(mockMap);
        assertEquals(ghost.getOrientation(), Orientation.DOWN);
    }
}

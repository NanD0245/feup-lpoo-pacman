package g50.controller;

import g50.model.Level;
import g50.states.GameState;
import java.util.ArrayList;
import java.util.List;

public class GameStateHandler {
    private GameState state;
    private final GameController gameController;
    private final List<Integer> times;
    private int frightenedFrames;
    private int elapsedFrames;
    private int defaultFrightenedTime;

    public GameStateHandler(GameController gameController, Level levelInfo){
        this.state = GameState.GAME_SCATTER;
        this.times = levelInfo.getGameStateIntervals();
        this.frightenedFrames = 0;
        this.elapsedFrames = 0;
        this.gameController = gameController;
        this.defaultFrightenedTime = levelInfo.getGhostFrightnedTime();
    }

    public void update(int framerate) {
        elapsedFrames++;
        int elapsedSeconds =  elapsedFrames/framerate;
        int frightnedSeconds = frightenedFrames/framerate;

        if(state.equals(GameState.GAME_FRIGHTENED))
            if(elapsedSeconds - frightnedSeconds < defaultFrightenedTime) return;

        GameState newState = GameState.GAME_SCATTER;

        int timeForState = elapsedSeconds;
        for(Integer value: this.times){
            if (value == Integer.MAX_VALUE || timeForState - value < 0){
                if (this.state != newState) setCurrentState(newState);
                return;
            }
            newState = newState == GameState.GAME_SCATTER ? GameState.GAME_CHASE : GameState.GAME_SCATTER;
            timeForState -= value;
        }
        if (this.state != newState) setCurrentState(newState);
    }

    public void setCurrentState(GameState newState){
        this.state = newState;
        if (newState == GameState.GAME_FRIGHTENED) frightenedFrames = elapsedFrames;
        for (GhostController ghostController: gameController.getGhostsController()){
            ghostController.reverseOrientation();
        }
    }

    public GameState getState() { return state; }

    public void resetCurrentState() {
        this.state = GameState.GAME_SCATTER;
    }
}

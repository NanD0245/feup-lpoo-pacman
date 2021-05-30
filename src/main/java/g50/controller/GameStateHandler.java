package g50.controller;

import g50.model.Level;
import g50.states.GameState;

import java.util.List;

public class GameStateHandler {
    private GameState state;
    private final List<Integer> times;
    private int frightenedFrames;
    private int elapsedFrames;
    private final int defaultFrightenedTime;

    public GameStateHandler(Level level) {
        this.state = GameState.GAME_SCATTER;
        this.times = level.getGameStateIntervals();
        this.frightenedFrames = 0;
        this.elapsedFrames = 0;
        this.defaultFrightenedTime = level.getGhostFrightenedTime();
    }

    public void update(int framerate) {
        elapsedFrames++;
        int elapsedSeconds =  elapsedFrames/framerate;
        int frightenedSeconds = frightenedFrames/framerate;
        if (state.equals(GameState.GAME_FRIGHTENED) && elapsedSeconds - frightenedSeconds < defaultFrightenedTime) return;

        GameState newState = GameState.GAME_SCATTER;
        int timeForState = elapsedSeconds;
        for (Integer value: this.times){
            if (timeForState - value < 0){
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
    }

    public GameState getState() { return state; }

    public void resetCurrentState() {
        this.state = GameState.GAME_SCATTER;
    }
}

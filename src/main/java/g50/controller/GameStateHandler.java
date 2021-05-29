package g50.controller;

import g50.states.GameState;
import java.util.ArrayList;
import java.util.List;

public class GameStateHandler {
    private GameState state;
    private GameController gameController;
    private List<Integer> times;
    private int frightenedTime;
    private int elapsedSeconds;
    private static int defaultFrightenedTime = 6;
    private List<Controller<?>> observers;

    public GameStateHandler(GameController gameController, List<Integer> timeIntervals){
        this.state = GameState.GAME_SCATTER;
        this.times = timeIntervals;
        this.observers = new ArrayList<>();
        this.frightenedTime = 0;
        this.elapsedSeconds = 0;
        this.gameController = gameController;
    }

    public void update(int frame, int framerate) {
        elapsedSeconds = frame/framerate;

        if(state.equals(GameState.GAME_FRIGHTENED))
            if(elapsedSeconds - frightenedTime < defaultFrightenedTime) return;

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
    }

    public void setCurrentState(GameState newState){
        this.state = newState;
        if (newState == GameState.GAME_FRIGHTENED) frightenedTime = elapsedSeconds;
        for (GhostController ghostController: gameController.getGhostsController()){
            ghostController.reverseOrientation();
        }
    }

    public GameState getState() { return state; }

    public void resetCurrentState() {
        this.state = GameState.GAME_SCATTER;
    }
}

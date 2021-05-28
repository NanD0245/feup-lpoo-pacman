package g50.states;

import g50.controller.Controller;
import g50.controller.GameController;
import g50.controller.ghost.GhostController;
import g50.model.Game;
import g50.model.element.movable.ghost.Ghost;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameStateHandler {
    private GameController gameController;
    private GameState state;
    private List<Integer> times;
    private int frightenedTime;
    private int elapsedSeconds;
    private static int defaultFrightenedTime = 6;
    private List<Controller<Ghost>> observers;

    public GameStateHandler(GameController gameController){
        this.state = GameState.GAME_SCATTER;
        this.times = Arrays.asList(7, 20, 7, 20, 5, 20, 5, Integer.MAX_VALUE);
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
            if (value == Integer.MAX_VALUE || timeForState - value < 0) {
                if (this.state != newState) setCurrentState(newState);
                return;
            }
            if (newState == GameState.GAME_SCATTER) newState = GameState.GAME_CHASE;
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
}

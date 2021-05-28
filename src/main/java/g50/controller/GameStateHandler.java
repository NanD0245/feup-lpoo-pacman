package g50.controller;

import g50.Application;
import g50.controller.states.GameState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GameStateHandler {
    private GameState state;
    private List<Integer> times;
    private int frightnedTime;
    private int elapsedSeconds;
    private static int defaultFrightnedTime = 6;
    private List<Controller> observers;

    public GameStateHandler(List<Integer> timeIntervals){
        this.state = GameState.GameScatter;
        this.times = timeIntervals;
        this.observers = new ArrayList<>();
        this.frightnedTime = 0;
        this.elapsedSeconds = 0;
    }

    public void update(int frame, int framerate) {
        elapsedSeconds = frame/framerate;

        if(state.equals(GameState.GameFrightned))
            if(elapsedSeconds - frightnedTime < defaultFrightnedTime) return;

        GameState newState = GameState.GameScatter;

        int timeForState = elapsedSeconds;
        for(Integer value: this.times){
            if(value == Integer.MAX_VALUE || timeForState - value < 0){
                if(this.state == null || this.state != newState) setCurrentState(newState);
                return;
            }
            newState = newState == GameState.GameScatter ? GameState.GameChase : GameState.GameScatter;
            timeForState -= value;
        }
    }

    public void setCurrentState(GameState newState){
        this.state = newState;
        notifyObservers();
        if(newState == GameState.GameFrightned) frightnedTime = elapsedSeconds;
    }

    public void addObserver(Controller controller){
        this.observers.add(controller);
    }

    private void notifyObservers(){
        for(Controller controller: observers){
            controller.notify(this.state);
        }
    }

    public GameState getState() { return state; }

    public void resetCurrentState() {
        this.state = GameState.GameScatter;
    }
}

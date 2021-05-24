package g50.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameState {
    public enum CurrentState{
        GameScatter, GameChase, GameFrightned
    }

    private CurrentState state;
    private List<Integer> times;
    private int frightnedTime;
    private int elapsedSeconds;
    private static int defaultFrightnedTime = 6;
    private List<Controller> observers;

    public GameState(){
        this.state = CurrentState.GameScatter;
        this.times = Arrays.asList(7, 20, 7, 20, 5, 20, 5, Integer.MAX_VALUE);
        this.observers = new ArrayList<>();
        this.frightnedTime = 0;
        this.elapsedSeconds = 0;
    }

    public void update(int frame, int framerate) {
        elapsedSeconds = frame/framerate;

        if(state == CurrentState.GameFrightned)
            if(elapsedSeconds - frightnedTime < defaultFrightnedTime) return;

        CurrentState newState = CurrentState.GameScatter;
        for(Integer value: this.times){
            if(value == Integer.MAX_VALUE || elapsedSeconds - value < 0){
                if(this.state == null || this.state != newState) setCurrentState(newState);
                return;
            }
            newState = newState == CurrentState.GameScatter ? CurrentState.GameChase : CurrentState.GameScatter;
            elapsedSeconds -= value;
        }
    }

    public void setCurrentState(CurrentState newState){
        this.state = newState;
        notifyObservers();
        if(newState == CurrentState.GameFrightned) frightnedTime = elapsedSeconds;
    }

    public void addObserver(Controller controller){
        this.observers.add(controller);
    }

    private void notifyObservers(){
        for(Controller controller: observers){
            controller.notify(this.state);
        }
    }

    public CurrentState getState() { return state; }
}

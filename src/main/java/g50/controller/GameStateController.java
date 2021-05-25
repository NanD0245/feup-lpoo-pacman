package g50.controller;

import g50.controller.states.GameState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GameStateController implements Controller{
    private GameState state;
    private List<Integer> times;
    private int frightnedTime;
    private int elapsedSeconds;
    private static int defaultFrightnedTime = 6;
    private List<Controller> observers;

    public GameStateController(){
        this.state = GameState.GameScatter;
        this.times = Arrays.asList(7, 20, 7, 20, 5, 20, 5, Integer.MAX_VALUE);
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

    @Override
    public void update(int frame) {}

    @Override
    public void notify(GameState state) {

    }
}

package g50.controller;

import g50.Application;
import g50.controller.states.GameState;
import g50.controller.states.app_states.AppState;
import g50.gui.GUIObserver;

import java.io.IOException;

public abstract class Controller<T> implements GUIObserver {
    private final T model;
    public AppState state;

    protected Controller(T model) {
        this.model = model;
    }

    public abstract void update(Application application, int frame) throws IOException;

    public abstract void notify(GameState gameState);

    public T getModel() {
        return model;
    }

    public void setState(AppState state) {
        this.state = state;
    }

}

package g50.controller;

import g50.Application;
import g50.controller.states.GameState;

public abstract class Controller<T> {
    private final T model;

    protected Controller(T model) {
        this.model = model;
    }
    public abstract void update(Application application, int frame);

    public abstract void notify(GameState gameState);

    public T getModel() {
        return model;
    }
}

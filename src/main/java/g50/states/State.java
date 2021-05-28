package g50.states;

import g50.controller.Controller;

public abstract class State<T> {
    private final T model;

    public State(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract Controller<T> getController();
}

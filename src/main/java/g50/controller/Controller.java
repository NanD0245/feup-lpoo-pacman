package g50.controller;

import g50.Application;
import g50.view.Viewer;

public abstract class Controller<T> {
    private final T model;
    private final Viewer<T> viewer;

    protected Controller(T model) {
        this.model = model;
        this.viewer = getViewer();
    }
    public abstract void update(Application application, int frame);

    public T getModel() {
        return model;
    }

    public abstract Viewer<T> getViewer();
}

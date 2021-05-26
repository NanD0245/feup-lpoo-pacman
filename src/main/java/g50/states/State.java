/*package g50.states;

import g50.Application;
import g50.controller.ApplicationController;
import g50.controller.Controller;
import g50.view.Viewer;

public abstract class State<T> {

    private T model;
    private Viewer<T> viewer;
    private Controller<T> controller;
    private Application application;


    protected abstract Viewer<T> getViewer();

    protected abstract Controller<T> getController();

    State(T model, Application application){
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
        this.application = application;
    }

    public T getModel() {
        return model;
    }

    public void step(int frame) {
        controller.update(application, frame);
    }
}*/

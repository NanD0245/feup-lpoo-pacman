package g50.states;

import g50.Application;
import g50.controller.ApplicationController;

public abstract class AppState {
    protected final ApplicationController applicationController;

    AppState(ApplicationController applicationController){
        this.applicationController = applicationController;
    }

    abstract AppState step(int frame);
}

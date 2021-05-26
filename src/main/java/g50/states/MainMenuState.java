package g50.states;

import g50.Application;
import g50.controller.ApplicationController;

public class MainMenuState extends AppState {

    MainMenuState(ApplicationController applicationController) {
        super(applicationController);
    }

    @Override
    AppState step(int frame) {
        return null;
    }
}

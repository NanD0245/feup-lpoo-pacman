package g50.states;

import g50.Application;
import g50.controller.Controller;

public class ApplicationState extends State<Application> {
    public ApplicationState(Application model) {
        super(model);
    }

    @Override
    public Controller<Application> getController() {
        return null;
    }
}

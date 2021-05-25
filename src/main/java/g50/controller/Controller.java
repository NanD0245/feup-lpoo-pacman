package g50.controller;

import g50.controller.states.GameState;

public interface Controller {
    public void update(int frame);
    public void notify(GameState state);
}

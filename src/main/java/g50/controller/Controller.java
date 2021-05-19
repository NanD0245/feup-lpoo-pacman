package g50.controller;

public interface Controller {
    public void update(int frame);
    public void notify(GameState.CurrentState state);
}

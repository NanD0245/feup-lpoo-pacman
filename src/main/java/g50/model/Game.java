package g50.model;

import g50.model.map.GameMap;

public class Game {
    private final GameMap map;
    private int score = 0;

    public Game(GameMap map) {
        this.map = map;
    }

    public GameMap getMap() {
        return map;
    }

    public int getScore() {
        return score;
    }
}

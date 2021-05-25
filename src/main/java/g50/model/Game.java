package g50.model;

import g50.model.map.GameMap;
import g50.model.map.mapbuilder.DefaultGameMapBuilder;

import java.io.IOException;

public class Game {
    private final GameMap map;
    private int score = 0;

    public Game() throws IOException {
        this.map = new DefaultGameMapBuilder().getBuild();
    }

    public GameMap getGameMap() {
        return map;
    }

    public int getScore() {
        return score;
    }
}

package g50.model;

import g50.model.map.GameMap;
import g50.model.map.mapbuilder.DefaultGameMapBuilder;

import java.io.IOException;

public class Game {
    private final GameMap map;
    private int score = 0;
    private int highscore;

    public Game(int highscore) throws IOException {
        this.map = new DefaultGameMapBuilder().getBuild();
        this.highscore = highscore;
    }

    public GameMap getGameMap() {
        return map;
    }

    public int getScore() {
        return score;
    }

    public int getHighscore() {
        return highscore;
    }

    public void incrementScore(int increment) { score += increment; }

    public void resetScore() { score = 0; }

    public void resetPositions() {
        this.map.resetPositions();
    }
}

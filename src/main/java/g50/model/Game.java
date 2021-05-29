package g50.model;

import g50.model.map.GameMap;
import g50.model.map.mapbuilder.DefaultGameMapBuilder;

import java.io.IOException;

public class Game {
    private final GameMap map;
    private int score;
    private final int highScore;
    private final Level levelInfo;

    public Game(int highScore, int level) throws IOException {
        this.map = new DefaultGameMapBuilder().getBuild();
        this.highScore = highScore;
        this.score = 0;
        this.levelInfo = new Level(level);
    }

    public GameMap getGameMap() {
        return map;
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public Level getLevelInfo() { return levelInfo; }

    public void incrementScore(int increment) { score += increment; }

    public void resetScore() { score = 0; }

    public void resetPositions() {
        this.map.resetPositions();
    }
}

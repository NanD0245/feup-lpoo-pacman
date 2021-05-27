package g50.model;

import g50.model.map.GameMap;
import g50.model.map.mapbuilder.DefaultGameMapBuilder;

import java.io.IOException;

public class Game {
    private final GameMap map;
    private int score;
    private LevelInfo levelInfo;

    public Game() throws IOException {
        this.map = new DefaultGameMapBuilder().getBuild();
        this.score = 0;
        this.levelInfo = new LevelInfo(1);
    }
    public Game(int score, int level) throws IOException {
        this.map = new DefaultGameMapBuilder().getBuild();
        this.score = score;
        this.levelInfo = new LevelInfo(level);
    }

    public GameMap getGameMap() {
        return map;
    }

    public int getScore() {
        return score;
    }

    public LevelInfo getLevelInfo() { return levelInfo; }

    public void incrementScore(int increment) { score += increment; }

    public void resetScore() { score = 0; }

    public void resetPositions() {
        this.map.resetPositions();
    }
}

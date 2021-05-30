package com.g50.model.menu;

import java.util.Collections;

public class HighScoreMenu extends Menu {

    private final int highScore;

    public HighScoreMenu(int highScore) {
        super("HIGH-SCORE", Collections.singletonList(ENTRIES.RETURN_ENTER));
        this.highScore = highScore;
    }

    public int getHighScore() {
        return highScore;
    }
}

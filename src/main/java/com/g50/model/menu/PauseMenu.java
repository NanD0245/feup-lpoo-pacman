package com.g50.model.menu;

import java.util.Arrays;

public class PauseMenu extends Menu {

    private int score;
    public PauseMenu(int score) {
        super("PAUSE", Arrays.asList(ENTRIES.RESUME, ENTRIES.EXIT));
        this.score = score;
    }

    public boolean isSelectedResume() { return (currentEntry == 0); }

    public boolean isSelectedExit() { return (currentEntry == 1); }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

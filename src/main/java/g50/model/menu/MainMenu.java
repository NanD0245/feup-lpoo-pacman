package g50.model.menu;

import java.util.Arrays;

public class MainMenu extends Menu {
    public MainMenu() {
        super(Arrays.asList("START", "CONTROLS", "CREDITS", "HIGH-SCORE", "EXIT"));
    }

    public boolean isSelectedStart() {
        return (currentEntry == 0);
    }

    public boolean isSelectedControls() {
        return (currentEntry == 1);
    }

    public boolean isSelectedCredits() {
        return (currentEntry == 2);
    }

    public boolean isSelectedHighScore() {
        return (currentEntry == 3);
    }
    public boolean isSelectedExit() {
        return (currentEntry == 4);
    }
}

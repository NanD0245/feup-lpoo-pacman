package g50.model.menu;

import java.util.Arrays;
import java.util.List;

public class PauseMenu extends Menu {
    public PauseMenu() {
        super(Arrays.asList("RESUME", "EXIT"));
    }

    public boolean isSelectedResume() { return (currentEntry == 0); }

    public boolean isSelectedExit() { return (currentEntry == 1); }
}

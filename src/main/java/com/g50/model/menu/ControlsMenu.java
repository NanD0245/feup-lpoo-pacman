package com.g50.model.menu;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ControlsMenu extends Menu {
    List<String> text;

    public ControlsMenu() {
        super("CONTROLS" , Collections.singletonList(ENTRIES.RETURN_ENTER));

        this.text = Arrays.asList(" PACMAN CONTROLS:", "",
                "   MOVE UP - ARROW UP", "",
                "   MOVE LEFT - ARROW LEFT", "",
                "   MOVE RIGHT - ARROW RIGHT", "",
                "   MOVE DOWN - ARROW DOWN", "", "", "",
                " PAUSE - ESQ", "", "", "",
                " SELECT - ENTER");
    }

    public String getText(int i) {
        return text.get(i);
    }

    public int getLinesNumber() {
        return text.size();
    }
}

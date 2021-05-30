package com.g50.view.menu;

import com.g50.gui.GUI;
import com.g50.model.element.Position;
import com.g50.model.menu.GameOverMenu;

import java.io.IOException;

public class GameOverViewer extends MenuViewer {

    public GameOverViewer(GameOverMenu model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();

        gui.drawText(getModel().getTitle(), new Position(9, 12), "#FF0000");

        gui.drawBlinkText(map.get(getModel().getEntry(0)), new Position(3, 21), "#FFFF00");

        gui.refresh();
    }
}

package g50.view.menu;

import g50.gui.GUI;
import g50.model.Position;
import g50.model.menu.GameOverMenu;
import g50.model.menu.Menu;

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

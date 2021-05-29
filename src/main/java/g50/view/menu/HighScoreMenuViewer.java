package g50.view.menu;

import g50.gui.GUI;
import g50.model.element.Position;
import g50.model.menu.HighScoreMenu;
import g50.model.menu.Menu;

import java.io.IOException;

public class HighScoreMenuViewer extends MenuViewer{

    public HighScoreMenuViewer(HighScoreMenu model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();

        gui.drawText(getModel().getTitle(), new Position(8, 10), "#FF0000");

        gui.drawText("   HIGH-SCORE: " + ((HighScoreMenu) getModel()).getHighScore(), new Position(0,15));

        gui.drawBlinkText(map.get(getModel().getEntry(0)), new Position(3, 21), "#FFFF00");

        gui.refresh();
    }
}

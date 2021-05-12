package g50.view.menu;

import g50.gui.GUI;
import g50.model.Position;
import g50.model.menu.Menu;

import java.io.IOException;

public class GameOverViewer extends MenuViewer{
    public GameOverViewer(GUI gui) {
        super(gui);
    }

    @Override
    public void initViewerBuilder(GUI gui) {

    }

    @Override
    public void draw(Menu menu) throws IOException {
        gui.clear();

        gui.drawText(menu.getEntry(0), new Position(9, 16), "#FF0000");

        gui.drawBlinkText(menu.getEntry(1), new Position(3, 25), "#FFFFFF");

        gui.refresh();
    }
}

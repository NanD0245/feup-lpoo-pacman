package g50.view.menu;


import g50.gui.GUI;
import g50.model.Position;
import g50.model.menu.Menu;
import g50.model.menu.PauseMenu;

import java.io.IOException;

public class PauseMenuViewer extends MenuViewer<PauseMenu> {
    public PauseMenuViewer(GUI gui) {
        super(gui);
    }

    @Override
    public void draw(PauseMenu menu) throws IOException {
        gui.clear();

        gui.drawText("PAUSE", new Position(11, 7), "#FFFF00");

        int selected = menu.getCurrentEntry();

        for (int i = 0; i < menu.getNumberEntries(); i++) {
            if (i == selected)
                gui.drawText('>' + menu.getEntry(i), new Position(5-1, 14 + 2*i), "#FFFF00");
            else {
                gui.drawText(menu.getEntry(i), new Position(5, 14 + 2*i));
            }
        }

        gui.drawText("CURRENT SCORE: ", new Position(5, 25));

        gui.refresh();
    }
}

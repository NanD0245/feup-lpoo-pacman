package g50.view.menu;

import g50.gui.GUI;
import g50.model.Position;
import g50.model.menu.ControlsMenu;

import java.io.IOException;

public class ControlsMenuViewer extends MenuViewer<ControlsMenu> {
    @Override
    public void draw(GUI gui, ControlsMenu menu) throws IOException {
        gui.clear();

        gui.drawText(menu.getTitle(), new Position(10,5), "#FFFF00");

        for (int i = 0; i < menu.getLinesNumber(); i++) {
            if (!menu.getText(i).equals(""))
                gui.drawText(menu.getText(i), new Position(0, 10 + i));
        }

        gui.drawBlinkText(map.get(menu.getEntry(0)), new Position(3, 30), "#FFFF00");

        gui.refresh();
    }
}

package g50.view.menu;

import g50.gui.GUI;
import g50.model.element.Position;
import g50.model.menu.ControlsMenu;

import java.io.IOException;

public class ControlsMenuViewer extends MenuViewer {

    public ControlsMenuViewer(ControlsMenu model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();

        gui.drawText(getModel().getTitle(), new Position(10,5), "#FFFF00");

        for (int i = 0; i < ((ControlsMenu)getModel()).getLinesNumber(); i++) {
            if (!((ControlsMenu)getModel()).getText(i).equals(""))
                gui.drawText(((ControlsMenu)getModel()).getText(i), new Position(0, 10 + i));
        }

        gui.drawBlinkText(map.get(getModel().getEntry(0)), new Position(3, 30), "#FFFF00");

        gui.refresh();
    }
}

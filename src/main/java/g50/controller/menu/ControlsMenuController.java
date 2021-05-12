package g50.controller.menu;

import g50.gui.GUI;
import g50.model.menu.ControlsMenu;
import g50.view.menu.ControlsMenuViewer;
import g50.view.menu.MenuViewer;

import java.io.IOException;

public class ControlsMenuController extends MenuController<ControlsMenuViewer, ControlsMenu> {

    public ControlsMenuController(GUI gui, ControlsMenuViewer menuViewer, ControlsMenu menu) { super(gui, menuViewer, menu); }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        if (action == GUI.KBD_ACTION.ESQ) {

        }
    }

    @Override
    public void update(int frame) {
        try {
            menuViewer.draw(gui, menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package g50.controller.menu;

import g50.gui.GUI;
import g50.model.menu.ControlsMenu;
import g50.view.menu.ControlsMenuViewer;
import g50.view.menu.MenuViewer;

public class ControlsMenuController extends MenuController<ControlsMenu> {

    public ControlsMenuController(GUI gui, MenuViewer<ControlsMenu> menuViewer, ControlsMenu menu) { super(gui, menuViewer, menu); }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        if (action == GUI.KBD_ACTION.SELECT) {

        }
    }
}

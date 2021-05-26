package g50.controller.menu;

import g50.controller.states.GameState;
import g50.controller.states.app_states.AppState;
import g50.gui.GUI;
import g50.model.menu.ControlsMenu;
import g50.view.menu.ControlsMenuViewer;
import g50.view.menu.MenuViewer;

public class ControlsMenuController extends MenuController {

    public ControlsMenuController(GUI gui, ControlsMenu menu) { super(gui, new ControlsMenuViewer(menu), menu); }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        if (action == GUI.KBD_ACTION.SELECT) {
            setState(AppState.MAIN_MENU);
        }
    }

    @Override
    public void notify(GameState state) {

    }
}

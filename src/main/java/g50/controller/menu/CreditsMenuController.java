package g50.controller.menu;

import g50.controller.states.GameState;
import g50.gui.GUI;
import g50.model.menu.CreditsMenu;
import g50.view.menu.MenuViewer;

public class CreditsMenuController extends MenuController {

    public CreditsMenuController(GUI gui, MenuViewer menuViewer, CreditsMenu menu) {
        super(gui, menuViewer, menu);
    }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        if (action == GUI.KBD_ACTION.SELECT) {

        }
    }

    @Override
    public void notify(GameState state) {

    }
}

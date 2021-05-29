package g50.controller.menu;

import g50.Application;
import g50.gui.GUI;
import g50.model.menu.HighScoreMenu;
import g50.model.menu.Menu;
import g50.states.AppState;
import g50.view.menu.HighScoreMenuViewer;
import g50.view.menu.MenuViewer;

public class HighScoreMenuController extends MenuController {

    public HighScoreMenuController(GUI gui, HighScoreMenu menu) {
        super(gui, new HighScoreMenuViewer(menu), menu);
    }

    @Override
    public void handleKBDAction(Application application, GUI.KBD_ACTION action) {
        if (action == GUI.KBD_ACTION.SELECT) {
            application.setState(AppState.MAIN_MENU);
        }
    }
}

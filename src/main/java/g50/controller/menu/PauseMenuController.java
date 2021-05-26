package g50.controller.menu;

import g50.controller.states.GameState;
import g50.controller.states.app_states.AppState;
import g50.gui.GUI;
import g50.model.menu.PauseMenu;
import g50.view.menu.MenuViewer;
import g50.view.menu.PauseMenuViewer;

public class PauseMenuController extends MenuController {

    public PauseMenuController(GUI gui, PauseMenu menu) {
        super(gui, new PauseMenuViewer(menu), menu);
    }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                if (((PauseMenu)getModel()).isSelectedResume())
                    setState(AppState.IN_GAME);
                if (((PauseMenu)getModel()).isSelectedExit())
                    setState(AppState.MAIN_MENU);
                break;
        }
    }

    @Override
    public void notify(GameState state) {

    }
}

package g50.controller.menu;

import g50.Application;
import g50.states.AppState;
import g50.gui.GUI;
import g50.model.menu.PauseMenu;
import g50.view.menu.PauseMenuViewer;

public class PauseMenuController extends MenuController {

    public PauseMenuController(GUI gui, PauseMenu menu) {
        super(gui, new PauseMenuViewer(menu), menu);
    }

    @Override
    public void handleKBDAction(Application application, GUI.KBD_ACTION action) {
        System.out.println("entrei");
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                if (((PauseMenu)getModel()).isSelectedResume())
                    application.setState(AppState.IN_GAME);
                if (((PauseMenu)getModel()).isSelectedExit())
                    application.setState(AppState.MAIN_MENU);
                break;
        }
    }
}

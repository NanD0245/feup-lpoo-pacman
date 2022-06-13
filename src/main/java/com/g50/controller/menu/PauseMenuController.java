package com.g50.controller.menu;

import com.g50.Application;
import com.g50.states.AppState;
import com.g50.gui.GUI;
import com.g50.model.menu.PauseMenu;
import com.g50.view.menu.PauseMenuViewer;

public class PauseMenuController extends MenuController {

    public PauseMenuController(GUI gui, PauseMenu menu) {
        super(gui, new PauseMenuViewer(menu), menu);
    }

    @Override
    public void handleKBDAction(Application application, GUI.KBD_ACTION action) {
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
                if (((PauseMenu)getModel()).isSelectedExit()) {
                    application.setState(AppState.MAIN_MENU);
                    if (((PauseMenu) getModel()).getScore() > application.getHighScore())
                        application.setHighScore(((PauseMenu) getModel()).getScore());
                }
                break;
        }
    }
}

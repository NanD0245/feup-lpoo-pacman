package com.g50.controller.menu;

import com.g50.Application;
import com.g50.gui.GUI;
import com.g50.model.menu.HighScoreMenu;
import com.g50.states.AppState;
import com.g50.view.menu.HighScoreMenuViewer;

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

package com.g50.controller.menu;

import com.g50.Application;
import com.g50.states.AppState;
import com.g50.gui.GUI;
import com.g50.model.menu.MainMenu;
import com.g50.view.menu.MainMenuViewer;

public class MainMenuController extends MenuController {

    public MainMenuController(GUI gui, MainMenu menu){
        super(gui, new MainMenuViewer(menu),menu);
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
                if (((MainMenu) getModel()).isSelectedStart())
                    application.setState(AppState.IN_GAME);
                if (((MainMenu) getModel()).isSelectedControls())
                    application.setState(AppState.CONTROLS_MENU);
                if (((MainMenu) getModel()).isSelectedHighScore())
                    application.setState(AppState.HIGH_SCORE_MENU);
                if (((MainMenu) getModel()).isSelectedCredits())
                    application.setState(AppState.CREDITS_MENU);
                if (((MainMenu) getModel()).isSelectedExit())
                    application.setState(AppState.EXIT_MENU);
                break;
        }
    }
}

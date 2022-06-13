package com.g50.controller;

import com.g50.Application;
import com.g50.controller.menu.*;
import com.g50.gui.GUI;
import com.g50.gui.LanternaGUI;
import com.g50.model.element.Position;
import com.g50.model.menu.*;
import com.g50.model.menu.Menu;
import com.g50.states.AppState;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuControllerTest {

    @Test
    public void MenuControllersTest() throws IOException, URISyntaxException, FontFormatException {
        Menu menu = null;
        Controller<Menu> menuController = null;
        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        Application application = new Application(gui);

        assert(application.getMenu() instanceof MainMenu);
        assertTrue(((MainMenu) application.getMenu()).isSelectedStart());
        ((MainMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.DOWN);

        assertTrue(((MainMenu) application.getMenu()).isSelectedControls());
        ((MainMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.ESQ);
        assertTrue(((MainMenu) application.getMenu()).isSelectedControls());

        ((MainMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.OTHER);
        assertTrue(((MainMenu) application.getMenu()).isSelectedControls());

        ((MainMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.SELECT);
        assertEquals(application.getState(), AppState.CONTROLS_MENU);

        menu = new ControlsMenu();
        menuController = new ControlsMenuController(gui,(ControlsMenu) menu);
        application.setMenu(menu);
        application.setController(menuController);

        ((ControlsMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.SELECT);
        assertEquals(application.getState(), AppState.MAIN_MENU);

        menu = new MainMenu();
        menuController = new MainMenuController(gui,(MainMenu) menu);
        application.setMenu(menu);
        application.setController(menuController);

        assertTrue(((MainMenu) application.getMenu()).isSelectedStart());
        ((MainMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.DOWN);
        ((MainMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.DOWN);
        assertTrue(((MainMenu) application.getMenu()).isSelectedHighScore());

        ((MainMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.SELECT);
        assertEquals(application.getState(), AppState.HIGH_SCORE_MENU);

        menu = new HighScoreMenu(0);
        menuController = new HighScoreMenuController(gui,(HighScoreMenu) menu);
        application.setMenu(menu);
        application.setController(menuController);

        ((HighScoreMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.ESQ);
        assertEquals(application.getState(), AppState.HIGH_SCORE_MENU);
        ((HighScoreMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.OTHER);
        assertEquals(application.getState(), AppState.HIGH_SCORE_MENU);
        ((HighScoreMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.SELECT);
        assertEquals(application.getState(), AppState.MAIN_MENU);

        menu = new MainMenu();
        menuController = new MainMenuController(gui,(MainMenu) menu);
        application.setMenu(menu);
        application.setController(menuController);

        assertTrue(((MainMenu) application.getMenu()).isSelectedStart());
        ((MainMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.DOWN);
        ((MainMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.DOWN);
        ((MainMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.DOWN);
        assertTrue(((MainMenu) application.getMenu()).isSelectedCredits());

        ((MainMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.SELECT);
        assertEquals(application.getState(), AppState.CREDITS_MENU);

        menu = new CreditsMenu();
        menuController = new CreditsMenuController(gui,(CreditsMenu) menu);
        application.setMenu(menu);
        application.setController(menuController);

        ((CreditsMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.ESQ);
        assertEquals(application.getState(), AppState.CREDITS_MENU);
        ((CreditsMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.OTHER);
        assertEquals(application.getState(), AppState.CREDITS_MENU);
        ((CreditsMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.SELECT);
        assertEquals(application.getState(), AppState.MAIN_MENU);

        menu = new MainMenu();
        menuController = new MainMenuController(gui,(MainMenu) menu);
        application.setMenu(menu);
        application.setController(menuController);

        assertTrue(((MainMenu) application.getMenu()).isSelectedStart());
        ((MainMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.DOWN);
        ((MainMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.DOWN);
        ((MainMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.DOWN);
        ((MainMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.DOWN);
        assertTrue(((MainMenu) application.getMenu()).isSelectedExit());

        menu = new GameOverMenu();
        menuController = new GameOverMenuController(gui, (GameOverMenu) menu);
        application.setMenu(menu);
        application.setController(menuController);
        application.setState(AppState.GAME_OVER);

        ((GameOverMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.DOWN);

        assertEquals(application.getState(), AppState.GAME_OVER);

        ((GameOverMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.SELECT);

        assertEquals(application.getState(), AppState.MAIN_MENU);

        menu = new PauseMenu(0);
        menuController = new PauseMenuController(gui,(PauseMenu) menu);
        application.setMenu(menu);
        application.setController(menuController);

        ((PauseMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.UP);
        assertTrue(((PauseMenu) application.getMenu()).isSelectedExit());
        ((PauseMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.OTHER);
        assertTrue(((PauseMenu) application.getMenu()).isSelectedExit());
        ((PauseMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.DOWN);
        assertTrue(((PauseMenu) application.getMenu()).isSelectedResume());
        ((PauseMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.SELECT);
        assertEquals(application.getState(), AppState.IN_GAME);

        application.setState(AppState.PAUSE_MENU);

        ((PauseMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.UP);
        assertTrue(((PauseMenu) application.getMenu()).isSelectedExit());
        ((PauseMenuController)application.getController()).handleKBDAction(application,GUI.KBD_ACTION.SELECT);
        assertEquals(application.getState(), AppState.MAIN_MENU);

        menu = new TransitionMenu();
        menuController = new TransitionMenuController(gui,(TransitionMenu) menu);
        application.setMenu(menu);
        application.setController(menuController);
        application.setState(AppState.NEXT_LEVEL_MENU);

        ((TransitionMenu) menu).getGhost().setPosition(new Position(-1, 0));
        menuController.update(application, 1);
        assertEquals(application.getState(), AppState.NEXT_LEVEL_MENU);
        ((TransitionMenu) menu).getPacMan().setPosition(new Position(28, 0));
        menuController.update(application, 1);
        assertEquals(application.getState(), AppState.NEXT_LEVEL_MENU);
        ((TransitionMenu) menu).getPacMan().setPosition(new Position(29, 0));
        menuController.update(application, 1);
        assertEquals(application.getState(), AppState.IN_GAME);

    }
}

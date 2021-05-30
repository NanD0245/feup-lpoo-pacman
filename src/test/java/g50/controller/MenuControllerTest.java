package g50.controller;

import g50.Application;
import g50.controller.menu.*;
import g50.gui.GUI;
import g50.gui.LanternaGUI;
import g50.model.menu.*;
import g50.model.menu.Menu;
import g50.states.AppState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuControllerTest {

    @Test
    public void MenuControllersTest() throws IOException, URISyntaxException, FontFormatException {
        Menu menu = null;
        Controller<Menu> menuController = null;

        GUI gui = new g50.gui.LanternaGUI(28,38);
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
    }
}

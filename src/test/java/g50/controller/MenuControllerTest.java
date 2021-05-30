package g50.controller;

import g50.Application;
import g50.controller.menu.MainMenuController;
import g50.controller.menu.PauseMenuController;
import g50.gui.GUI;
import g50.model.menu.MainMenu;
import g50.model.menu.PauseMenu;
import g50.states.AppState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuControllerTest {

    private Application mockApp;

    @BeforeEach
    public void setupViewMock(){
        mockApp = Mockito.mock(Application.class);
    }

    @Test
    public void mainMenuTest() {
        MainMenu menu = new MainMenu();
        MainMenuController menuController = new MainMenuController(null, menu);

        assertTrue(menu.isSelectedStart());
        menuController.handleKBDAction(mockApp, GUI.KBD_ACTION.DOWN);

        assertTrue(menu.isSelectedControls());
        menuController.handleKBDAction(mockApp, GUI.KBD_ACTION.ESQ);
        assertTrue(menu.isSelectedControls());

        menuController.handleKBDAction(mockApp, GUI.KBD_ACTION.OTHER);
        assertTrue(menu.isSelectedControls());

        menuController.handleKBDAction(mockApp, GUI.KBD_ACTION.DOWN);
        assertTrue(menu.isSelectedHighScore());
    }

    @Test
    public void pauseMenuTest() {
        PauseMenu menu = new PauseMenu(0);
        PauseMenuController menuController = new PauseMenuController(null, menu);

        assertTrue(menu.isSelectedResume());
        menuController.handleKBDAction(null, GUI.KBD_ACTION.DOWN);

        assertTrue(menu.isSelectedExit());
        menuController.handleKBDAction(null, GUI.KBD_ACTION.ESQ);
        assertTrue(menu.isSelectedExit());

        menuController.handleKBDAction(null, GUI.KBD_ACTION.OTHER);
        assertTrue(menu.isSelectedExit());

        menuController.handleKBDAction(null, GUI.KBD_ACTION.DOWN);
        assertTrue(menu.isSelectedResume());
    }
}

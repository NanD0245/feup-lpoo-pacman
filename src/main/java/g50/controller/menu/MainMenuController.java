package g50.controller.menu;

import g50.Application;
import g50.controller.Controller;
import g50.controller.states.GameState;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.menu.MainMenu;
import g50.view.menu.MainMenuViewer;
import g50.view.menu.MenuViewer;

public class MainMenuController extends MenuController {

    public MainMenuController(GUI gui, MainMenu menu){
        super(gui, new MainMenuViewer(menu),menu);
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
                if (((MainMenu)getModel()).isSelectedExit())
                if (((MainMenu)getModel()).isSelectedStart())
                    break;
        }
    }

    @Override
    public void notify(GameState state) {

    }
}

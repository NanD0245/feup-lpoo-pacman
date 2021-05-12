package g50.controller.menu;

import g50.gui.GUI;
import g50.model.menu.PauseMenu;
import g50.view.menu.MenuViewer;

import java.io.IOException;

public class PauseMenuController extends MenuController<PauseMenu> {

    public PauseMenuController(MenuViewer menuViewer, PauseMenu menu) {
        super(menuViewer, menu);
    }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        switch (action) {
            case UP:
                menu.previousEntry();
                break;
            case DOWN:
                menu.nextEntry();
                break;
            case SELECT:
                if (menu.isSelectedResume())
                if (menu.isSelectedExit())
                break;
        }
    }
}

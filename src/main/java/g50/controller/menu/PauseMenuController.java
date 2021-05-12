package g50.controller.menu;

import g50.gui.GUI;
import g50.model.menu.PauseMenu;
import g50.view.menu.MenuViewer;
import g50.view.menu.PauseMenuViewer;

import java.io.IOException;

public class PauseMenuController extends MenuController<PauseMenuViewer, PauseMenu> {

    public PauseMenuController(GUI gui, PauseMenuViewer menuViewer, PauseMenu menu) {
        super(gui, menuViewer, menu);
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

    @Override
    public void update(int frame) {
        try {
            menuViewer.draw(gui, menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

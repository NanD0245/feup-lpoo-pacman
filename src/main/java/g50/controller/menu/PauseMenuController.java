package g50.controller.menu;

import g50.controller.states.GameState;
import g50.gui.GUI;
import g50.model.menu.PauseMenu;
import g50.view.menu.MenuViewer;
import g50.view.menu.PauseMenuViewer;

public class PauseMenuController extends MenuController<PauseMenu> {

    public PauseMenuController(GUI gui, MenuViewer<PauseMenu> menuViewer, PauseMenu menu) {
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
    public void notify(GameState state) {

    }
}

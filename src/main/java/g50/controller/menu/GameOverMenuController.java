package g50.controller.menu;

import g50.gui.GUI;
import g50.model.menu.GameOverMenu;
import g50.view.menu.GameOverViewer;

public class GameOverMenuController extends MenuController<GameOverMenu> {

    public GameOverMenuController(GUI gui, GameOverViewer menuViewer, GameOverMenu menu) {
        super(gui,menuViewer,menu);
    }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        if (action == GUI.KBD_ACTION.SELECT) {

        }
    }
}

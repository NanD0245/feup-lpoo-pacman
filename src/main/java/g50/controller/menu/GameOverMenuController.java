package g50.controller.menu;

import g50.gui.GUI;
import g50.model.menu.GameOverMenu;
import g50.model.menu.MainMenu;
import g50.model.menu.Menu;
import g50.view.menu.GameOverViewer;
import g50.view.menu.MenuViewer;

import java.io.IOException;

public class GameOverMenuController extends MenuController<GameOverViewer,GameOverMenu> {

    public GameOverMenuController(GUI gui, GameOverViewer menuViewer, GameOverMenu menu) {
        super(gui,menuViewer,menu);
    }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        if (action == GUI.KBD_ACTION.SELECT) {

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

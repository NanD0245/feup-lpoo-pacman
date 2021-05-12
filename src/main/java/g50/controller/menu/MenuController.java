package g50.controller.menu;

import g50.controller.Controller;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.menu.Menu;
import g50.view.menu.MenuViewer;

import java.io.IOException;

public class MenuController implements Controller , GUIObserver {
    Menu menu;
    MenuViewer menuViewer;

    public MenuController(MenuViewer menuViewer, Menu menu) {
        this.menu = menu;
        this.menuViewer = menuViewer;
    }

    public void addPendingKBDAction(GUI.KBD_ACTION action) {
        switch (action) {
            case UP:
                menu.previousEntry();
                break;
            case DOWN:
                menu.nextEntry();
                break;
            case SELECT:
                //if (menu.isSelectedExit())
                //if (menu.isSelectedStart())
                break;
        }
    }

    @Override
    public void update(int frame) {
        try {
            menuViewer.draw(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

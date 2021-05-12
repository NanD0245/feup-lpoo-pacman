package g50.controller.menu;

import g50.controller.Controller;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.view.menu.MenuViewer;
import g50.model.menu.Menu;

import java.io.IOException;

public abstract class MenuController<T> implements Controller , GUIObserver {
    T menu;
    MenuViewer menuViewer;

    public MenuController(MenuViewer menuViewer, T menu) {
        this.menu = menu;
        this.menuViewer = menuViewer;
    }

    @Override
    public abstract void addPendingKBDAction(GUI.KBD_ACTION action);

    @Override
    public void update(int frame) {
        try {
            menuViewer.draw((Menu)menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

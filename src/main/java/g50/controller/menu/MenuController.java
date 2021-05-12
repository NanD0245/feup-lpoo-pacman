package g50.controller.menu;

import g50.controller.Controller;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.view.menu.MenuViewer;

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
    public abstract void update(int frame);
}

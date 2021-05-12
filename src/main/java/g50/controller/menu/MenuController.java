package g50.controller.menu;

import g50.controller.Controller;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.view.menu.MenuViewer;

import java.io.IOException;

public abstract class MenuController<T> implements Controller , GUIObserver {
    GUI gui;
    T menu;
    MenuViewer<T> menuViewer;

    public MenuController(GUI gui, MenuViewer<T> menuViewer, T menu) {
        this.gui = gui;
        this.menu = menu;
        this.menuViewer = menuViewer;
    }

    @Override
    public abstract void addPendingKBDAction(GUI.KBD_ACTION action);

    @Override
    public void update(int frame) {
        try {
            menuViewer.draw(gui, menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

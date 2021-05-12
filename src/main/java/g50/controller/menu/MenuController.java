package g50.controller.menu;

import g50.controller.Controller;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.view.menu.MenuViewer;
import g50.model.menu.Menu;

import java.io.IOException;

public abstract class MenuController<G,T> implements Controller , GUIObserver {
    GUI gui;
    T menu;
    G menuViewer;

    public MenuController(GUI gui, G menuViewer, T menu) {
        this.gui = gui;
        this.menu = menu;
        this.menuViewer = menuViewer;
    }

    @Override
    public abstract void addPendingKBDAction(GUI.KBD_ACTION action);

    @Override
    public abstract void update(int frame);
}

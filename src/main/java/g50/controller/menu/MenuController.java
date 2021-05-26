package g50.controller.menu;

import g50.Application;
import g50.controller.Controller;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.menu.ControlsMenu;
import g50.model.menu.Menu;
import g50.view.menu.MenuViewer;

import java.io.IOException;

public abstract class MenuController extends Controller<Menu> implements GUIObserver {
    private final GUI gui;
    private final MenuViewer menuViewer;

    public MenuController(GUI gui, MenuViewer menuViewer, Menu menu) {
        super(menu);
        this.gui = gui;
        this.menuViewer = menuViewer;
    }

    @Override
    public abstract void addPendingKBDAction(GUI.KBD_ACTION action);

    @Override
    public void update(Application application, int frame) {
        System.out.println("controller");
        try {
            menuViewer.draw(gui);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package g50.controller.menu;

import g50.gui.GUI;
import g50.model.menu.MainMenu;
import g50.view.menu.MenuViewer;

import java.io.IOException;

public class MainMenuController extends MenuController <MainMenu>{

    public MainMenuController(MenuViewer menuViewer, MainMenu menu) {
        super(menuViewer,menu);
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
                if (menu.isSelectedExit())
                    if (menu.isSelectedStart())
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

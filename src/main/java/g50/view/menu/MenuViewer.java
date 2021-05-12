package g50.view.menu;

import g50.gui.GUI;
import g50.model.Position;
import g50.model.menu.Menu;

import java.io.IOException;

public class MenuViewer {
    Menu menu;
    private final GUI gui;

    public MenuViewer(GUI gui, Menu menu) {
        this.menu = menu;
        this.gui = gui;
    }

    public void draw() throws IOException {
        this.gui.clear();

        gui.drawText("PAC-MAN" ,new Position(5,5), "#FFFF00");

        int selected = menu.getCurrentEntry();

        for (int i = 0; i < menu.getNumberEntries(); i++) {
            gui.drawText(menu.getEntry(i), new Position(5,7+2*i),
                    (selected == i) ? "#FFFF00" : "#FFFFFF");
        }

        this.gui.refresh();
    }
}

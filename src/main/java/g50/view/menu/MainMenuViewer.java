package g50.view.menu;

import g50.gui.GUI;
import g50.model.Position;
import g50.model.menu.MainMenu;
import g50.model.menu.Menu;
import g50.view.ElementViewer;
import g50.view.ViewProperty;

import java.io.IOException;

public class MainMenuViewer extends MenuViewer<MainMenu> {
    public MainMenuViewer(GUI gui) {
        super(gui);
    }

    public void initViewerBuilder(GUI gui) {
        this.elementViewers.add(new ElementViewer(gui, new ViewProperty("#DEA185",'o')));
        this.elementViewers.add(new ElementViewer(gui, new ViewProperty("#FFFF00",'P')));
        this.elementViewers.add(new ElementViewer(gui, new ViewProperty("#FF0000",'G')));
        this.elementViewers.add(new ElementViewer(gui, new ViewProperty("#FFB8FF",'G')));
        this.elementViewers.add(new ElementViewer(gui, new ViewProperty("#00FFFF",'G')));
        this.elementViewers.add(new ElementViewer(gui, new ViewProperty("#FFB852",'G')));
    }

    public void draw(MainMenu menu) throws IOException {
        gui.clear();

        gui.drawText("PAC-MAN" ,new Position(10,5), "#FFFF00");


        for (int i = 0; i < elementViewers.size(); i++)
            elementViewers.get(i).draw(new Position(11+i, 7));

        int selected = menu.getCurrentEntry();

        for (int i = 0; i < menu.getNumberEntries(); i++) {
            if (selected == i)
                gui.drawText(">" + menu.getEntry(i), new Position(5 - 1,12+2*i), "#FFFF00");
            else {
                gui.drawText(menu.getEntry(i), new Position(5, 12 + 2 * i), "#FFFFFF");
            }
        }

        gui.refresh();
    }
}

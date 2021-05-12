package g50.view.menu;

import g50.gui.GUI;
import g50.model.Position;
import g50.model.menu.MainMenu;
import g50.view.ViewProperty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenuViewer extends MenuViewer<MainMenu> {
    protected List<ViewProperty> elementViewers;

    public MainMenuViewer() {
        super();
        this.elementViewers = new ArrayList<>();
        initViewerBuilder();
    }

    public void initViewerBuilder() {
        this.elementViewers.add(new ViewProperty("#DEA185",'o'));
        this.elementViewers.add(new ViewProperty("#DEA185",'.'));
        this.elementViewers.add(new ViewProperty("#FFFF00",'P'));
        this.elementViewers.add(new ViewProperty("#FF0000",'G'));
        this.elementViewers.add(new ViewProperty("#FFB8FF",'G'));
        this.elementViewers.add(new ViewProperty("#00FFFF",'G'));
        this.elementViewers.add(new ViewProperty("#FFB852",'G'));
    }

    public void draw(GUI gui, MainMenu menu) throws IOException {
        gui.clear();

        gui.drawText(menu.getTitle(),new Position(10,5), "#FFFF00");


        for (int i = 0; i < elementViewers.size(); i++)
            gui.drawText(String.valueOf(elementViewers.get(i).getCharacter()),
                    new Position(10+i, 8),
                    elementViewers.get(i).getColor());

        int selected = menu.getCurrentEntry();

        for (int i = 0; i < menu.getNumberEntries(); i++) {
            if (selected == i)
                gui.drawText('>' + map.get(menu.getEntry(i)), new Position(5 - 1,15 + 3 * i), "#FFFF00");
            else {
                gui.drawText(map.get(menu.getEntry(i)), new Position(5, 15 + 3 * i), "#FFFFFF");
            }
        }

        gui.refresh();
    }
}

package g50.view.menu;

import g50.gui.GUI;
import g50.model.element.Position;
import g50.model.menu.MainMenu;
import g50.view.ViewProperty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenuViewer extends MenuViewer {
    protected List<ViewProperty> elementViewers;

    public MainMenuViewer(MainMenu menu) {
        super(menu);
        this.elementViewers = new ArrayList<>();
        initViewerBuilder();
    }

    public void initViewerBuilder() {
        this.elementViewers.add(new ViewProperty("#DEA185",(char)(199)));
        this.elementViewers.add(new ViewProperty("#DEA185",(char)(201)));
        this.elementViewers.add(new ViewProperty("#FFFF00",(char)(193)));
        this.elementViewers.add(new ViewProperty("#FF0000",(char)(200)));
        this.elementViewers.add(new ViewProperty("#FFB8FF",(char)(200)));
        this.elementViewers.add(new ViewProperty("#00FFFF",(char)(200)));
        this.elementViewers.add(new ViewProperty("#FFB852",(char)(200)));
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();

        gui.drawText((getModel()).getTitle(),new Position(10,5), "#FFFF00");


        for (int i = 0; i < elementViewers.size(); i++)
            gui.drawText(String.valueOf(elementViewers.get(i).getCharacter()),
                    new Position(10+i, 8),
                    elementViewers.get(i).getColor());

        int selected = getModel().getCurrentEntry();

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            if (selected == i)
                gui.drawText('>' + map.get(getModel().getEntry(i)), new Position(5 - 1,15 + 3 * i), "#FFFF00");
            else {
                gui.drawText(map.get(getModel().getEntry(i)), new Position(5, 15 + 3 * i), "#FFFFFF");
            }
        }

        gui.refresh();
    }
}

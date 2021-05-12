package g50.view.menu;

import g50.gui.GUI;
import g50.model.Position;
import g50.model.element.fixed.collectable.PowerPellet;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;
import g50.model.menu.Menu;
import g50.view.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class MenuViewer<T> {
    protected List<ElementViewer> elementViewers;
    protected GUI gui;
    public MenuViewer(GUI gui) {
        this.elementViewers = new ArrayList<>();
        this.gui = gui;
        initViewerBuilder(gui);
    }

    public abstract void initViewerBuilder(GUI gui);

    public abstract void draw(T menu) throws IOException;
}

package g50.view.menu;

import g50.gui.GUI;
import g50.model.Position;
import g50.model.element.fixed.collectable.PowerPellet;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;
import g50.model.menu.Menu;
import g50.view.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MenuViewer<T> {

    protected static final Map<Menu.ENTRIES, String> map = new HashMap<>() {{
        put(Menu.ENTRIES.START, "START");
        put(Menu.ENTRIES.CONTROLS, "CONTROLS");
        put(Menu.ENTRIES.CREDITS, "CREDITS");
        put(Menu.ENTRIES.HIGH_SCORE, "HIGH-SCORE");
        put(Menu.ENTRIES.EXIT, "EXIT");
        put(Menu.ENTRIES.RESUME, "RESUME");
        put(Menu.ENTRIES.RETURN_ENTER, "PRESS ENTER TO RETURN");
    }};

    public abstract void draw(GUI gui, T menu) throws IOException;
}

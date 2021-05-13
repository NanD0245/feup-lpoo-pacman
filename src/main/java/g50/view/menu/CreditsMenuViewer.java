package g50.view.menu;

import g50.gui.GUI;
import g50.model.Position;
import g50.model.menu.CreditsMenu;

import java.io.IOException;

public class CreditsMenuViewer extends MenuViewer<CreditsMenu> {
    @Override
    public void draw(GUI gui, CreditsMenu menu) throws IOException {
        gui.clear();

        gui.drawText(menu.getTitle(), new Position(10, 5), "#FFFF00");

        /*gui.drawText("\t\t LPOO 20/21", new Position(0, 10));

        gui.drawText("PROGRAMMERS & PRODUCERS:", new Position(2, 15));

        gui.drawText("Bruno Mendes", new Position(8, 19));

        gui.drawText("Nuno Costa", new Position(9, 22));

        gui.drawText("Fernando Rego", new Position(7, 25));*/

        for (int i = 0; i < menu.getLinesNumber(); i++) {
            if (!menu.getText(i).equals(""))
                gui.drawText(menu.getText(i), new Position(0, 10 + i));
        }

        gui.drawBlinkText(map.get(menu.getEntry(0)), new Position(3, 30), "#FFFF00");

        gui.refresh();
    }
}

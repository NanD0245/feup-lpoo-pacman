package g50.view;

import g50.gui.GUI;
import g50.model.Position;

public class ElementViewer {
    private final GUI gui;
    private final ViewProperty viewProperty;

    public ElementViewer(GUI gui, ViewProperty viewProperty) {
        this.gui = gui;
        this.viewProperty = viewProperty;
    }

    void draw(Position position){
        this.gui.drawCharacter(this.viewProperty.getCharacter(), position, this.viewProperty.getColor());
    }
}

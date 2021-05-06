package g50.view;

import g50.gui.GUI;
import g50.model.Position;

public abstract class ElementViewer {
    private final String color;
    private final char character;
    private final GUI gui;

    public ElementViewer(GUI gui, String color, char character) {
        this.color = color;
        this.character = character;
        this.gui = gui;
    }

    public ElementViewer(GUI gui, char character){
        this(gui, "#FFFFFF", character);
    }

    void draw(Position position){
        this.gui.drawCharacter(this.character, position, this.color);
    }
}

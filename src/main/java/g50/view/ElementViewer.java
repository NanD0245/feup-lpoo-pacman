package g50.view;

import g50.gui.GUI;
import g50.model.Position;

import java.util.Objects;

public class ElementViewer {
    private final GUI gui;
    private final ViewProperty viewProperty;

    public ElementViewer(GUI gui, ViewProperty viewProperty) {
        this.gui = gui;
        this.viewProperty = viewProperty;
    }

    public void draw(Position position){
        this.gui.drawCharacter(this.viewProperty.getCharacter(), position, this.viewProperty.getColor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementViewer that = (ElementViewer) o;
        return gui.equals(that.gui) && viewProperty.equals(that.viewProperty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gui, viewProperty);
    }
}

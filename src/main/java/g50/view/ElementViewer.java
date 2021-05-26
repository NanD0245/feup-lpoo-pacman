package g50.view;

import g50.gui.GUI;
import g50.model.Position;
import g50.model.element.Element;

import java.util.Objects;

public class ElementViewer extends Viewer<Element> {
    private final ViewProperty viewProperty;

    public ElementViewer(Element element, ViewProperty viewProperty) {
        super(element);
        this.viewProperty = viewProperty;
    }

    @Override
    public void draw(GUI gui) {
        gui.drawCharacter(this.viewProperty.getCharacter(), getModel().getPosition(), this.viewProperty.getColor());
    }
}

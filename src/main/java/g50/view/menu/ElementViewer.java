package g50.view.menu;

import g50.gui.GUI;
import g50.model.element.Element;

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

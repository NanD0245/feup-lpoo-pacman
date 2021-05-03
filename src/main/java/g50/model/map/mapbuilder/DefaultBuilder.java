package g50.model.map.mapbuilder;

import g50.model.element.Element;

import java.util.List;

public class DefaultBuilder implements MapBuilder {

    private static final String defaultFilename = "resources/maps/default.txt";

    @Override
    public List<List<Element>> getBuild() {
        return new FileBuilder(defaultFilename).getBuild();
    }
}

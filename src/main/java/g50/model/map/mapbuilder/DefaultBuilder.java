package g50.model.map.mapbuilder;

import g50.model.element.Element;
import g50.model.element.fixed.FixedElement;

import java.io.IOException;
import java.util.List;

public class DefaultBuilder implements MapBuilder {

    private static final String defaultFilename = "resources/maps/default.txt";
    private static final FileBuilder builder = new FileBuilder(defaultFilename);

    @Override
    public List<List<FixedElement>> getBuild() throws IOException {
        return builder.getBuild();
    }
}

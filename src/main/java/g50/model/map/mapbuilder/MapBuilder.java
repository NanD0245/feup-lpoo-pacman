package g50.model.map.mapbuilder;

import g50.model.element.Element;
import g50.model.element.fixed.FixedElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface MapBuilder{
    public List<List<FixedElement>> getBuild() throws IOException;
}

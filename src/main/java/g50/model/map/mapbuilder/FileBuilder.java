package g50.model.map.mapbuilder;

import g50.model.element.Element;

import java.util.List;

public class FileBuilder implements MapBuilder {

    private String filename;

    public FileBuilder(String filename){
        this.filename = filename;
    }

    

    @Override
    public List<List<Element>> getBuild() {
        return null;
    }
}

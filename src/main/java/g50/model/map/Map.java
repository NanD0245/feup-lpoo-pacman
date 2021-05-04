package g50.model.map;

import g50.model.element.Element;
import g50.model.element.fixed.FixedElement;
import g50.model.map.mapbuilder.DefaultBuilder;
import g50.model.map.mapbuilder.FileBuilder;
import g50.model.map.mapbuilder.MapBuilder;

import java.io.IOException;
import java.util.List;

public class Map {

    private MapBuilder builder;
    private List<List<FixedElement>> map;

    public Map(){
        this.builder = new DefaultBuilder();
    }

    public Map(String filename){
        this.builder = new FileBuilder(filename);
    }

    public void build() throws IOException {
        map = this.builder.getBuild();
    }

    public List<List<FixedElement>> getMap(){
        return this.map;
    }

}

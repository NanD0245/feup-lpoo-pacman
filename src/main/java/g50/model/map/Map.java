package g50.model.map;

import g50.model.element.Element;
import g50.model.map.mapbuilder.DefaultBuilder;
import g50.model.map.mapbuilder.FileBuilder;
import g50.model.map.mapbuilder.MapBuilder;

import java.util.List;

public class Map {

    private MapBuilder builder;
    private List<List<Element>> map;

    public Map(){
        this.builder = new DefaultBuilder();
    }

    public Map(String filename){
        this.builder = new FileBuilder(filename);
    }

    public void build(){
        map = this.builder.getBuild();
    }

}

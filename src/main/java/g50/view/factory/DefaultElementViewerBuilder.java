package g50.view.factory;

import g50.model.element.fixed.collectable.PacDot;
import g50.model.element.fixed.collectable.PowerPellet;
import g50.model.element.fixed.nonCollectable.*;
import g50.view.ViewProperty;

public class DefaultElementViewerBuilder extends ElementViewerBuilder {
    public DefaultElementViewerBuilder(){
        super();

        this.properties.put(Wall.class, new ViewProperty("#0000FF",(char)(203)));
        this.properties.put(EmptySpace.class, new ViewProperty(' '));
        this.properties.put(PacDot.class, new ViewProperty("#DEA185",(char)(201)));
        this.properties.put(PowerPellet.class, new ViewProperty("#DEA185",(char)(199)));
        this.properties.put(SpawnArea.class, new ViewProperty(' '));
        this.properties.put(Door.class, new ViewProperty(' '));
        this.properties.put(Target.class, new ViewProperty(' '));
    }
}

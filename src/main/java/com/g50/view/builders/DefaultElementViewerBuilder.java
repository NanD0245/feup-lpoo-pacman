package com.g50.view.builders;

import com.g50.model.element.fixed.collectable.PacDot;
import com.g50.model.element.fixed.collectable.PowerPellet;
import com.g50.model.element.fixed.collectable.fruit.*;
import com.g50.model.element.fixed.noncollectable.*;
import com.g50.view.ViewProperty;

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
        this.properties.put(Apple.class, new ViewProperty("#FF0000",(char)(145)));
        this.properties.put(Cherry.class, new ViewProperty("#FF0000",(char)(143)));
        this.properties.put(Orange.class, new ViewProperty("#FFA500",(char)(146)));
        this.properties.put(Strawberry.class, new ViewProperty("#FF0000",(char)(144)));
        this.properties.put(Manjaro.class, new ViewProperty("#32b858", (char)(147)));
    }
}

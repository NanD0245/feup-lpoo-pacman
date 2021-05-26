package g50.view;

import g50.model.element.fixed.collectable.PacDot;
import g50.model.element.fixed.collectable.PowerPellet;
import g50.model.element.fixed.nonCollectable.*;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.*;

public class DefaultElementViewerBuilder extends ElementViewerBuilder {
    public DefaultElementViewerBuilder(){
        super();
        this.properties.put(PacMan.class, new ViewProperty("#FFFF00", 'Á' ));
        this.properties.put(Ghost.class, new ViewProperty('G'));
        this.properties.put(BlinkyGhost.class, new ViewProperty("#FF0000", 'È'));
        this.properties.put(ClydeGhost.class, new ViewProperty("#00FFFF", 'È'));
        this.properties.put(PinkyGhost.class, new ViewProperty("#FFB8FF", 'È'));

        this.properties.put(InkyGhost.class, new ViewProperty("#FFB852", 'È'));

        this.properties.put(Wall.class, new ViewProperty("#0000FF",'Ë'));
        this.properties.put(EmptySpace.class, new ViewProperty(' '));
        this.properties.put(PacDot.class, new ViewProperty("#DEA185",'É'));
        this.properties.put(PowerPellet.class, new ViewProperty("#DEA185",'Ç'));
        this.properties.put(SpawnArea.class, new ViewProperty(' '));
        this.properties.put(Door.class, new ViewProperty(' '));
        this.properties.put(Target.class, new ViewProperty(' '));
    }
}

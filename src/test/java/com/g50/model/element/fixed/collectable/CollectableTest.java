package com.g50.model.element.fixed.collectable;
import com.g50.model.element.Position;
import com.g50.model.element.fixed.collectable.fruit.Strawberry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollectableTest {

    private Position pos;
    Strawberry fruit;
    PacDot pacDot;
    PowerPellet powerPellet;

    @BeforeEach
    public void initTest() {
        int x = 10;
        int y = 15;
        pos = new Position(x, y);
        fruit = new Strawberry(pos);
        pacDot =  new PacDot(pos);
        powerPellet = new PowerPellet(pos);
    }

    @Test
    public void verifyTriggersEffect(){
        Assertions.assertEquals(fruit.triggersEffect(), CollectableTriggers.BONUS);
        Assertions.assertEquals(pacDot.triggersEffect(), CollectableTriggers.COLLECT);
        Assertions.assertEquals(powerPellet.triggersEffect(), CollectableTriggers.FRIGHTEN);
    }
}

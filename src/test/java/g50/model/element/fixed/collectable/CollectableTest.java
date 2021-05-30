package g50.model.element.fixed.collectable;
import g50.model.element.Position;
import g50.model.element.fixed.collectable.fruit.Strawberry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollectableTest {

    private Position pos;

    @BeforeEach
    public void initTest() {
        int x = 10;
        int y = 15;
        pos = new Position(x, y);
    }

    @Test
    public void verifyTriggersEffect(){
        Strawberry fruit = new Strawberry(pos);
        Assertions.assertEquals(fruit.triggersEffect(), CollectableTriggers.BONUS);
        PacDot pacDot =  new PacDot(pos);
        Assertions.assertEquals(pacDot.triggersEffect(), CollectableTriggers.COLLECT);
        PowerPellet powerPellet = new PowerPellet(pos);
        Assertions.assertEquals(powerPellet.triggersEffect(), CollectableTriggers.FRIGHTEN);
    }
}

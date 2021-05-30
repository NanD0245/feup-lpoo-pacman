package g50.model.element.movable.ghost;
import g50.model.element.Position;
import g50.model.element.fixed.noncollectable.Target;
import g50.model.element.movable.Orientation;
import g50.states.GhostState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

public class GhostTest {

    @Test
    public void resetTest(){
        BlinkyGhost blinkyGhost = new BlinkyGhost("blinky", new Position(1,1), Orientation.DOWN, new Target(new Position(1,1), "blinkyTarget"));
        blinkyGhost.setState(GhostState.DEAD);
        Assertions.assertEquals(blinkyGhost.getState(), GhostState.DEAD);

        blinkyGhost.setOrientation(Orientation.UP);
        Assertions.assertEquals(blinkyGhost.getOrientation(), Orientation.UP);

        blinkyGhost.getStrategy().setDotLimit(432);
        Assertions.assertEquals(blinkyGhost.getStrategy().getDotLimit(), 432);

        blinkyGhost.setFramesPerPosition(3);
        Assertions.assertEquals(blinkyGhost.getFramesPerPosition(), 3);

        blinkyGhost.reset();

        Assertions.assertEquals(blinkyGhost.getState(), GhostState.SCATTER);
        Assertions.assertEquals(blinkyGhost.getOrientation(), Orientation.DOWN);
        Assertions.assertEquals(blinkyGhost.getStrategy().getDotLimit(), 0);
        Assertions.assertEquals(blinkyGhost.getFramesPerPosition(), 10);

        ClydeGhost clydeGhost = new ClydeGhost("clyde", new Position(1,1), Orientation.DOWN, new Target(new Position(1,1), "clydeTarget"));

        clydeGhost.setState(GhostState.SCATTER);
        Assertions.assertEquals(clydeGhost.getState(), GhostState.SCATTER);

        clydeGhost.reset();
        Assertions.assertEquals(clydeGhost.getState(), GhostState.IN_CAGE);
    }
}

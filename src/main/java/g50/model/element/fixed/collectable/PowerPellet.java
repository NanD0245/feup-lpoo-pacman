package g50.model.element.fixed.collectable;

import g50.model.Position;
import g50.model.element.fixed.FixedElement;

public class PowerPellet extends Collectable {
    public PowerPellet(Position position) {
        super(position,50);
    }

    @Override
    public FixedElement generate(Position position) {
        return new PowerPellet(position);
    }

}

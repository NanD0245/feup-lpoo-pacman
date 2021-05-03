package g50.model.element.movable;

import java.util.Map;

public enum Orientation {
    UP,DOWN,LEFT,RIGHT;

    private static final Map<Orientation, Orientation> map = Map.of(
            UP, DOWN,
            DOWN, UP,
            LEFT, RIGHT,
            RIGHT, LEFT
    );

    public Orientation getOpposite() {
        return map.get(this);
    }
}

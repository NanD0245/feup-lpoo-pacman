package g50.model;

import g50.model.element.Position;
import g50.model.element.fixed.collectable.Collectable;
import g50.model.element.fixed.collectable.fruit.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level {

    private final int level;

    private static final Map<Integer, Class<? extends Collectable>> fruit = new HashMap<>() {{
        put(1, Cherry.class);
        put(2, Strawberry.class);
        put(3, Orange.class);
        put(4, Orange.class);
        put(5, Apple.class);
        put(6, Apple.class);
    }};

    private static final Map<Integer, Integer> pacManFramesPerMovement = new HashMap<>(){{
        put(1, 12);
        put(2, 10);
        put(3, 10);
        put(4, 10);
    }};

    private static final Map<Integer, Integer> ghostFramesPerMovement =  new HashMap<>() {{
        put(1, 17);
        put(2, 15);
        put(3, 12);
        put(4, 12);
    }};

    private static final Map<Integer, Integer> frightenedGhostFramesPerMovement = new HashMap<>() {{
       put(1, 30);
       put(2, 27);
       put(3, 27);
       put(4, 27);
    }};

    private static final Map<Integer, List<Integer>> gameStateIntervals = new HashMap<>() {{
       put(1, Arrays.asList(7,20,7,20,5,20,5));
       put(2, Arrays.asList(7,20,7,20,5,1033,1));
       put(3, Arrays.asList(7,20,7,20,5,1033,1));
       put(4, Arrays.asList(7,20,7,20,5,1033,1));
    }};


    Level(int level){
        this.level = level;
    }

    public Fruit getFruit(Position pos) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (Fruit) fruit.getOrDefault(level, Apple.class).getConstructor(Position.class).newInstance(pos);
    }

    public int getPacManFramesPerMovement(){
        return pacManFramesPerMovement.getOrDefault(level, 7);
    }

    public int getGhostFramesPerMovement() {
        return ghostFramesPerMovement.getOrDefault(level, 10);
    }

    public int getFrightenedGhostFramesPerMovement() { return frightenedGhostFramesPerMovement.getOrDefault(level, 25); }

    public List<Integer> getGameStateIntervals() {
        return gameStateIntervals.getOrDefault(level,Arrays.asList(5,20,5,20,5,1037,1));
    }
}

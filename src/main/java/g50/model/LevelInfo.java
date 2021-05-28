package g50.model;

import g50.model.element.fixed.collectable.Collectable;
import g50.model.element.fixed.collectable.fruit.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelInfo {

    private final int level;

    private static final Map<Integer, Class<? extends Collectable>> fruit = new HashMap<>() {{
        put(1, Cherry.class);
        put(2, Strawberry.class);
        put(3, Orange.class);
        put(4, Orange.class);
        put(5, Apple.class);
        put(6, Apple.class);
        put(7, Melon.class);
        put(8, Melon.class);
        put(9, GalaxianBoss.class);
        put(10, GalaxianBoss.class);
    }};

    private static final Map<Integer, Integer> pacManFramesPerMovement = new HashMap<>(){{
        put(1, 25);
        put(2, 20);
        put(3, 20);
        put(4, 20);
    }};

    private static final Map<Integer, Integer> ghostFramesPerMovement =  new HashMap<>() {{
        put(1, 35);
        put(2, 30);
        put(3, 25);
        put(4, 25);
    }};

    private static final Map<Integer, Integer> frightnedGhostFramesPerMovement = new HashMap<>() {{
       put(1, 60);
       put(2, 55);
       put(3, 55);
       put(4, 55);
    }};

    private static final Map<Integer, List<Integer>> gameStateIntervals = new HashMap<>() {{
       put(1, Arrays.asList(7,20,7,20,5,20,5));
       put(2, Arrays.asList(7,20,7,20,5,1033,1));
       put(3, Arrays.asList(7,20,7,20,5,1033,1));
       put(4, Arrays.asList(7,20,7,20,5,1033,1));
    }};


    LevelInfo(int level){
        this.level = level;
    }

    public Fruit getFruit(Position pos) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (Fruit) fruit.getOrDefault(level, Key.class).getConstructor(Position.class).newInstance(pos);
    }

    public int getPacManFramesPerMovement(){
        return pacManFramesPerMovement.getOrDefault(level, 15);
    }

    public int getGhostFramesPerMovement() {
        return ghostFramesPerMovement.getOrDefault(level, 20);
    }

    public int getFrightnedGhostFramesPerMovement() { return frightnedGhostFramesPerMovement.getOrDefault(level, 50); }

    public List<Integer> getGameStateIntervals() {
        return gameStateIntervals.getOrDefault(level,Arrays.asList(5,20,5,20,5,1037,1));
    }
}

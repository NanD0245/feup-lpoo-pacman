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

    private final int levelNumber;

    private static final Map<Integer, Class<? extends Collectable>> fruit = new HashMap<>() {{
        put(1, Cherry.class);
        put(2, Strawberry.class);
        put(3, Orange.class);
        put(4, Orange.class);
        put(5, Apple.class);
        put(6, Apple.class);
    }};

    private static final Map<Integer, Integer> pacManFramesPerMovement = new HashMap<>(){{
        put(1, 8);
        put(2, 6);
        put(3, 6);
        put(4, 6);
    }};

    private static final Map<Integer, Integer> ghostFramesPerMovement =  new HashMap<>() {{
        put(1, 9);
        put(2, 7);
        put(3, 7);
        put(4, 7);
    }};

    private static final Map<Integer, Integer> frightenedGhostFramesPerMovement = new HashMap<>() {{
       put(1, 14);
       put(2, 13);
       put(3, 13);
       put(4, 13);
    }};

    private static final Map<Integer, List<Integer>> gameStateIntervals = new HashMap<>() {{
       put(1, Arrays.asList(7,20,7,20,5,20,5));
       put(2, Arrays.asList(7,20,7,20,5,1033,1));
       put(3, Arrays.asList(7,20,7,20,5,1033,1));
       put(4, Arrays.asList(7,20,7,20,5,1033,1));
    }};


    Level(int level){
        this.levelNumber = level;
    }

    public Fruit getFruit(Position pos) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (Fruit) fruit.getOrDefault(levelNumber, Apple.class).getConstructor(Position.class).newInstance(pos);
    }

    public int getPacManFramesPerMovement(){
        return pacManFramesPerMovement.getOrDefault(levelNumber, 4);
    }

    public int getGhostFramesPerMovement() {
        return ghostFramesPerMovement.getOrDefault(levelNumber, 5);
    }

    public int getFrightenedGhostFramesPerMovement() { return frightenedGhostFramesPerMovement.getOrDefault(levelNumber, 12); }

    public List<Integer> getGameStateIntervals() {
        return gameStateIntervals.getOrDefault(levelNumber,Arrays.asList(5,20,5,20,5,1037,1));
    }

    public int getLevelNumber() {
        return levelNumber;
    }
}

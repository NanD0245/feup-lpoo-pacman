package g50.model.map.mapbuilder;

import g50.model.element.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.PacDot;
import g50.model.element.fixed.collectable.PowerPellet;
import g50.model.element.fixed.noncollectable.*;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.*;
import g50.model.element.movable.ghost.strategy.InkyStrategy;
import g50.model.map.GameMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class GameMapBuilder {
    private final String filename;
    private BufferedReader buffer;
    private static final Map<Character, FixedElement> charToElement = Map.of(
            ' ', new EmptySpace(new Position(-1,-1)),
            '@', new PowerPellet(new Position(-1,-1)),
            'W', new Wall(new Position(-1,-1)),
            'P', new PacDot(new Position(-1,-1)),
            '1', new Target(new Position(-1,-1), "Pinky"),
            '2', new Target(new Position(-1,-1), "Blinky"),
            '3', new Target(new Position(-1,-1), "Clyde"),
            '4', new Target(new Position(-1,-1), "Inky"),
            'D', new Door(new Position(-1,-1)),
            'S', new SpawnArea(new Position(-1,-1))
    );
    private static final Map<String, Class <? extends Ghost>> ghostClass = new HashMap<>(){{
        put("Blinky", BlinkyGhost.class);
        put("Clyde", ClydeGhost.class);
        put("Inky", InkyGhost.class);
        put("Pinky", PinkyGhost.class);
    }};

    GameMapBuilder(String filename){
        this.filename = filename;
    }

    public GameMap getBuild() throws IOException {
        List<Ghost> ghosts = new ArrayList<>();
        PacMan pacman = new PacMan(new Position(5, 5));
        this.buffer = new BufferedReader(new FileReader(this.filename));
        List<List<FixedElement>> map = new ArrayList<>();
        Position startPos = null, fruitPos = null;
        try{
            String gridSize = this.buffer.readLine();
            String[] values = gridSize.split("x", 2);
            int columns = Integer.parseInt(values[0]), rows = Integer.parseInt(values[1]);
            Map<String, Target> targets = new HashMap<>();
            map = generateMap(targets, rows, columns);
            startPos = startUpEntities(pacman, ghosts, targets);
            fruitPos = new Position(startPos.getX(), startPos.getY() + 6);
            this.generateWallBitMask(map);
            this.buffer.close();
        } catch (Exception e){
            System.out.println("File not compatible with map generation. More details below.");
            System.out.println(e.getMessage());
        }
        return new GameMap(map, ghosts, pacman, startPos, fruitPos);
    }

    private List<List<FixedElement>> generateMap(Map<String, Target> targets, int rows, int columns) throws IOException {
        List<List<FixedElement>> fixedElementsMap = new ArrayList<>();
        String c;

        for(int row = 0; row < rows ; row++){
            c = this.buffer.readLine();
            List<FixedElement> fixedElementsRow = new ArrayList<>();
            for(int column = 0; column < columns; column++){

                char charAt = ' ';
                if (column < c.length()) charAt = c.charAt(column);
                FixedElement newElement = charToElement.get(charAt).generate(new Position(column, row));
                fixedElementsRow.add(newElement);
                if (newElement instanceof Target) {
                    Target target = (Target) newElement;
                    targets.put(target.getTargetName(), target);
                }
            }
            fixedElementsMap.add(fixedElementsRow);
        }
        return fixedElementsMap;
    }

    private Position startUpEntities(PacMan pacman, List<Ghost> ghosts, Map<String, Target> targets)
            throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        while((!this.buffer.readLine().equals("SPAWN POSITIONS")));

        String c = this.buffer.readLine();
        String[] entityCoords = c.split(" ", 2);
        int x = Integer.parseInt(entityCoords[0]), y = Integer.parseInt(entityCoords[1]);
        Position startPos = new Position(x,y);
        BlinkyGhost blinkyGhost = null;
        while((c = this.buffer.readLine()) != null && c.length() > 0){
            entityCoords = c.split(" ", 3);
            String name = entityCoords[0];
            x = Integer.parseInt(entityCoords[1]);
            y = Integer.parseInt(entityCoords[2]);
            if(entityCoords[0].equalsIgnoreCase("PacMan")) pacman.setPosition(new Position(x,y));
            else {
                Constructor<? extends Ghost> ghostConstructor = ghostClass.containsKey(entityCoords[0]) ?
                        ghostClass.get(entityCoords[0]).getConstructor(String.class,
                                Position.class, Orientation.class, Target.class) : null;
                if (ghostConstructor == null){
                    ghosts.add(new BlinkyGhost(name, new Position(x, y), Orientation.UP, targets.get(name)));
                } else {
                    Ghost ghost = ghostConstructor.newInstance(name, new Position(x, y), Orientation.UP, targets.get(name));
                    ghosts.add(ghost);
                    if (ghost instanceof BlinkyGhost) {
                        blinkyGhost = (BlinkyGhost) ghost;
                    } else if (ghost instanceof InkyGhost){
                        ((InkyStrategy)ghost.getStrategy()).setBlinkyGhost(blinkyGhost);
                    }
                }
            }
        }
        return startPos;
    }

    private void generateWallBitMask(List<List<FixedElement>> map) {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) instanceof Wall) {
                    if ((i == 15 && j == 0) || (i == 15 && j == map.get(i).size()-1) ||
                            (i == 17 && j == 0) || (i == 17 && j == map.get(i).size()-1)) {
                        ((Wall) map.get(i).get(j)).setBitmask(1);
                        ((Wall) map.get(i).get(j)).setBitmask(3);
                    } else {
                        if ((i == 0) || !(map.get(i - 1).get(j) instanceof Wall))
                            ((Wall) map.get(i).get(j)).setBitmask(1);

                        if (j==0 || !(map.get(i).get(j - 1) instanceof Wall))
                            ((Wall) map.get(i).get(j)).setBitmask(0);

                        if ((i == map.size()-1) || !(map.get(i + 1).get(j) instanceof Wall))
                            ((Wall) map.get(i).get(j)).setBitmask(3);

                        if ((j == map.get(i).size() - 1) || !(map.get(i).get(j + 1) instanceof Wall))
                            ((Wall) map.get(i).get(j)).setBitmask(2);
                    }
                }
            }
        }
    }
}

package g50.model.map.mapbuilder;

import g50.model.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.PacDot;
import g50.model.element.fixed.collectable.PowerPellet;
import g50.model.element.fixed.nonCollectable.*;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.*;
import g50.model.map.GameMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class GameMapBuilder {

    private String filename;
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

    private static final Map<String, Class <? extends Ghost>> getGhost = new HashMap<>(){{
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
        PacMan pacman = new PacMan(new Position(-1, -1));
        this.buffer = new BufferedReader(new FileReader(this.filename));
        List<List<FixedElement>> map = new ArrayList<>();
        Position startPos = null;
        try{
            String gridSize = this.buffer.readLine();
            String[] values = gridSize.split("x", 2);

            int columns = Integer.parseInt(values[0]), rows = Integer.parseInt(values[1]);
            Map<String, Target> targets = new HashMap<>();

            map = generateMap(targets, rows, columns);

            startPos = startUpEntities(pacman, ghosts, targets);

            generateWallBitMask(map);

            this.buffer.close();

        } catch (Exception e){
            System.out.println("File not compatible with map generation. More details below.");
            System.out.println(e.getMessage());
        }

        return new GameMap(map, ghosts, pacman, startPos);
    }

    private List<List<FixedElement>> generateMap(Map<String, Target> targets, int rows, int columns) throws IOException {
        List<List<FixedElement>> fixedElementsMap = new ArrayList<>();
        String c;

        for(int row = 0; row < rows ; row++){
            c = this.buffer.readLine();
            List<FixedElement> fixedElementsRow = new ArrayList<>();
            for(int column = 0; column < columns; column++){

                char charAt = ' ';
                if(column < c.length())
                    charAt = c.charAt(column);

                // Gets the generator of the correspondent char
                FixedElement newElement = charToElement.get(charAt).generate(new Position(column, row));
                fixedElementsRow.add(newElement);

                // Sets a target name to the target location on a map for
                // ghost target generation
                if(newElement instanceof Target) {
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

        String c;

        while((!this.buffer.readLine().equals("SPAWN POSITIONS")));
        c = this.buffer.readLine();
        String[] entityCoords = c.split(" ", 2);
        int x = Integer.parseInt(entityCoords[0]), y = Integer.parseInt(entityCoords[1]);
        Position startPos = new Position(x,y);
        while((c = this.buffer.readLine()) != null && c.length() > 0){
            entityCoords = c.split(" ", 3);
            String name = entityCoords[0];
            x = Integer.parseInt(entityCoords[1]);
            y = Integer.parseInt(entityCoords[2]);
            if(entityCoords[0].equalsIgnoreCase("PacMan")) pacman.setPosition(new Position(x,y));
            else {
                Constructor ghostConstructor = null;
                if (getGhost.containsKey(entityCoords[0]))
                    ghostConstructor = getGhost.get(entityCoords[0]).getConstructor(String.class, Position.class, Orientation.class, Target.class);
                if (ghostConstructor == null)
                    ghosts.add(new BlinkyGhost(name, new Position(x, y), Orientation.UP, targets.get(name)));
                else {
                    ghosts.add((Ghost) ghostConstructor.newInstance(name, new Position(x, y), Orientation.UP, targets.get(name)));
                }
            }
        }
        return startPos;
    }

    private void generateWallBitMask(List<List<FixedElement>> map) {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) instanceof Wall) {
                    if ((i == 15 && j == 0) || (i == 15 && j == map.get(i).size()-1) || (i == 17 && j == 0) || (i == 17 && j == map.get(i).size()-1)) {
                        ((Wall) map.get(i).get(j)).setBitmask(1);
                        ((Wall) map.get(i).get(j)).setBitmask(3);
                    } else {
                        if (!(map.get(i - 1).get(j) instanceof Wall))
                            ((Wall) map.get(i).get(j)).setBitmask(1);

                        if (j==0 || !(map.get(i).get(j - 1) instanceof Wall))
                            ((Wall) map.get(i).get(j)).setBitmask(0);

                        if (!(map.get(i + 1).get(j) instanceof Wall))
                            ((Wall) map.get(i).get(j)).setBitmask(3);

                        if ((j == map.get(i).size() - 1) || !(map.get(i).get(j + 1) instanceof Wall))
                            ((Wall) map.get(i).get(j)).setBitmask(2);
                    }
                }
            }
        }
    }
}

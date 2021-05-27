package g50.model.map.mapbuilder;

import g50.model.element.movable.ghost.strategy.GhostStrategy;
import g50.controller.states.GhostState;
import g50.model.element.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.PacDot;
import g50.model.element.fixed.collectable.PowerPellet;
import g50.model.element.fixed.nonCollectable.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        try{
            String gridSize = this.buffer.readLine();
            String[] values = gridSize.split("x", 2);
            int columns = Integer.parseInt(values[0]), rows = Integer.parseInt(values[1]);
            Map<String, Target> targets = new HashMap<>();
            map = generateMap(targets, rows, columns);
            startUpEntities(pacman, ghosts, targets);
            this.buffer.close();
        } catch (Exception e){
            System.out.println("File not compatible with map generation. More details below.");
            System.out.println(e.getMessage());
        }
        return new GameMap(map, ghosts, pacman);
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

    private void startUpEntities(PacMan pacman, List<Ghost> ghosts, Map<String, Target> targets)
            throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        String c;

        while((!this.buffer.readLine().equals("SPAWN POSITIONS")));

        BlinkyGhost blinkyGhost = null;
        while((c = this.buffer.readLine()) != null && c.length() > 0){
            String[] entityCoords = c.split(" ", 3);
            System.out.println(entityCoords);
            String name = entityCoords[0];
            int x = Integer.parseInt(entityCoords[1]);
            int y = Integer.parseInt(entityCoords[2]);
            if (entityCoords[0].equalsIgnoreCase(("PacMan"))){
                pacman.setPosition(new Position(x,y));
                System.out.println(x);
                System.out.println(y);
            }
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
    }
}

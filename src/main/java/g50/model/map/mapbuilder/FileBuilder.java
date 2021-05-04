package g50.model.map.mapbuilder;

import g50.model.element.Element;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.PacDot;
import g50.model.element.fixed.collectable.PowerPellet;
import g50.model.element.fixed.nonCollectable.*;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileBuilder implements MapBuilder {

    private String filename;
    private BufferedReader buffer;
    private static final Map<Character, FixedElement> charToElement;
    static{
        charToElement = new HashMap<>();
        charToElement.put(' ', new Empty(-1, -1));
        charToElement.put('@', new PowerPellet(-1,-1));
        charToElement.put('W', new Wall(-1,-1));
        charToElement.put('P', new PacDot(-1,-1));
        charToElement.put('1', new Target(-1, -1, "Pinky"));
        charToElement.put('2', new Target(-1, -1, "Blinky"));
        charToElement.put('3', new Target(-1, -1, "Clyde"));
        charToElement.put('4', new Target(-1, -1, "Inky"));
        charToElement.put('D', new Door(-1, -1));
        charToElement.put('S', new SpawnArea(-1, -1));
    }

    public FileBuilder(String filename){
        this.filename = filename;
    }

    @Override
    public List<List<FixedElement>> getBuild(PacMan pacman, List<Ghost> ghosts) throws IOException {
        this.buffer = new BufferedReader(new FileReader(this.filename));
        List<List<FixedElement>> fixedElementsMap = new ArrayList<>();
        try{
            String gridSize = this.buffer.readLine();
            String[] values = gridSize.split("x", 2);

            int columns = Integer.parseInt(values[0]), rows = Integer.parseInt(values[1]);

            String c;
            Map<String, Target> targets = new HashMap<>();

            for(int row = 0; row < rows ; row++){
                c = this.buffer.readLine();
                List<FixedElement> fixedElementsRow = new ArrayList<>();
                for(int column = 0; column < columns; column++){

                    char charAt = ' ';
                    if(column < c.length())
                        charAt = c.charAt(column);

                    // Gets the generator of the correspondent char
                    FixedElement newElement = charToElement.get(charAt).generate(column, row);
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

            while((!this.buffer.readLine().equals("SPAWN POSITIONS")));

            while((c = this.buffer.readLine()) != null && c.length() > 0){
                String[] entityCoords = c.split(" ", 3);
                int x = Integer.parseInt(entityCoords[1]), y = Integer.parseInt(entityCoords[2]);
                if(entityCoords[0].equals("PacMan")) pacman.setCoordinates(x,y);
                else ghosts.add(new Ghost(entityCoords[0], x, y, Orientation.UP, targets.get(entityCoords[0])));
            }

            this.buffer.close();

        } catch (Exception e){
            System.out.println("File not compatible with map generation. More details below.");
            System.out.println(e.getMessage());
        }

        return fixedElementsMap;
    }
}

package g50.model.map.mapbuilder;

import g50.model.element.Element;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.PacDot;
import g50.model.element.fixed.collectable.PowerPellet;
import g50.model.element.fixed.nonCollectable.*;

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
        charToElement.put('1', new Target(-1, -1, "PinkyTarget"));
        charToElement.put('2', new Target(-1, -1, "BlinkyTarget"));
        charToElement.put('3', new Target(-1, -1, "ClydeTarget"));
        charToElement.put('4', new Target(-1, -1, "InkyTarget"));
        charToElement.put('D', new Door(-1, -1));
        charToElement.put('S', new SpawnArea(-1, -1));
    }

    public FileBuilder(String filename){
        this.filename = filename;
    }

    @Override
    public List<List<FixedElement>> getBuild() throws IOException {
        this.buffer = new BufferedReader(new FileReader(this.filename));
        List<List<FixedElement>> fixedElementsMap = new ArrayList<>();
        try{
            String gridSize = this.buffer.readLine();
            String[] values = gridSize.split("x", 2);

            int columns = Integer.parseInt(values[0]), rows = Integer.parseInt(values[1]);

            String c;


            for(int row = 0; row < rows ; row++){
                c = this.buffer.readLine();
                List<FixedElement> fixedElementsRow = new ArrayList<>();
                for(int column = 0; column < columns; column++){
                    char charAt = ' ';
                    if(column < c.length())
                        charAt = c.charAt(column);
                    fixedElementsRow.add(charToElement.get(charAt).generate(column, row));
                }
                fixedElementsMap.add(fixedElementsRow);
            }

            this.buffer.close();
        } catch (Exception e){
            System.out.println("File not compatible with map generation. More details below.");
            System.out.println(e.getMessage());
        }

        return fixedElementsMap;
    }
}

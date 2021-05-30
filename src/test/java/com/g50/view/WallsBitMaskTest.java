package com.g50.view;

import com.g50.model.element.fixed.noncollectable.Wall;
import com.g50.model.map.GameMap;
import com.g50.model.map.mapbuilder.FileGameMapBuilder;
import com.g50.model.map.mapbuilder.GameMapBuilder;
import com.g50.view.builders.DefaultWallViewerBuilder;
import com.g50.view.builders.WallViewerBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.BitSet;

public class WallsBitMaskTest {

    @Test
    public void BitMaskTest() throws IOException {
        GameMapBuilder mapBuilder = new FileGameMapBuilder("src/main/resources/maps/default.txt");
        GameMap map = mapBuilder.getBuild();
        assert(map.getMap().get(2).get(0) instanceof Wall);
        assertEquals(((Wall) map.getMap().get(2).get(0)).getBitmask(),
                BitSet.valueOf(new byte[]{(byte) 0x3}));

        assert(map.getMap().get(3).get(0) instanceof Wall);
        assertEquals(((Wall) map.getMap().get(3).get(0)).getBitmask(),
                BitSet.valueOf(new byte[]{(byte) 0x5}));

        assert(map.getMap().get(15).get(0) instanceof Wall);
        assertEquals(((Wall) map.getMap().get(15).get(0)).getBitmask(),
                BitSet.valueOf(new byte[]{(byte) 0xA}));
    }

    @Test
    public void WallViewerTest() {
        Wall wall = new Wall(null);
        wall.setBitmask(BitSet.valueOf(new byte[]{(byte)0x1}));

        WallViewerBuilder wallViewerBuilder = new DefaultWallViewerBuilder();
        assertEquals(wallViewerBuilder.getViewer(wall),
                new ElementViewer(new Wall(null),new ViewProperty("#0000FF",(char)(131))));

        wall.setBitmask(BitSet.valueOf(new byte[]{(byte)0x2}));

        assertEquals(wallViewerBuilder.getViewer(wall),
                new ElementViewer(new Wall(null),new ViewProperty("#0000FF",(char)(129))));

        wall.setBitmask(BitSet.valueOf(new byte[]{(byte)0x3}));

        assertEquals(wallViewerBuilder.getViewer(wall),
                new ElementViewer(new Wall(null),new ViewProperty("#0000FF",(char)(134))));
    }
}

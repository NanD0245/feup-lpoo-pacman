package com.g50.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LevelTest {

    @Test
    public void checkLevelInfo(){
        Level levelInfo = new Level(1);
        Level levelInfo2 = new Level(1000);

        Assertions.assertEquals(levelInfo.getPacManFramesPerMovement(), 8);
        Assertions.assertEquals(levelInfo2.getPacManFramesPerMovement(), 4);

        Assertions.assertEquals(levelInfo.getGhostFramesPerMovement(), 9);
        Assertions.assertEquals(levelInfo2.getGhostFramesPerMovement(), 5);

        Assertions.assertEquals(levelInfo.getFrightenedGhostFramesPerMovement(), 14);
        Assertions.assertEquals(levelInfo2.getFrightenedGhostFramesPerMovement(), 12);

        Assertions.assertEquals(levelInfo.getGameStateIntervals(), Arrays.asList(7,20,7,20,5,20,5));
        Assertions.assertEquals(levelInfo2.getGameStateIntervals(), Arrays.asList(5,20,5,20,5,1037,1));

        Assertions.assertEquals(levelInfo.getGhostFrightenedTime(), 6);
        Assertions.assertEquals(levelInfo2.getGhostFrightenedTime(), 2);

    }
}

package g50.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;



public class MenuTest {

    List<Menu> menus;

    @BeforeEach
    public void initTest() {
        int x = 10;
        int y = 15;
        menus = Arrays.asList(
                new MainMenu(),
                new ControlsMenu(),
                new HighScoreMenu(500),
                new CreditsMenu(),
                new GameOverMenu(),
                new PauseMenu(500),
                new TransitionMenu()
        );
    }

    @Test
    public void testMenuFlow(){
        for(Menu menu: menus){
            int first = menu.currentEntry;
            int realCount = menu.getNumberEntries();
            while(realCount > 0){
                menu.nextEntry();
                realCount--;
            }

            Assertions.assertEquals(first, menu.currentEntry);

            realCount = menu.getNumberEntries();
            while(realCount > 0){
                menu.previousEntry();
                realCount--;
            }

            Assertions.assertEquals(first, menu.currentEntry);
        }
    }

}

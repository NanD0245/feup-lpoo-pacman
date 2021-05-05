import g50.gui.GUI;
import g50.gui.LanternaGUI;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        System.out.println("hello World");
        Game pacman = new Game();
        pacman.init();
    }
}

import g50.gui.GUI;
import g50.gui.LanternaGUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        System.out.println("hello World");
        Game pacman = new Game();
        pacman.init();
    }
}

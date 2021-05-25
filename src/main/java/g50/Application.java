package g50;

import g50.controller.ApplicationController;
import g50.gui.LanternaGUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        ApplicationController controller = new ApplicationController(new Application(), new g50.gui.LanternaGUI(28,38));
        controller.setUp();
    }
}

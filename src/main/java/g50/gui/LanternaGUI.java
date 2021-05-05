package g50.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import g50.model.Position;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI implements GUI{

    private final Screen screen;

    public LanternaGUI(int width, int height) throws IOException {
        Terminal terminal = createTerminal(width, height);
        this.screen = createScreen(terminal);
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig)
            throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(width, height + 1));
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        // TO DO: add event handlers
        return terminal;
    }

    private Terminal createTerminal(int width, int height)
            throws IOException {
        // TO DO: chain
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(width, height + 1));
        return terminalFactory.createTerminal();
    }

    private AWTTerminalFontConfiguration loadFontConfig(String path, int size)
            throws IOException, FontFormatException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(path);
        assert resource != null;
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        return AWTTerminalFontConfiguration.newInstance(font.deriveFont(Font.PLAIN, 25));
    }

    @Override
    public ACTION getNextAction() {
        return null;
    }

    @Override
    public void drawCharacter(char c, Position position, String color) {
        this.drawText("" + c, position, color);
    }

    @Override
    public void drawText(String text, Position position, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX(), position.getY() + 1, text);
    }

    @Override
    public void drawCharacter(char c, Position position) {
        this.drawText("" + c, position, "#FFFFFF");
    }

    @Override
    public void drawText(String text, Position position) {
        this.drawText(text, position, "#FFFFFF");
    }

    @Override
    public void clear() {
        this.screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        this.screen.refresh();
    }

    @Override
    public void close() throws IOException {
        this.screen.close();
    }
}

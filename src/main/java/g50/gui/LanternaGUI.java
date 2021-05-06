package g50.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import g50.model.Position;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LanternaGUI implements GUI{

    private final Screen screen;
    private final List<GUIObserver> observers;

    public LanternaGUI(int width, int height) throws IOException, URISyntaxException, FontFormatException {
        AWTTerminalFontConfiguration fontConfig = loadFontConfig("fonts/square.ttf", 25);
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
        this.observers = new ArrayList<>();
        setupWindowClosing(terminal);
        setupActionNotifiers(terminal);
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
        return terminalFactory.createTerminal();
    }

    private void setupWindowClosing(Terminal terminal) {
        ((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                try {
                    close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private AWTTerminalFontConfiguration loadFontConfig(String path, int size)
            throws IOException, FontFormatException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(path);
        assert resource != null;
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        return AWTTerminalFontConfiguration.newInstance(font.deriveFont(Font.PLAIN, size));
    }

    private void setupActionNotifiers(Terminal terminal){
        ((AWTTerminalFrame)terminal).getComponent(0).addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                ACTION action = getAction(keyEvent);
                notifyObservers(action);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
    }

    private ACTION getAction(KeyEvent keyEvent){
        if (keyEvent == null) return ACTION.OTHER;
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_DOWN: return ACTION.DOWN;
            case KeyEvent.VK_UP: return ACTION.UP;
            case KeyEvent.VK_LEFT: return ACTION.LEFT;
            case KeyEvent.VK_RIGHT: return ACTION.RIGHT;
            default: return ACTION.OTHER;
        }
    }

    private void notifyObservers(ACTION action) {
        for (GUIObserver observer : this.observers){
            observer.addPendingAction(action);
        }
    }

    @Override
    public void addObserver(GUIObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void drawCharacter(char c, Position position, String color) {
        this.drawText("" + c, position, color);
    }

    @Override
    public void drawText(String text, Position position, String color) {
        TextGraphics tg = this.screen.newTextGraphics();
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

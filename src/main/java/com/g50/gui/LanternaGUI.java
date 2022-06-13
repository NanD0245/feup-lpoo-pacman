package com.g50.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.g50.model.element.Position;

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

    public LanternaGUI(int width, int height, String fontPath) throws IOException, URISyntaxException, FontFormatException {
        AWTTerminalFontConfiguration fontConfig = loadFontConfig(fontPath, 25);
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
        this.observers = new ArrayList<>();
        setupWindowClosing(terminal);
        setupActionNotifiers(terminal);
    }

    public LanternaGUI(Screen screen){
        this.screen = screen;
        this.observers = new ArrayList<>();
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
                    notifyObservers(KBD_ACTION.QUIT);
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
                KBD_ACTION action = getAction(keyEvent);
                if (action != KBD_ACTION.OTHER){
                    try {
                        notifyObservers(action);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
    }

    private KBD_ACTION getAction(KeyEvent keyEvent){
        if (keyEvent == null) return KBD_ACTION.OTHER;
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_DOWN: return KBD_ACTION.DOWN;
            case KeyEvent.VK_UP: return KBD_ACTION.UP;
            case KeyEvent.VK_LEFT: return KBD_ACTION.LEFT;
            case KeyEvent.VK_RIGHT: return KBD_ACTION.RIGHT;
            case KeyEvent.VK_ENTER: return KBD_ACTION.SELECT;
            case KeyEvent.VK_ESCAPE: return KBD_ACTION.ESQ;
            default: return KBD_ACTION.OTHER;
        }
    }


    private void notifyObservers(KBD_ACTION action) throws IOException {
        for (GUIObserver observer : this.observers){
            observer.addPendingKBDAction(action);
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
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX(), position.getY() + 1, text);
    }

    @Override
    public void drawBlinkText(String text, Position position, String color) {
        TextGraphics tg = this.screen.newTextGraphics();
        tg.enableModifiers(SGR.BLINK);
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX(), position.getY() + 1, text);
        tg.disableModifiers(SGR.BLINK);
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

package g50.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.*;
import g50.model.Position;

import java.io.IOException;

public class LanternaGUI implements GUI{

    private final TerminalScreen screen;

    public LanternaGUI(int width, int height) throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(width, height));
        Terminal terminal = terminalFactory.createTerminal();
        this.screen = new TerminalScreen(terminal);
        this.screen.setCursorPosition(null);
        this.screen.startScreen();
        this.screen.doResizeIfNecessary();
    }

    @Override
    public ACTION getNextAction() {
        return null;
    }

    @Override
    public void drawCharacter(char c, Position position, String color) {

    }

    @Override
    public void drawText(String text, Position position, String color) {

    }

    @Override
    public void drawCharacter(char c, Position position) {

    }

    @Override
    public void drawText(String text, Position position) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void close() {

    }
}

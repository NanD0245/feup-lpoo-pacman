package g50.gui;

import g50.model.element.Position;

import java.io.IOException;

public interface GUI {
    enum KBD_ACTION {UP, RIGHT, DOWN, LEFT, QUIT, SELECT, ESQ, OTHER};
    void addObserver(GUIObserver observer);
    void drawCharacter(char c, Position position, String color);
    void drawText(String text, Position position, String color);
    void drawBlinkText(String text, Position position, String color);
    void drawCharacter(char c, Position position);
    void drawText(String text, Position position);
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
}

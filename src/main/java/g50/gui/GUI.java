package g50.gui;

import g50.model.Position;

public interface GUI {
    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT};
    ACTION getNextAction();
    void drawCharacter(char c, Position position, String color);
    void drawText(String text, Position position, String color);
    void drawCharacter(char c, Position position);
    void drawText(String text, Position position);
    void clear();
    void refresh();
    void close();
}

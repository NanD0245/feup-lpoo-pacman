package g50.view;

import g50.model.Position;

public class ViewProperty {
    private final String color;
    private final char character;

    public ViewProperty(String color, char character) {
        this.color = color;
        this.character = character;
    }

    public ViewProperty(char character){
        this("#FFFFFF", character);
    }

    public String getColor() {
        return color;
    }

    public char getCharacter() {
        return character;
    }
}

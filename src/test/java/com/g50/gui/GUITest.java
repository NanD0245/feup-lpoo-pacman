package com.g50.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.g50.model.element.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.isA;
import org.mockito.Mockito;

import java.io.IOException;

public class GUITest {

    private Screen screen;
    private TextGraphics textGraphics;

    @BeforeEach
    public void buildGUI(){
        screen = Mockito.mock(Screen.class);
        textGraphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(textGraphics);
    }

    @Test
    public void testMainGUIFunctionality() throws IOException {

        GUI lanternaGUI = new LanternaGUI(screen);
        lanternaGUI.drawText("hehe", new Position(50, 50), "#8c2d19");

        Mockito.verify(textGraphics, Mockito.atLeastOnce()).putString(isA(Integer.class), isA(Integer.class), isA(String.class));
        Mockito.verify(textGraphics, Mockito.atLeastOnce()).setForegroundColor(TextColor.Factory.fromString("#8c2d19"));

        lanternaGUI.drawBlinkText("hehe", new Position(50, 50), "#8c2d19");

        Mockito.verify(textGraphics, Mockito.atLeastOnce()).putString(isA(Integer.class), isA(Integer.class), isA(String.class));
        Mockito.verify(textGraphics, Mockito.atLeastOnce()).setForegroundColor(TextColor.Factory.fromString("#8c2d19"));
        Mockito.verify(textGraphics, Mockito.atLeastOnce()).enableModifiers(SGR.BLINK);
        Mockito.verify(textGraphics, Mockito.atLeastOnce()).disableModifiers(SGR.BLINK);

        lanternaGUI.clear();

        Mockito.verify(screen, Mockito.atLeastOnce()).clear();

        lanternaGUI.refresh();

        Mockito.verify(screen, Mockito.atLeastOnce()).refresh();

        lanternaGUI.close();

        Mockito.verify(screen, Mockito.atLeastOnce()).close();


    }
}

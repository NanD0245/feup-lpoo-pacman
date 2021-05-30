package g50.view;

import g50.gui.GUI;
import g50.model.element.fixed.collectable.PacDot;
import g50.model.element.fixed.collectable.PowerPellet;
import g50.model.element.fixed.collectable.fruit.*;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.*;
import g50.view.builders.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElementViewerBuilderTest {

    private GUI mockGUI;

    @BeforeEach
    public void setupViewMock(){
        mockGUI = Mockito.mock(GUI.class);
    }

    @Test
    public void defaultBuilderPacMan(){
        ViewProperty pacmanViewProperty = Mockito.mock(ViewProperty.class);
        Mockito.when(pacmanViewProperty.getCharacter()).thenReturn((char)(193));
        Mockito.when(pacmanViewProperty.getColor()).thenReturn("#FFFF00");
        PacManViewerBuilder pacManViewerBuilder = new DefaultPacManViewerBuilder();
        assertEquals(pacManViewerBuilder.getViewer(new PacMan(null)),
                new ElementViewer(new PacMan(null), pacmanViewProperty));
    }

    @Test
    public void defaultBuilderGhosts(){
        ViewProperty ghostViewProperty = Mockito.mock(ViewProperty.class);
        Mockito.when(ghostViewProperty.getCharacter()).thenReturn((char)(200));
        Mockito.when(ghostViewProperty.getColor()).thenReturn("#FF0000");
        GhostViewerBuilder ghostViewerBuilder = new DefaultGhostViewerBuilder();

        assertEquals(ghostViewerBuilder.getViewer(new BlinkyGhost(null,null,null,null)),
                new ElementViewer(new BlinkyGhost(null,null,null,null), ghostViewProperty));
        Mockito.when(ghostViewProperty.getColor()).thenReturn("#FFB852");
        assertEquals(ghostViewerBuilder.getViewer(new ClydeGhost(null,null,null,null)),
                new ElementViewer(new BlinkyGhost(null,null,null,null), ghostViewProperty));
        Mockito.when(ghostViewProperty.getColor()).thenReturn("#FFB8FF");
        assertEquals(ghostViewerBuilder.getViewer(new PinkyGhost(null,null,null,null)),
                new ElementViewer(new BlinkyGhost(null,null,null,null), ghostViewProperty));
        Mockito.when(ghostViewProperty.getColor()).thenReturn("#00FFFF");
        assertEquals(ghostViewerBuilder.getViewer(new InkyGhost(null,null,null,null)),
                new ElementViewer(new BlinkyGhost(null,null,null,null), ghostViewProperty));
    }

    @Test
    public void defaultBuilderElements() {
        ViewProperty defaultViewProperty = Mockito.mock(ViewProperty.class);
        ElementViewerBuilder elementViewerBuilder = new DefaultElementViewerBuilder();

        Mockito.when(defaultViewProperty.getCharacter()).thenReturn((char)(201));
        Mockito.when(defaultViewProperty.getColor()).thenReturn("#DEA185");
        assertEquals(elementViewerBuilder.getViewer(new PacDot(null)),
                new ElementViewer(new PacDot(null), defaultViewProperty));

        Mockito.when(defaultViewProperty.getCharacter()).thenReturn((char)(199));
        Mockito.when(defaultViewProperty.getColor()).thenReturn("#DEA185");
        assertEquals(elementViewerBuilder.getViewer(new PowerPellet(null)),
                new ElementViewer(new PowerPellet(null), defaultViewProperty));

        Mockito.when(defaultViewProperty.getCharacter()).thenReturn((char)(145));
        Mockito.when(defaultViewProperty.getColor()).thenReturn("#FF0000");
        assertEquals(elementViewerBuilder.getViewer(new Apple(null)),
                new ElementViewer(new Apple(null), defaultViewProperty));

        Mockito.when(defaultViewProperty.getCharacter()).thenReturn((char)(143));
        Mockito.when(defaultViewProperty.getColor()).thenReturn("#FF0000");
        assertEquals(elementViewerBuilder.getViewer(new Cherry(null)),
                new ElementViewer(new Cherry(null), defaultViewProperty));

        Mockito.when(defaultViewProperty.getCharacter()).thenReturn((char)(146));
        Mockito.when(defaultViewProperty.getColor()).thenReturn("#FFA500");
        assertEquals(elementViewerBuilder.getViewer(new Orange(null)),
                new ElementViewer(new Orange(null), defaultViewProperty));

        Mockito.when(defaultViewProperty.getCharacter()).thenReturn((char)(144));
        Mockito.when(defaultViewProperty.getColor()).thenReturn("#FF0000");
        assertEquals(elementViewerBuilder.getViewer(new Strawberry(null)),
                new ElementViewer(new Strawberry(null), defaultViewProperty));

        Mockito.when(defaultViewProperty.getCharacter()).thenReturn((char)(147));
        Mockito.when(defaultViewProperty.getColor()).thenReturn("#32b858");
        assertEquals(elementViewerBuilder.getViewer(new Manjaro(null)),
                new ElementViewer(new Manjaro(null), defaultViewProperty));

    }

    @Test
    public void customBuilder(){
        ViewProperty ghostViewProperty = Mockito.mock(ViewProperty.class);
        Mockito.when(ghostViewProperty.getCharacter()).thenReturn('G');
        Mockito.when(ghostViewProperty.getColor()).thenReturn("#FFFFFF");
        CustomElementViewerBuilder elementViewerBuilder = new CustomElementViewerBuilder();

        assertEquals(elementViewerBuilder.getViewer(new BlinkyGhost(null, null, null, null)),
                new ElementViewer(new BlinkyGhost(null, null, null, null), new ViewProperty("#FFFFFF", '?')));

        elementViewerBuilder.addViewer(PinkyGhost.class, new ViewProperty("#FFFF00", 'G'));
        assertEquals(elementViewerBuilder.getViewer(new PinkyGhost(null,null,null,null)),
                new ElementViewer(new PinkyGhost(null,null,null,null), new ViewProperty("#FFFF00", 'G')));

        elementViewerBuilder.addViewer(PacMan.class, new ViewProperty("#F0FFF0", 'P'));
        assertEquals(elementViewerBuilder.getViewer(new PacMan(null)),
                new ElementViewer(new PacMan(null), new ViewProperty("#F0FFF0", 'P')));

        elementViewerBuilder.removeViewer(PacMan.class);
        assertEquals(elementViewerBuilder.getViewer(new PacMan(null)),
                new ElementViewer(new PacMan(null), new ViewProperty("#FFFFFF", '?')));
    }


}
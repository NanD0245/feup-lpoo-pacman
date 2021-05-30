package g50.view;

import g50.gui.GUI;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.*;
import g50.view.factory.*;
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
        Mockito.when(pacmanViewProperty.getCharacter()).thenReturn((char)(192));
        Mockito.when(pacmanViewProperty.getColor()).thenReturn("#FFFF00");
        PacManViewerBuilder pacManViewerBuilder = new DefaultPacManViewerBuilder();
        assertEquals(pacManViewerBuilder.getViewer(new PacMan(null)),
                new ElementViewer(new PacMan(null), new ViewProperty("#FFFF00", (char)(193))));
    }

    @Test
    public void defaultBuilderGhosts(){
        ViewProperty ghostViewProperty = Mockito.mock(ViewProperty.class);
        Mockito.when(ghostViewProperty.getCharacter()).thenReturn((char)(200));
        Mockito.when(ghostViewProperty.getColor()).thenReturn("#FF0000");
        GhostViewerBuilder ghostViewerBuilder = new DefaultGhostViewerBuilder();

        assertEquals(ghostViewerBuilder.getViewer(new BlinkyGhost(null,null,null,null)),
                new ElementViewer(new BlinkyGhost(null,null,null,null), new ViewProperty("#FF0000", (char)(200))));
        Mockito.when(ghostViewProperty.getColor()).thenReturn("#FFB852");
        assertEquals(ghostViewerBuilder.getViewer(new ClydeGhost(null,null,null,null)),
                new ElementViewer(new BlinkyGhost(null,null,null,null), new ViewProperty("#FFB852", (char)(200))));
        Mockito.when(ghostViewProperty.getColor()).thenReturn("#FFB8FF");
        assertEquals(ghostViewerBuilder.getViewer(new PinkyGhost(null,null,null,null)),
                new ElementViewer(new BlinkyGhost(null,null,null,null), new ViewProperty("#FFB8FF", (char)(200))));
        Mockito.when(ghostViewProperty.getColor()).thenReturn("#00FFFF");
        assertEquals(ghostViewerBuilder.getViewer(new InkyGhost(null,null,null,null)),
                new ElementViewer(new BlinkyGhost(null,null,null,null), new ViewProperty("#00FFFF", (char)(200))));
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
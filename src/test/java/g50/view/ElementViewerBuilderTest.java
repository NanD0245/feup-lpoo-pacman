package g50.view;

import g50.gui.GUI;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;
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
        Mockito.when(pacmanViewProperty.getCharacter()).thenReturn('P');
        Mockito.when(pacmanViewProperty.getColor()).thenReturn("#FFFFFF");
        ElementViewerBuilder elementViewerBuilder = new DefaultElementViewerBuilder();
        assertEquals(elementViewerBuilder.getViewer(mockGUI, new PacMan(null)),
                new ElementViewer(mockGUI, new ViewProperty("#FFFFFF", 'P')));
    }

    @Test
    public void customBuilder(){
        ViewProperty ghostViewProperty = Mockito.mock(ViewProperty.class);
        Mockito.when(ghostViewProperty.getCharacter()).thenReturn('G');
        Mockito.when(ghostViewProperty.getColor()).thenReturn("#FFFFFF");
        CustomElementViewerBuilder elementViewerBuilder = new CustomElementViewerBuilder();

        assertEquals(elementViewerBuilder.getViewer(mockGUI, new Ghost(null, null, null, null)),
                new ElementViewer(mockGUI, new ViewProperty("#FFFFFF", '?')));
        elementViewerBuilder.addViewer(Ghost.class, new ViewProperty("#FFFF00", 'G'));
        assertEquals(elementViewerBuilder.getViewer(mockGUI, new Ghost(null,null,null,null)),
                new ElementViewer(mockGUI, new ViewProperty("#FFFF00", 'G')));

        elementViewerBuilder.addViewer(PacMan.class, new ViewProperty("#F0FFF0", 'P'));
        assertEquals(elementViewerBuilder.getViewer(mockGUI, new PacMan(null)),
                new ElementViewer(mockGUI, new ViewProperty("#F0FFF0", 'P')));

        elementViewerBuilder.removeViewer(PacMan.class);
        assertEquals(elementViewerBuilder.getViewer(mockGUI, new PacMan(null)),
                new ElementViewer(mockGUI, new ViewProperty("#FFFFFF", '?')));
    }
}
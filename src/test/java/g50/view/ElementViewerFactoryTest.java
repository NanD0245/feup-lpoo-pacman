package g50.view;

import g50.gui.GUI;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElementViewerFactoryTest {

    private GUI mockGUI;

    @BeforeEach
    public void setupViewMock(){
        mockGUI = Mockito.mock(GUI.class);
    }

    @Test
    public void defaultFactoryPacman(){
        ViewProperty pacmanViewProperty = Mockito.mock(ViewProperty.class);
        Mockito.when(pacmanViewProperty.getCharacter()).thenReturn('P');
        Mockito.when(pacmanViewProperty.getColor()).thenReturn("#FFFFFF");
        ElementViewerFactory elementViewerFactory = new DefaultElementViewerFactory();
        assertEquals(elementViewerFactory.getViewer(mockGUI, new PacMan(null)),
                new ElementViewer(mockGUI, new ViewProperty("#FFFFFF", 'P')));
    }

    @Test
    public void customFactory(){
        ViewProperty ghostViewProperty = Mockito.mock(ViewProperty.class);
        Mockito.when(ghostViewProperty.getCharacter()).thenReturn('G');
        Mockito.when(ghostViewProperty.getColor()).thenReturn("#FFFFFF");
        CustomElementViewerFactory elementViewerFactory = new CustomElementViewerFactory();

        assertEquals(elementViewerFactory.getViewer(mockGUI, new Ghost(null, null, null, null)),
                new ElementViewer(mockGUI, new ViewProperty("#FFFFFF", '?')));
        elementViewerFactory.addViewer(Ghost.class, new ViewProperty("#FFFF00", 'G'));
        assertEquals(elementViewerFactory.getViewer(mockGUI, new Ghost(null,null,null,null)),
                new ElementViewer(mockGUI, new ViewProperty("#FFFF00", 'G')));

        elementViewerFactory.addViewer(PacMan.class, new ViewProperty("#F0FFF0", 'P'));
        assertEquals(elementViewerFactory.getViewer(mockGUI, new PacMan(null)),
                new ElementViewer(mockGUI, new ViewProperty("#F0FFF0", 'P')));

        elementViewerFactory.removeViewer(PacMan.class);
        assertEquals(elementViewerFactory.getViewer(mockGUI, new PacMan(null)),
                new ElementViewer(mockGUI, new ViewProperty("#FFFFFF", '?')));
    }
}
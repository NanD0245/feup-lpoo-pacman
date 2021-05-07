package g50.controller;

import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.element.movable.PacMan;

import java.util.Map;
import java.util.function.Function;

public class PacManController implements GUIObserver {

    private final PacMan controllabe;

    public PacManController(PacMan controllabe){
        this.controllabe = controllabe;
    }

    @Override
    public void addPendingAction(GUI.ACTION action) {
        switch(action){
            case UP:
                break;
            case RIGHT:
                break;
            case DOWN:
                break;
            case LEFT:
                break;
            case QUIT:
            case OTHER:
                break;
        }


    }
}

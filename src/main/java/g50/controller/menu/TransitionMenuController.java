package g50.controller.menu;

import g50.Application;
import g50.controller.states.GameState;
import g50.gui.GUI;
import g50.model.element.movable.Orientation;
import g50.model.menu.Menu;
import g50.model.menu.TransitionMenu;
import g50.view.menu.MenuViewer;
import g50.view.menu.TransitionMenuViewer;

import java.io.IOException;

public class TransitionMenuController extends MenuController {

    private int count;
    private boolean direction;
    private boolean started;

    public TransitionMenuController(GUI gui, TransitionMenu menu) {
        super(gui, new TransitionMenuViewer(menu), menu);
        this.count = 0;
        this.direction = true;
        this.started = false;
    }

    @Override
    public void notify(GameState gameState) {

    }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) {

    }

    @Override
    public void update(Application application, int frame) throws IOException {
        if (direction) {
            if(!started){
                Application.playSound("pacman_intermission.wav");
                this.started = true;
            }
            ((TransitionMenu) getModel()).getPacMan().setFramesPerPosition(10);
            ((TransitionMenu) getModel()).getGhost().setFramesPerPosition(8);
            if (((TransitionMenu)getModel()).getPacMan().getPosition().getX() >= 0 && frame % ((TransitionMenu) getModel()).getPacMan().getFramesPerPosition() == 0) {
                ((TransitionMenu) getModel()).getPacMan().setPosition(((TransitionMenu) getModel()).getPacMan().getPosition().getLeft());
                count++;
            }
            if (count > 7 && frame % ((TransitionMenu) getModel()).getGhost().getFramesPerPosition() == 0) {
                ((TransitionMenu) getModel()).getGhost().setPosition(((TransitionMenu) getModel()).getGhost().getPosition().getLeft());
            }
            if (((TransitionMenu) getModel()).getGhost().getPosition().getX() < 0) {
                direction = false;
                count = 0;
                ((TransitionMenu) getModel()).getPacMan().setOrientation(Orientation.RIGHT);
            }
        }
        else {
            if(started){
                Application.playSound("pacman_intermission.wav");
                this.started = false;
            }
            ((TransitionMenu) getModel()).getGhost().setFramesPerPosition(10);
            ((TransitionMenu) getModel()).getPacMan().setFramesPerPosition(8);
            if (frame % ((TransitionMenu) getModel()).getGhost().getFramesPerPosition() == 0) {
                ((TransitionMenu) getModel()).getGhost().setPosition(((TransitionMenu) getModel()).getGhost().getPosition().getRight());
                count++;
            }
            if (count > 7 && frame % ((TransitionMenu) getModel()).getPacMan().getFramesPerPosition() == 0) {
                ((TransitionMenu) getModel()).getPacMan().setPosition(((TransitionMenu) getModel()).getPacMan().getPosition().getRight());
            }
        }
        this.getMenuViewer().draw(this.getGui());
    }
}

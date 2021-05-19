package g50.controller;

import g50.controller.ghost_strategy.*;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.Collectable;
import g50.model.element.fixed.nonCollectable.EmptySpace;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.*;
import g50.model.map.GameMap;
import g50.view.GameMapViewer;

import java.io.IOException;
import java.util.*;

public class GameController implements GUIObserver, Controller {

    private final GameMap map;
    private final List<GhostController> ghostsController;
    private final PacManController pacManController;
    private int framerate = 60;
    private Timer timer;
    private TimerTask updater;
    private final GameMapViewer viewer;
    private final GameState gameState;

    public GameController(GameMap map, GameMapViewer viewer){
        this.map = map;
        this.viewer = viewer;
        this.ghostsController = new ArrayList<>();
        this.gameState = new GameState();
        setUpGhosts();
        this.pacManController = new PacManController(map);
    }

    public void setUpGhosts(){

        BlinkyGhost currentBlinky = null;

        for(Ghost ghost: map.getGhosts())
            if(ghost instanceof BlinkyGhost) currentBlinky = (BlinkyGhost) ghost;

        for(Ghost ghost: map.getGhosts()) {
            GhostController newGhostController;
            if(ghost instanceof InkyGhost)
                newGhostController = new GhostController(this.map, ghost, GhostState.INCAGE, new InkyStrategy(this.map, ghost, currentBlinky));
            else if(ghost instanceof ClydeGhost)
                newGhostController = new GhostController(this.map, ghost, GhostState.INCAGE, new ClydeStrategy(this.map, ghost));
            else if(ghost instanceof PinkyGhost)
                newGhostController = new GhostController(this.map, ghost, GhostState.INCAGE, new PinkyStrategy(this.map, ghost));
            else {
                newGhostController = new GhostController(this.map, ghost, GhostState.CHASE, new BlinkyStrategy(this.map, ghost));
                currentBlinky = (BlinkyGhost) ghost;
            }

            this.ghostsController.add(newGhostController);
            this.gameState.addObserver(newGhostController);
        }
    }

    public void setUp(){
        setUp(framerate);
    }

    public void setUp(int framerate){
        this.framerate = framerate;
        updater = new TimerTask() {
            int frame = 0;
            @Override
            public void run() {
                frame++;
                update(frame);
            }
        };
        timer = new Timer();
        timer.schedule(updater, 1000/framerate, 1000/framerate);
    }

    public void terminate() {
        timer.cancel();
    }

    @Override
    public void addPendingAction(GUI.ACTION action) {
        if(action == GUI.ACTION.QUIT) terminate();
        pacManController.addPendingAction(action);
    }


    @Override
    public void update(int frame) {
        gameState.update(frame, framerate);
        pacManController.update(frame);

        controlGhosts(frame);


        try {
            viewer.draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void controlGhosts(int frame) {

        for(GhostController ghostController: ghostsController){
            ghostController.update(frame);
        }
    }

    @Override
    public void notify(GameState.CurrentState state) { }

}

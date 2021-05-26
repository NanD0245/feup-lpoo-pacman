package g50.controller;

import g50.controller.menu.*;
import g50.controller.states.GameState;
import g50.Application;
import g50.controller.states.app_states.AppState;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.Game;
import g50.model.map.GameMap;
import g50.model.map.mapbuilder.DefaultGameMapBuilder;
import g50.model.menu.*;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ApplicationController extends Controller<Application> {

    private int frameRate = 60;
    private Timer timer;
    private Controller controller;
    private final GUI gui;
    private AppState lastAppState;
    private Game game;
    private PauseMenuController pauseController;
    private Menu menu;

    public ApplicationController(Application application, GUI gui) throws IOException {
        super(application);
        this.menu = new MainMenu();
        GameMap map = new DefaultGameMapBuilder().getBuild();
        this.gui = gui;
        gui.addObserver(this);
        this.controller = new MainMenuController(gui,(MainMenu)menu);
        setState(AppState.MAIN_MENU);
        this.lastAppState = AppState.MAIN_MENU;
        this.game = null;
        this.pauseController = null;
    }

    public void setUp(){
        setUp(frameRate);
    }

    public void setUp(int frameRate){
        this.frameRate = frameRate;
        TimerTask task = new TimerTask() {
            int frame = 0;
            @Override
            public void run() {
                frame++;
                try {
                    update(getModel(), frame);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer = new Timer();
        timer.schedule(task, 1000/frameRate, 1000/frameRate);
    }

    public void terminate() throws IOException {
        timer.cancel();
        this.gui.close();
    }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) throws IOException {
        if(action == GUI.KBD_ACTION.QUIT) terminate();
        this.controller.addPendingKBDAction(action);
        if (this.controller.state != null)
            this.state = this.controller.state;
    }

    @Override
    public void update(Application application, int frame) throws IOException {
        if (!lastAppState.equals(state)) {
            switch (state) {
                case MAIN_MENU:
                    this.menu = new MainMenu();
                    this.controller = new MainMenuController(gui,(MainMenu)menu);
                    break;
                case IN_GAME:
                    this.game = new Game();
                    this.controller = new GameController(gui, game, frameRate);
                    break;
                case CONTROLS_MENU:
                    this.menu = new ControlsMenu();
                    this.controller = new ControlsMenuController(gui,(ControlsMenu)menu);
                    break;
                case CREDITS_MENU:
                    this.menu = new CreditsMenu();
                    this.controller = new CreditsMenuController(gui,(CreditsMenu)menu);
                    break;
                case GAME_OVER:
                    this.menu = new GameOverMenu();
                    this.controller = new GameOverMenuController(gui,(GameOverMenu)menu);
                    break;
                case EXIT_MENU:
                    terminate();
                    break;
            }
            lastAppState = state;
            System.out.println(lastAppState);
        }
        else {
            if (this.controller instanceof GameController && ((GameController) this.controller).isGameOver()) {
                this.menu = new GameOverMenu();
                this.controller = new GameOverMenuController(gui, (GameOverMenu) menu);
            } /*else if (this.controller instanceof GameController && ((GameController) this.controller).isPause()) {
                this.state = AppState.PAUSE_MENU;
                this.menu = new PauseMenu();
                this.pauseController = new PauseMenuController(gui, (PauseMenu) menu);
            }*/
        }

        if (this.pauseController != null)
            this.pauseController.update(application,frame);
        else {
            this.controller.update(application, frame);
        }
    }

    @Override
    public void notify(GameState state) {}
}

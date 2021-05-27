package g50;

import g50.controller.Controller;
import g50.controller.GameController;
import g50.controller.menu.*;
import g50.controller.states.app_states.AppState;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.gui.LanternaGUI;
import g50.model.Game;
import g50.model.map.GameMap;
import g50.model.map.mapbuilder.DefaultGameMapBuilder;
import g50.model.menu.*;
import g50.model.menu.Menu;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Integer.parseInt;

public class Application implements GUIObserver {
    private int highscore;

    static final String highscore_file = "src/main/resources/highscore/highscore.txt";
    private int frameRate = 60;
    private Timer timer;
    private Controller controller;
    private final GUI gui;
    private AppState state;
    private AppState lastAppState;
    private Game game;
    private Menu menu;

    Application(GUI gui) throws FileNotFoundException {
        setHighscore(readHighscore(highscore_file));
        System.out.println(readHighscore(highscore_file));
        //this.menu = new MainMenu();
        this.menu = new TransitionMenu();
        this.gui = gui;
        gui.addObserver(this);
        //this.controller = new MainMenuController(gui,(MainMenu)menu);
        this.controller = new TransitionMenuController(gui, (TransitionMenu)menu);
        this.state = AppState.MAIN_MENU;
        this.lastAppState = AppState.MAIN_MENU;
        this.game = null;
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        Application application =
                new Application(new g50.gui.LanternaGUI(28,38));
        application.setUp(60);
    }

    public int readHighscore(String file) throws FileNotFoundException {
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        int highscore = 0;
        try {
            String score_s = buffer.readLine();
            if (score_s != null && !score_s.equals(""))
                highscore =  parseInt(score_s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highscore;
    }

    public void writeHighscore(String file) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(String.valueOf(getHighscore()));
        writer.close();
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setUp(int frameRate){
        this.frameRate = frameRate;
        TimerTask task = new TimerTask() {
            int frame = 0;
            @Override
            public void run() {
                frame++;
                try {
                    update(frame);
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
        writeHighscore(highscore_file);
    }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) throws IOException {
        if(action == GUI.KBD_ACTION.QUIT) terminate();
        this.controller.addPendingKBDAction(action);
        //if (this.controller.state != null)
            //this.state = this.controller.state;
    }

    public void update(int frame) throws IOException {
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
            }
        }
        this.controller.update(this, frame);
    }
}

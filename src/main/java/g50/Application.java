package g50;

import com.googlecode.lanterna.terminal.ansi.TelnetTerminalServer;
import g50.controller.Controller;
import g50.controller.GameController;
import g50.controller.menu.*;
import g50.states.AppState;
import g50.gui.GUI;
import g50.gui.GUIObserver;
import g50.model.Game;
import g50.model.menu.*;
import g50.model.menu.Menu;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Integer.parseInt;

public class Application implements GUIObserver {
    private int highScore;

    static final String highscore_file = "src/main/resources/highscore/highscore.txt";
    private int frameRate;
    private Timer timer;
    private Controller<?> controller;
    private final GUI gui;
    private AppState state;
    private AppState lastAppState;
    private Game game;
    private Menu menu;
    private PauseMenu pauseMenu;
    private PauseMenuController pauseMenuController;

    Application(GUI gui) throws FileNotFoundException {
        setHighScore(readHighScore(highscore_file));
        System.out.println(readHighScore(highscore_file));
        this.menu = new MainMenu();
        this.gui = gui;
        gui.addObserver(this);
        this.controller = new MainMenuController(gui,(MainMenu)menu);
        this.state = AppState.MAIN_MENU;
        this.lastAppState = AppState.MAIN_MENU;
        this.game = null;

        this.pauseMenu = new PauseMenu();
        this.pauseMenuController = new PauseMenuController(gui,pauseMenu);
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        Application application =
                new Application(new g50.gui.LanternaGUI(28,38));
        application.setUp(30);
    }

    public int readHighScore(String file) throws FileNotFoundException {
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

    public void writeHighScore(String file) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(String.valueOf(getHighScore()));
        writer.close();
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getHighScore() {
        return highScore;
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
        writeHighScore(highscore_file);
    }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) throws IOException {
        if(action == GUI.KBD_ACTION.QUIT) terminate();
        if (!state.equals(AppState.PAUSE_MENU))
            this.controller.addPendingKBDAction(action);
        else this.pauseMenuController.addPendingKBDAction(action);
    }

    public void update(int frame) throws IOException {
        if (!lastAppState.equals(state)) {
            if (!(lastAppState.equals(AppState.PAUSE_MENU) || lastAppState.equals(AppState.IN_GAME)))
                this.game = null;
            switch (state) {
                case MAIN_MENU:
                    this.menu = new MainMenu();
                    this.controller = new MainMenuController(gui,(MainMenu)menu);
                    break;
                case IN_GAME:
                    if (this.game == null) {
                        this.game = new Game(highScore);
                        this.controller = new GameController(gui, game);
                    } else {
                        ((GameController) controller).setPause(false);
                    }
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
        }
        else {
            if (this.controller instanceof GameController && ((GameController) this.controller).isGameOver()) {
                if (this.game.getScore() > this.highScore) {
                    this.highScore = this.getGame().getScore();
                }
                this.menu = new GameOverMenu();
                this.controller = new GameOverMenuController(gui, (GameOverMenu) menu);
            }
        }
        if (state.equals(AppState.PAUSE_MENU)) {
            this.pauseMenuController.update(this,frame);
        }
        else this.controller.update(this, frame);
    }

    public Controller<?> getController() {
        return controller;
    }

    public Game getGame() {
        return game;
    }

    public Menu getMenu() {
        return menu;
    }

    public AppState getState() {
        return state;
    }

    public void setState(AppState state) {
        this.state = state;
    }

    public int getFrameRate() {
        return frameRate;
    }
}

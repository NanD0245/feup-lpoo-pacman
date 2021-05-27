package g50.view;

import g50.controller.GameController;
import g50.gui.GUI;
import g50.model.Game;
import g50.model.Position;
import g50.model.element.movable.PacMan;
import g50.model.map.GameMap;
import g50.view.pacmanViewerFactory.DefaultPacManViewerBuilder;

import java.io.IOException;

public class GameViewer {
    private GameMapViewer gameMapViewer;
    private DefaultPacManViewerBuilder pacmanBuilder;
    private Game game;
    private GameController gameController;

    public GameViewer(Game game, GameController gameController) {
        this.game = game;
        this.gameMapViewer = new GameMapViewer(gameController);
        this.pacmanBuilder = new DefaultPacManViewerBuilder(gameController);
    }

    public void draw(GUI gui, GameMap gameMap) throws IOException {
        gui.clear();

        gameMapViewer.draw(gui, gameMap);

        gui.drawText("SCORE\t\t\tHIGH SCORE", new Position(2,-1));

        gui.drawText(String.valueOf(game.getScore()), new Position(3,0));
        gui.drawText(String.valueOf(game.getScore()), new Position(19,0));

        for (int i = 0; i < gameMap.getPacman().getLives(); i++) {
            this.pacmanBuilder.getViewer(new PacMan(new Position(i, 34))).draw(gui);
        }

        gui.refresh();
    }
}

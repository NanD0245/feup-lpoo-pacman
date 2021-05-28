package g50.view.menu;

import g50.controller.GameController;
import g50.gui.GUI;
import g50.model.Game;
import g50.model.element.Position;
import g50.model.element.movable.PacMan;

import java.io.IOException;

public class GameViewer extends Viewer<Game>{
    private final GameMapViewer gameMapViewer;

    public GameViewer(Game game, GameController gameController) {
        super(game);
        this.gameMapViewer = new GameMapViewer(game.getGameMap(), gameController);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();

        gameMapViewer.draw(gui);

        gui.drawText("SCORE\t\t\tHIGH SCORE", new Position(2,-1));

        gui.drawText(String.valueOf(getModel().getScore()), new Position(3,0));
        gui.drawText(String.valueOf(getModel().getHighscore()), new Position(19,0));

        for (int i = 0; i < getModel().getGameMap().getPacman().getLives(); i++) {
            this.gameMapViewer.getPacManViewerBuilder().getViewer(new PacMan(new Position(i, 34))).draw(gui);
        }

        gui.refresh();
    }
}

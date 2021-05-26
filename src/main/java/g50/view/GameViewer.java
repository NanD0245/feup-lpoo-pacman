package g50.view;

import g50.gui.GUI;
import g50.model.Game;
import g50.model.Position;
import g50.model.element.movable.PacMan;
import g50.model.map.GameMap;

import java.io.IOException;

public class GameViewer {
    private GameMapViewer gameMapViewer;
    private DefaultElementViewerBuilder viewerBuilder;
    private Game game;

    public GameViewer(Game game) {
        this.game = game;
        this.gameMapViewer = new GameMapViewer();
        this.viewerBuilder = new DefaultElementViewerBuilder();
    }

    public void draw(GUI gui, GameMap gameMap) throws IOException {
        gui.clear();

        gameMapViewer.draw(gui, gameMap);

        gui.drawText(String.valueOf(game.getScore()), new Position(3,0));

        gui.drawText("HIGH SCORE", new Position(16,-1));

        for (int i = 0; i < gameMap.getPacman().getLives(); i++) {
            this.viewerBuilder.getViewer(new PacMan(new Position(i, 34))).draw(gui);
        }

        gui.refresh();
    }
}

package buontyhunter.model;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.core.GameFactory;

public class GameState {

    private int score;
    private World world;
    private boolean gameOver;

    public GameState(WorldEventListener l) {
        GameFactory f = GameFactory.getInstance();

        score = 0;
        world = new World(new RectBoundingBox(new Point2d(-9, 10), 20, 18));
        world.setTileManager(f.createTileManager());
        world.setPlayer(f.createPlayer(new Point2d(0, 0), new Vector2d(0, 0), 100));
        world.setEventListener(l);
    }

    public World getWorld() {
        return world;
    }

    public void incScore() {
        score++;
    }

    public void decScore() {
        score--;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void gameOver() {
        gameOver = true;
    }

    public void update(int dt) {
        world.updateState(dt);
    }
}

package buontyhunter.model;

import buontyhunter.core.GameFactory;
import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;

public class GameState {

    private int score;
    private World world;
    private boolean gameOver;

    public GameState(WorldEventListener l) {
        GameFactory f = GameFactory.getInstance();

        score = 0;
        world = new World(new RectBoundingBox(new Point2d(0, 0), 20, 18));
        world.setPlayer(f.createPlayer(new Point2d(0, 0), Vector2d.symmetrical(0), 10, 100));
        world.setTileManager(f.createTileManager());
        world.setMiniMap(f.createMinimap());
        world.setNavigatorLine(f.createNavigatorLine(world));
        world.addEnemy(f.createEnemy(new Point2d(60, 50), new Vector2d(0.3, 0.3), 100));
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

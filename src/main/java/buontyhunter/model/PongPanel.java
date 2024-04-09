package buontyhunter.model;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.GraphicsComponent;
import buontyhunter.input.InputComponent;
import buontyhunter.physics.PhysicsComponent;

public class PongPanel extends HidableObject {

    /*
     * these dimensions are calculated in units of a 21 x 28 grid
     * positions indicate the center of the objects
     */
    private static int boardHeightSegments = 21;
    private static int boardWidthSegments = 28;
    private static double paddleHeight = 4;
    private static double paddleWidth = 1;
    private static double ballDimension = 0.5;
    private static double paddleSpeed = 0.5;
    private static double ballSpeed = 0.5;

    private PongObject playerPaddle;
    private PongObject enemyPaddle;
    private PongObject ball;
    private Vector2d ballDirection = new Vector2d(1, 1);

    public PongPanel(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, InputComponent input,
            GraphicsComponent graph, PhysicsComponent phys, boolean show) {
        super(type, pos, vel, box, input, graph, phys, show);
        this.playerPaddle = new PongObject(new Point2d(1, 10), paddleHeight, paddleWidth, paddleSpeed);
        this.enemyPaddle = new PongObject(new Point2d(26, 10), paddleHeight, paddleWidth, paddleSpeed);
        this.ball = new PongObject(new Point2d(13, 10), ballDimension, ballDimension, ballSpeed);
    }

    public int getBoardHeightSegments() {
        return boardHeightSegments;
    }

    public int getBoardWidthSegments() {
        return boardWidthSegments;
    }

    public void playerMoveUp() {
        if (this.playerPaddle.getUpperBound() - this.playerPaddle.getSpeed() >= 0) {
            this.playerPaddle.getPosition().y -= this.playerPaddle.getSpeed();
        } else {
            this.playerPaddle.getPosition().y = this.playerPaddle.getHeight() / 2;
        }
    }

    public void playerMoveDown() {
        if (this.playerPaddle.getLowerBound() + this.playerPaddle.getSpeed() < getBoardHeightSegments()) {
            this.playerPaddle.getPosition().y += this.playerPaddle.getSpeed();
        } else {
            this.playerPaddle.getPosition().y = getBoardHeightSegments() - (this.playerPaddle.getHeight() / 2);
        }
    }

    public void enemyMoveUp() {
        if (this.enemyPaddle.getUpperBound() - this.enemyPaddle.getSpeed() >= 0) {
            this.enemyPaddle.getPosition().y -= this.enemyPaddle.getSpeed();
        } else {
            this.enemyPaddle.getPosition().y = this.enemyPaddle.getHeight() / 2;
        }
    }

    public void enemyMoveDown() {
        if (this.enemyPaddle.getLowerBound() + this.enemyPaddle.getSpeed() < getBoardHeightSegments()) {
            this.enemyPaddle.getPosition().y += this.enemyPaddle.getSpeed();
        } else {
            this.enemyPaddle.getPosition().y = getBoardHeightSegments() - (this.enemyPaddle.getHeight() / 2);
        }
    }

    public void moveBall() {
        double x = this.ball.getPosition().x + (this.ballDirection.x * this.ball.getSpeed());
        double y = this.ball.getPosition().y + (this.ballDirection.y * this.ball.getSpeed());
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x >= this.getBoardWidthSegments()) {
            x = this.getBoardWidthSegments() - 1;
        }
        if (y >= this.getBoardHeightSegments()) {
            y = this.getBoardHeightSegments() - 1;
        }
        this.ball.setPosition(new Point2d(x, y));
    }

    public Vector2d getBallDirection() {
        return this.ballDirection;
    }

    public void setBallDirection(Vector2d ballDirection) {
        this.ballDirection = ballDirection;
    }

    public PongObject getBall() {
        return this.ball;
    }

    public PongObject getPlayerPaddle() {
        return this.playerPaddle;
    }

    public PongObject getEnemyPaddle() {
        return this.enemyPaddle;
    }
}

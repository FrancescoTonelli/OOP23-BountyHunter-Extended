package buontyhunter.model;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.GraphicsComponent;
import buontyhunter.input.InputComponent;
import buontyhunter.physics.PhysicsComponent;

public class PongPanel extends HidableObject {

    /*
     * these dimensions are calculated in units of a grid
     */
    private static int boardHeightSegments = 42;
    private static int boardWidthSegments = 56;
    private static double paddleHeight = 7;
    private static double paddleWidth = 2;
    private static double ballDimension = 1;
    private static double paddleSpeed = 0.5;
    private static double ballSpeed = 0.5;
    private static Point2d originalBallPosition = new Point2d(boardWidthSegments / 2, boardHeightSegments / 2);

    private PongObject playerPaddle;
    private PongObject enemyPaddle;
    private PongObject ball;
    private Vector2d ballDirection = new Vector2d(1, 1);
    private PongEntity entity;

    public PongPanel(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, InputComponent input,
            GraphicsComponent graph, PhysicsComponent phys, boolean show) {
        super(type, pos, vel, box, input, graph, phys, show);
        this.playerPaddle = new PongObject(new Point2d(0, boardHeightSegments / 2 - paddleHeight / 2), paddleHeight,
                paddleWidth, paddleSpeed);
        this.enemyPaddle = new PongObject(
                new Point2d(boardWidthSegments - paddleWidth, boardHeightSegments / 2 - paddleHeight / 2),
                paddleHeight, paddleWidth, paddleSpeed + 0.1);
        this.ball = new PongObject(originalBallPosition, ballDimension, ballDimension, ballSpeed);
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
            this.playerPaddle.getPosition().y = 0;
        }
    }

    public void playerMoveDown() {
        if (this.playerPaddle.getLowerBound() + this.playerPaddle.getSpeed() <= getBoardHeightSegments() - 1) {
            this.playerPaddle.getPosition().y += this.playerPaddle.getSpeed();
        } else {
            this.playerPaddle.getPosition().y = getBoardHeightSegments() - this.playerPaddle.getHeight();
        }
    }

    public void enemyMoveUp() {
        if (this.enemyPaddle.getUpperBound() - this.enemyPaddle.getSpeed() >= 0) {
            this.enemyPaddle.getPosition().y -= this.enemyPaddle.getSpeed();
        } else {
            this.enemyPaddle.getPosition().y = 0;
        }
    }

    public void enemyMoveDown() {
        if (this.enemyPaddle.getLowerBound() + this.enemyPaddle.getSpeed() <= getBoardHeightSegments() - 1) {
            this.enemyPaddle.getPosition().y += this.enemyPaddle.getSpeed();
        } else {
            this.enemyPaddle.getPosition().y = getBoardHeightSegments() - this.enemyPaddle.getHeight();
        }
    }

    public void moveBall() {
        this.ball.setPosition(new Point2d(this.ball.getPosition().x + (this.ballDirection.x * this.ball.getSpeed()),
                this.ball.getPosition().y + (this.ballDirection.y * this.ball.getSpeed())));
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

    public void resetBall() {
        this.getBall().setPosition(originalBallPosition);
        this.invertBallDirectionX();
        this.invertBallDirectionY();
        this.getBall().setSpeed(ballSpeed);
    }

    public void setEntity(PongEntity entity) {
        this.entity = entity;
    }

    public PongEntity getEntity() {
        return this.entity;
    }

    public void invertBallDirectionX() {
        this.setBallDirection(new Vector2d(-this.getBallDirection().x, this.getBallDirection().y));
    }

    public void invertBallDirectionY() {
        this.setBallDirection(new Vector2d(this.getBallDirection().x, -this.getBallDirection().y));
    }
}

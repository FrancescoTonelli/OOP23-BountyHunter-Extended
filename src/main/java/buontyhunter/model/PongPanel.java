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

    /**
     * Returns the number of units into which the height of the board has been
     * divided
     * 
     * @return the number of units
     */
    public int getBoardHeightSegments() {
        return boardHeightSegments;
    }

    /**
     * Returns the number of units into which the width of the board has been
     * divided
     * 
     * @return the number of units
     */
    public int getBoardWidthSegments() {
        return boardWidthSegments;
    }

    /**
     * moves the player's paddle up according to his speed
     */
    public void playerMoveUp() {
        if (this.playerPaddle.getUpperBound() - this.playerPaddle.getSpeed() >= 0) {
            this.playerPaddle.getPosition().y -= this.playerPaddle.getSpeed();
        } else {
            this.playerPaddle.getPosition().y = 0;
        }
    }

    /**
     * moves the player's paddle downward according to his speed
     */
    public void playerMoveDown() {
        if (this.playerPaddle.getLowerBound() + this.playerPaddle.getSpeed() <= getBoardHeightSegments() - 1) {
            this.playerPaddle.getPosition().y += this.playerPaddle.getSpeed();
        } else {
            this.playerPaddle.getPosition().y = getBoardHeightSegments() - this.playerPaddle.getHeight();
        }
    }

    /**
     * moves the enemy's paddle up according to his speed
     */
    public void enemyMoveUp() {
        if (this.enemyPaddle.getUpperBound() - this.enemyPaddle.getSpeed() >= 0) {
            this.enemyPaddle.getPosition().y -= this.enemyPaddle.getSpeed();
        } else {
            this.enemyPaddle.getPosition().y = 0;
        }
    }

    /**
     * moves the enemy's paddle downward according to his speed
     */
    public void enemyMoveDown() {
        if (this.enemyPaddle.getLowerBound() + this.enemyPaddle.getSpeed() <= getBoardHeightSegments() - 1) {
            this.enemyPaddle.getPosition().y += this.enemyPaddle.getSpeed();
        } else {
            this.enemyPaddle.getPosition().y = getBoardHeightSegments() - this.enemyPaddle.getHeight();
        }
    }

    /**
     * moves the ball according to its speed and direction vector
     */
    public void moveBall() {
        this.ball.setPosition(new Point2d(this.ball.getPosition().x + (this.ballDirection.x * this.ball.getSpeed()),
                this.ball.getPosition().y + (this.ballDirection.y * this.ball.getSpeed())));
    }

    /**
     * Returns the ball's direction vector
     * 
     * @return the ball's direction vector
     */
    public Vector2d getBallDirection() {
        return this.ballDirection;
    }

    /**
     * Set the ball's direction vetor on the indicated vector
     * 
     * @param ballDirection the vector to set on
     */
    public void setBallDirection(Vector2d ballDirection) {
        this.ballDirection = ballDirection;
    }

    /**
     * Return the ball PongObject
     * 
     * @return the ball
     */
    public PongObject getBall() {
        return this.ball;
    }

    /**
     * Returns the player's paddle PongObject
     * 
     * @return the player's paddle
     */
    public PongObject getPlayerPaddle() {
        return this.playerPaddle;
    }

    /**
     * Returns the enemy's paddle PongObject
     * 
     * @return the enemy's paddle
     */
    public PongObject getEnemyPaddle() {
        return this.enemyPaddle;
    }

    /**
     * Reset ball's position, direction and speed for a new game
     */
    public void resetBall() {
        this.getBall().setPosition(originalBallPosition);
        this.invertBallDirectionX();
        this.invertBallDirectionY();
        this.getBall().setSpeed(ballSpeed);
    }

    /**
     * Set the PongEntity to the indicated entity
     * 
     * @param entity the entity to set on
     */
    public void setEntity(PongEntity entity) {
        this.entity = entity;
    }

    /**
     * Return the PongEntity
     * 
     * @return the PongEntity
     */
    public PongEntity getEntity() {
        return this.entity;
    }

    /**
     * Reverses the direction of the ball on the Y axis
     */
    public void invertBallDirectionX() {
        this.setBallDirection(new Vector2d(-this.getBallDirection().x, this.getBallDirection().y));
    }

    /**
     * Reverses the direction of the ball on the X axis
     */
    public void invertBallDirectionY() {
        this.setBallDirection(new Vector2d(this.getBallDirection().x, -this.getBallDirection().y));
    }
}

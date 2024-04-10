package buontyhunter.physics;

import buontyhunter.model.GameObject;
import buontyhunter.model.PongObject;
import buontyhunter.model.PongPanel;
import buontyhunter.model.World;

public class PongPhysicsComponent extends PhysicsComponent {

    private static double speedEnhancer = 0.1;
    private static double speedCap = 1.0;

    @Override
    public void update(long dt, GameObject obj, World w) {
        collidingWithWall((PongPanel) obj);
        collidingWithPaddle((PongPanel) obj);
        ((PongPanel) obj).moveBall();
    }

    public void collidingWithPaddle(PongPanel pong) {

        switchPaddle(pong, pong.getPlayerPaddle(), true);
        switchPaddle(pong, pong.getEnemyPaddle(), false);
    }

    private void switchPaddle(PongPanel pong, PongObject paddle, boolean isPlayer) {
        if ((isPlayer && pong.getBall().getLeftBound() <= paddle.getRightBound()) ||
                (!isPlayer && pong.getBall().getRightBound() >= paddle.getLeftBound())) {

            if (isBetween(paddle.getUpperBound(), pong.getBall().getUpperBound(), pong.getBall().getLowerBound()) ||
                    isBetween(paddle.getLowerBound(), pong.getBall().getUpperBound(), pong.getBall().getLowerBound())) {

                pong.invertBallDirectionY();
                pong.getBall().setSpeed(pong.getBall().getSpeed() + speedEnhancer > speedCap ? speedCap
                        : pong.getBall().getSpeed() + speedEnhancer);
            }

            if (isBetween(pong.getBall().getUpperBound(), paddle.getLowerBound(), paddle.getUpperBound()) ||
                    isBetween(pong.getBall().getLowerBound(), paddle.getLowerBound(), paddle.getUpperBound())) {

                pong.invertBallDirectionX();
                pong.getBall().setSpeed(pong.getBall().getSpeed() + speedEnhancer > speedCap ? speedCap
                        : pong.getBall().getSpeed() + speedEnhancer);
            }
        }
    }

    private boolean isBetween(double x, double a, double b) {
        return (x >= a && x <= b) || (x >= b && x <= a);
    }

    public void collidingWithWall(PongPanel pong) {
        if (pong.getBall().getUpperBound() <= 0
                || pong.getBall().getLowerBound() >= pong.getBoardHeightSegments()) {
            pong.invertBallDirectionY();
        }

        if (pong.getBall().getLeftBound() < pong.getPlayerPaddle().getRightBound() - 1) {
            pong.getEntity().scored(true);
            pong.resetBall();
        }

        if (pong.getBall().getRightBound() > pong.getEnemyPaddle().getLeftBound() + 1) {
            pong.getEntity().scored(false);
            pong.resetBall();
        }
    }
}

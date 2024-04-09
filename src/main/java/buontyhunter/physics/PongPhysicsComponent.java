package buontyhunter.physics;

import buontyhunter.common.Vector2d;
import buontyhunter.model.GameObject;
import buontyhunter.model.PongPanel;
import buontyhunter.model.World;

public class PongPhysicsComponent extends PhysicsComponent {
    @Override
    public void update(long dt, GameObject obj, World w) {
        collidingWithWall((PongPanel) obj);
        ((PongPanel) obj).moveBall();
        System.out.println(((PongPanel) obj).getBall().getPosition());
    }

    public void collidingWithPaddle(PongPanel pong) {
        /* Colliding with player paddle */
        if (pong.getBall().getLeftBound() <= pong.getPlayerPaddle().getRightBound() &&
                ((pong.getBall().getUpperBound() <= pong.getPlayerPaddle().getLowerBound() &&
                        pong.getBall().getUpperBound() >= pong.getPlayerPaddle().getUpperBound()) ||
                        (pong.getBall().getLowerBound() <= pong.getPlayerPaddle().getLowerBound() &&
                                pong.getBall().getLowerBound() >= pong.getPlayerPaddle().getUpperBound()))) {
            this.invertBallDirectionX(pong);
        }

        /* Colliding with enemy paddle */
        if (pong.getBall().getRightBound() >= pong.getEnemyPaddle().getLeftBound() &&
                ((pong.getBall().getUpperBound() <= pong.getEnemyPaddle().getLowerBound() &&
                        pong.getBall().getUpperBound() >= pong.getEnemyPaddle().getUpperBound()) ||
                        (pong.getBall().getLowerBound() <= pong.getEnemyPaddle().getLowerBound() &&
                                pong.getBall().getLowerBound() >= pong.getEnemyPaddle().getUpperBound()))) {
            this.invertBallDirectionY(pong);
        }
    }

    public void collidingWithWall(PongPanel pong) {
        if (pong.getBall().getUpperBound() <= 0
                || pong.getBall().getLowerBound() >= pong.getBoardHeightSegments() - 1) {
            this.invertBallDirectionY(pong);
        }

        if (pong.getBall().getLeftBound() <= 0 || pong.getBall().getRightBound() >= pong.getBoardWidthSegments() - 1) {
            this.invertBallDirectionX(pong);
        }
    }

    private void invertBallDirectionX(PongPanel pong) {
        pong.setBallDirection(new Vector2d(-pong.getBallDirection().x, pong.getBallDirection().y));
    }

    private void invertBallDirectionY(PongPanel pong) {
        pong.setBallDirection(new Vector2d(pong.getBallDirection().x, -pong.getBallDirection().y));
    }
}

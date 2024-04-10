package buontyhunter.model.AI;

import buontyhunter.model.PongPanel;

public class AIPongImpl implements AIPong {

    @Override
    public void movePaddle(PongPanel pong) {
        if (pong.getBall().getUpperBound() >= pong.getEnemyPaddle().getUpperBound()) {
            pong.enemyMoveDown();
        } else if (pong.getBall().getLowerBound() <= pong.getEnemyPaddle().getLowerBound()) {
            pong.enemyMoveUp();
        }
    }

}

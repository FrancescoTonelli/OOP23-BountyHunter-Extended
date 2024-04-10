package buontyhunter.model.AI;

import buontyhunter.model.PongPanel;

public interface AIPong {
    /**
     * moves the paddle down if the ball is further down, or up if the ball is
     * higher
     * 
     * @param pong the panel on which to make checks and changes
     */
    void movePaddle(PongPanel pong);
}

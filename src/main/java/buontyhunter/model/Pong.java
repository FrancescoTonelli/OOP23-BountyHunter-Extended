package buontyhunter.model;

public interface Pong {
    /**
     * This method is called if a point is scored. If the player scores a point, he
     * is given 3 doblons, if he loses one, one is taken away from him (if he
     * doesn't have any, nothing happens)
     * 
     * @param scoredAgainst false if the player scored, true otherwise
     */
    void scored(boolean scoredAgainst);

    /**
     * Return the PongPanel of the entity
     * 
     * @return the pong panel as an HidableObject
     */
    HidableObject getPanel();
}

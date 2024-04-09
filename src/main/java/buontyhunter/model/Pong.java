package buontyhunter.model;

public interface Pong {
    void scored(PlayerEntity player, boolean scoredAgainst);

    HidableObject getPanel();
}

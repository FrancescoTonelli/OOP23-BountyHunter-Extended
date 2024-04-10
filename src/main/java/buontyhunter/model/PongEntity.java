package buontyhunter.model;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;

public class PongEntity extends InterractableArea implements Pong {

    private PlayerEntity player;

    public PongEntity(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, HidableObject panel,
            PlayerEntity player) {
        super(type, pos, vel, box, panel);
        ((PongPanel) this.getPanel()).setEntity(this);
        this.player = player;
    }

    @Override
    public void scored(boolean scoredAgainst) {
        if (scoredAgainst) {
            this.player.withdrawDoblons(1);
        } else {
            this.player.depositDoblons(3);
        }
    }

    @Override
    public HidableObject getPanel() {
        return super.getPanel();
    }

}

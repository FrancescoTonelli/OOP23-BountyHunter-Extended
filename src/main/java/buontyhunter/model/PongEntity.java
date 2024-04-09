package buontyhunter.model;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;

public class PongEntity extends InterractableArea implements Pong {

    public PongEntity(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, HidableObject panel) {
        super(type, pos, vel, box, panel);
    }

    @Override
    public void scored(PlayerEntity player, boolean scoredAgainst) {
        if (scoredAgainst) {
            player.withdrawDoblons(1);
        } else {
            player.depositDoblons(3);
        }
    }

    @Override
    public HidableObject getPanel() {
        return super.getPanel();
    }

}

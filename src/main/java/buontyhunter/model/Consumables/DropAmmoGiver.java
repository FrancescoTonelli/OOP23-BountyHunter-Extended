package buontyhunter.model.Consumables;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.GraphicsComponent;
import buontyhunter.input.InputComponent;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.PlayerEntity;
import buontyhunter.physics.PhysicsComponent;

public class DropAmmoGiver extends Consumable{

    public DropAmmoGiver(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, InputComponent input,
            GraphicsComponent graph, PhysicsComponent phys) {
        super(type, pos, vel, box, input, graph, phys);
    }

    @Override
    public void apply(PlayerEntity player) {
        player.giveAmmo(3);
    }
    
}

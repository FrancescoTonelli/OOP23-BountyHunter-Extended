package buontyhunter.model.consumables;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.GraphicsComponent;
import buontyhunter.input.InputComponent;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.PlayerEntity;
import buontyhunter.physics.PhysicsComponent;

public class PowerUpDamage extends Consumable{

    private static float additive = (float)0.5;

    public PowerUpDamage(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, InputComponent input,
            GraphicsComponent graph, PhysicsComponent phys, int id) {
        super(type, pos, vel, box, input, graph, phys, id);
    }

    @Override
    public void apply(PlayerEntity player) {
        player.setDamageMultiplier(player.getDamageMultiplier() + additive);
        System.out.println("damage: " + player.getDamageMultiplier());
    }
    
}

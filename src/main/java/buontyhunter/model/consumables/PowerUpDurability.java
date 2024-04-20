package buontyhunter.model.consumables;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.GraphicsComponent;
import buontyhunter.input.InputComponent;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.PlayerEntity;
import buontyhunter.model.weaponClasses.MeleeWeapon;
import buontyhunter.physics.PhysicsComponent;

public class PowerUpDurability extends Consumable{

    public PowerUpDurability(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, InputComponent input,
            GraphicsComponent graph, PhysicsComponent phys, int id) {
        super(type, pos, vel, box, input, graph, phys, id);
    }

    @Override
    public void apply(PlayerEntity player) {
        if(player.getWeapon() instanceof MeleeWeapon){
            ((MeleeWeapon)player.getWeapon()).setDurability(((MeleeWeapon)player.getWeapon()).getMaxDurability());
        }
    }
    
}

package buontyhunter.physics;

import buontyhunter.model.CollisionDetector;
import buontyhunter.model.GameObject;
import buontyhunter.model.PlayerEntity;
import buontyhunter.model.RectBoundingBox;
import buontyhunter.model.World;
import buontyhunter.model.Consumables.Consumable;
import buontyhunter.model.Consumables.DropAmmoGiver;
import buontyhunter.weaponClasses.RangedWeapon;

public class ConsumablesPhysicsComponent extends PhysicsComponent{
    public void update(long dt, GameObject obj, World w) {
        var consumable = (Consumable) obj;
        CollisionDetector detector = new CollisionDetector();

        try {
            if (detector.isColliding((RectBoundingBox) consumable.getBBox(), w.getPlayer().getPos())) {
                if(consumable instanceof DropAmmoGiver && !(((PlayerEntity)w.getPlayer()).getWeapon() instanceof RangedWeapon)){
                    return;
                }
                
                w.getConsumableManager().applyConsumable((PlayerEntity)w.getPlayer(), consumable);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

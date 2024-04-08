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

    private static float DAMAGE_MULTIPLIER = 2;
    private static int DURATION = 10000;
    private long usedTime;

    public PowerUpDamage(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, InputComponent input,
            GraphicsComponent graph, PhysicsComponent phys, int id) {
        super(type, pos, vel, box, input, graph, phys, id);
    }

    @Override
    public void apply(PlayerEntity player) {
        player.setDamageMultiplier(player.getDamageMultiplier() * DAMAGE_MULTIPLIER);
    }

    public void disable(PlayerEntity player){
        player.setDamageMultiplier(player.getDamageMultiplier() / DAMAGE_MULTIPLIER);
    }

    @Override
    public void use(){
        super.use();
        this.usedTime = System.currentTimeMillis();
    }

    public int getDurationInMillis(){
        return DURATION;
    }

    public long getUsedTime(){
        return this.usedTime;
    }
    
}

package buontyhunter.model.consumables;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.GraphicsComponent;
import buontyhunter.input.InputComponent;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.PlayerEntity;
import buontyhunter.physics.PhysicsComponent;

public class PowerUpDamage extends Consumable {

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

    /**
     * Nullifies the effect of the power up on the player
     * 
     * @param player the player to cancel the effect on
     */
    public void disable(PlayerEntity player) {
        player.setDamageMultiplier(player.getDamageMultiplier() / DAMAGE_MULTIPLIER);
    }

    @Override
    public void use() {
        super.use();
        this.usedTime = System.currentTimeMillis();
    }

    /**
     * Returns the power up duration in milliseconds
     * 
     * @return the duration in milliseconds
     */
    public int getDurationInMillis() {
        return DURATION;
    }

    /**
     * Returns the moment in which the power up was activated
     * 
     * @return the time in milliseconds
     */
    public long getUsedTime() {
        return this.usedTime;
    }

}

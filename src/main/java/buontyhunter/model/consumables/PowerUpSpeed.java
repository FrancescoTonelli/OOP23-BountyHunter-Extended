package buontyhunter.model.consumables;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.GraphicsComponent;
import buontyhunter.input.InputComponent;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.PlayerEntity;
import buontyhunter.physics.PhysicsComponent;

public class PowerUpSpeed extends Consumable {

    private static double SPEED_MULTIPLIER = 1.5;
    private static int DURATION = 10000;
    private long usedTime;

    public PowerUpSpeed(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, InputComponent input,
            GraphicsComponent graph, PhysicsComponent phys, int id) {
        super(type, pos, vel, box, input, graph, phys, id);
    }

    @Override
    public void apply(PlayerEntity player) {
        player.setMovementSpeed(player.getMovementSpeed() * SPEED_MULTIPLIER);
    }

    /**
     * Nullifies the effect of the power up on the player
     * 
     * @param player the player to cancel the effect on
     */
    public void disable(PlayerEntity player) {
        player.setMovementSpeed(player.getMovementSpeed() / SPEED_MULTIPLIER);
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

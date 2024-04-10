package buontyhunter.model.consumables;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.GraphicsComponent;
import buontyhunter.input.InputComponent;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObject;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.PlayerEntity;
import buontyhunter.physics.PhysicsComponent;

public abstract class Consumable extends GameObject {
    private final int id;
    private boolean used;

    public Consumable(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, InputComponent input,
            GraphicsComponent graph, PhysicsComponent phys, int id) {
        super(type, pos, vel, box, input, graph, phys);
        this.id = id;
        this.used = false;
    }

    /**
     * Activate the consumable effects on the player
     * 
     * @param player the player to whom the consumable is applied
     */
    public abstract void apply(final PlayerEntity player);

    /**
     * Get the consumable id
     * 
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Checks if the consumable has been used
     * 
     * @return true if it has been used, false otherwise
     */
    public boolean isUsed() {
        return this.used;
    }

    /**
     * changes the usage flag to indicate that a consumable has been used
     */
    public void use() {
        this.used = true;
    }
}

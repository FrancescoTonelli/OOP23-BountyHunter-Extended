package buontyhunter.model.Consumables;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.GraphicsComponent;
import buontyhunter.input.InputComponent;
import buontyhunter.input.NullInputComponent;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObject;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.PlayerEntity;
import buontyhunter.physics.NullPhysicsComponent;
import buontyhunter.physics.PhysicsComponent;

public abstract class Consumable extends GameObject{

    public Consumable(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, InputComponent input,
            GraphicsComponent graph, PhysicsComponent phys) {
        super(type, pos, null, box, new NullInputComponent(), graph, new NullPhysicsComponent());
    }

    public abstract void apply(final PlayerEntity player);
}

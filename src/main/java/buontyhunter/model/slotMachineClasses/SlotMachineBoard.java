package buontyhunter.model.slotMachineClasses;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.GraphicsComponent;
import buontyhunter.input.InputComponent;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.HidableObject;
import buontyhunter.physics.PhysicsComponent;

public class SlotMachineBoard extends HidableObject{

    public SlotMachineBoard(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, InputComponent input,
            GraphicsComponent graph, PhysicsComponent phys, boolean show) {
        
            super(type, pos, vel, box, input, graph, phys, show);
        //TODO Auto-generated constructor stub
    }
    
}

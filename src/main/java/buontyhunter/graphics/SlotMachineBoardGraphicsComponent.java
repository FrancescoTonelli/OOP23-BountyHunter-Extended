package buontyhunter.graphics;

import buontyhunter.model.GameObject;
import buontyhunter.model.World;
import buontyhunter.model.slotMachineClasses.SlotMachineBoard;

public class SlotMachineBoardGraphicsComponent implements GraphicsComponent {

    @Override
    public void update(GameObject obj, Graphics w, World world) {
        w.drawSlotMachineBoard((SlotMachineBoard)obj, world);
    }
    
}

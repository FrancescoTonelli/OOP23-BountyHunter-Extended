package buontyhunter.graphics;

import buontyhunter.model.GameObject;
import buontyhunter.model.World;
import buontyhunter.model.consumables.Consumable;

public class ConsumablesGraphicsComponent implements GraphicsComponent{

    @Override
    public void update(GameObject obj, Graphics w, World world) {
        w.drawConsumable((Consumable)obj);
    }
}

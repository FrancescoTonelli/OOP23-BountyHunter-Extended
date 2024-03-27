package buontyhunter.graphics;

import buontyhunter.common.Point2d;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.Consumables.Consumable;

public interface ConsumableGraphics {
    void drawConsumable(Consumable obj, Point2d pos, BoundingBox bBox);
}

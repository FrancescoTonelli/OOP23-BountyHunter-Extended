package buontyhunter.graphics;

import buontyhunter.model.consumables.Consumable;

public interface ConsumableGraphics {
    /**
     * Draws the consumable indicated on the game world
     * 
     * @param obj      the consumable to draw
     * @param xInPixel the x coordinate in pixel
     * @param yInPixel the y coordinate in pixel
     */
    void drawConsumable(Consumable obj, int xInPixel, int yInPixel);
}

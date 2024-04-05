package buontyhunter.model.consumables;

import buontyhunter.common.Point2d;
import buontyhunter.model.PlayerEntity;
import buontyhunter.model.World;

import java.util.List;

public interface ConsumableManager {
    List<Consumable> getAllConsumables();
    void applyConsumable(PlayerEntity player, Consumable consumable);
    void generateNewDrop(Point2d position);
    void generatePowerUp(World w);
    void disableUsedPowerUps(PlayerEntity player, boolean disableAll);
}

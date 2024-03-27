package buontyhunter.model.Consumables;

import buontyhunter.common.Point2d;
import buontyhunter.model.PlayerEntity;
import java.util.List;

public interface ConsumableManager {
    List<Consumable> getAllConsumables();
    void applyConsumable(PlayerEntity player, Consumable consumable);
    void addConsumable(Consumable newConsumable);
    void deleteConsumable(Consumable consumable);
    void generateNewDrop(PlayerEntity player, Point2d position);
}

package buontyhunter.model.Consumables;

import buontyhunter.common.Point2d;
import buontyhunter.model.PlayerEntity;
import java.util.List;

public interface ConsumableManager {
    List<Consumable> isOnConsumables(PlayerEntity plr);
    List<Consumable> getAllConsumables();
    void applyEachConsumableUnderPlayer(PlayerEntity plr);
    void addConsumable(Consumable newConsumable);
    void deleteConsumable(Consumable consumable);
    void generateNewDrop(Point2d position);
}

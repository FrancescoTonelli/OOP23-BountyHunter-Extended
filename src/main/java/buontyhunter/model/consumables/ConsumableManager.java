package buontyhunter.model.consumables;

import buontyhunter.common.Point2d;
import buontyhunter.model.PlayerEntity;
import buontyhunter.model.World;

import java.util.List;

public interface ConsumableManager {
    /**
     * Get all the consumables of the manager
     * 
     * @return the list of consumables
     */
    List<Consumable> getAllConsumables();

    /**
     * Applies the indicated consumable to the player
     * 
     * @param player     the player to whom the consumable is applied
     * @param consumable the consumable to apply
     */
    void applyConsumable(PlayerEntity player, Consumable consumable);

    /**
     * Generate a drop consumable at the indicated position
     * 
     * @param position where to generate the consumable
     */
    void generateNewDrop(Point2d position);

    /**
     * Generates all power ups in the indicated world
     * 
     * @param w the world in which to generate power ups
     */
    void generatePowerUp(World w);

    /**
     * Disable the used power ups effects after their duration time
     * 
     * @param player     the player on which the power ups are applicated
     * @param disableAll true for disale all used power ups, false otherwise
     */
    void disableUsedPowerUps(PlayerEntity player, boolean disableAll);
}

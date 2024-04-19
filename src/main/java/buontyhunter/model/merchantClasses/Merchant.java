package buontyhunter.model.merchantClasses;

public interface Merchant {

    /**
     * Returns by the HidableObject the current upgrading cost for armour
     * @return upgrading cost for armour
     */
    public int getUpgradeArmourCost();
    
    /**
     * Returns by the HidableObject the current upgrading cost for damage
     * @return upgrading cost for armour
     */
    public int getUpgradeDamageCost();

    /**
     * Checks by the HidableObject the current upgrading cost for armour
     * and updates eventually
     */
    public void updateUpgradeArmourCost();

    /**
     * Checks by the HidableObject the current upgrading cost for damage
     * and updates eventually
     */
    public void updateUpgradeDamageCost();
    
}

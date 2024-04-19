package buontyhunter.model.merchantClasses;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.GraphicsComponent;
import buontyhunter.input.InputComponent;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.HidableObject;
import buontyhunter.model.PlayerEntity;
import buontyhunter.physics.PhysicsComponent;

public class MerchantMenu extends HidableObject {

    private PlayerEntity player;
    private int upgradeArmourCost;
    private int upgradeDamageCost;
    private boolean weaponSold;

    public MerchantMenu(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, InputComponent input,
            GraphicsComponent graph, PhysicsComponent phys, boolean show, PlayerEntity player) {
        super(type, pos, vel, box, input, graph, phys, show);

        weaponSold=false;
        this.player=player;
    }

    public int getUpgradeArmourCost(){
        return upgradeArmourCost;
    }
    public int getUpgradeDamageCost(){
        return upgradeDamageCost;
    }

    public void updateUpgradeArmourCost(){
        upgradeArmourCost = player.getArmourLevel()*50;
    }
    public void updateUpgradeDamageCost(){
        upgradeDamageCost = (int)(player.getDamageMultiplier()*100);
    }

    public boolean upgradeArmour(){
        updateUpgradeArmourCost();

        if(player.getDoblons()>=getUpgradeArmourCost()){
            
            if(player.levelUpArmour()){
                
                player.withdrawDoblons(getUpgradeArmourCost());
                updateUpgradeArmourCost();
                return true;
            }
        }
        
        return false;
    }

    public boolean upgradeDamage(){
        updateUpgradeDamageCost();
        
        if(player.getDoblons()>=getUpgradeDamageCost()){
            
            player.withdrawDoblons(getUpgradeDamageCost());
            player.setDamageMultiplier(player.getDamageMultiplier()+(float)0.5);
            updateUpgradeDamageCost();
            return true;
        }
        
        return false;
    }

    public boolean buyNewWeapon(){
        if(!weaponSold&&player.getDoblons()>=1000){

            player.withdrawDoblons(1000);
            player.weaponBought();
            return true;
        }

        return false;
    }

}

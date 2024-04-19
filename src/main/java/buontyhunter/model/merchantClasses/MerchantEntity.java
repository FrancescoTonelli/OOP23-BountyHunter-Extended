package buontyhunter.model.merchantClasses;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.HidableObject;
import buontyhunter.model.InterractableArea;


public class MerchantEntity extends InterractableArea implements Merchant{


    public MerchantEntity(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, HidableObject panel,
                    HidableObject menu) {
        super(type, pos, vel, box, menu);
        
    }

    public int getUpgradeArmourCost(){
        return ((MerchantMenu)getPanel()).getUpgradeArmourCost();
    }
    public int getUpgradeDamageCost(){
        return ((MerchantMenu)getPanel()).getUpgradeDamageCost();
    }

    public void updateUpgradeArmourCost(){
        ((MerchantMenu)getPanel()).updateUpgradeArmourCost();
    }
    public void updateUpgradeDamageCost(){
        ((MerchantMenu)getPanel()).updateUpgradeDamageCost();
    }
    
}

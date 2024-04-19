package buontyhunter.graphics;

import buontyhunter.model.GameObject;
import buontyhunter.model.World;
import buontyhunter.model.merchantClasses.MerchantMenu;

public class MerchantMenuGraphicsComponent implements GraphicsComponent {
    
    @Override
    public void update(GameObject obj, Graphics w, World world) {
        w.drawMerchantMenu((MerchantMenu)obj, world);
    }

    
}

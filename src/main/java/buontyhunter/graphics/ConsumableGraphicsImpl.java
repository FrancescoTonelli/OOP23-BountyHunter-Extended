package buontyhunter.graphics;

import java.awt.*;

import buontyhunter.common.ImageType;
import buontyhunter.model.Consumables.Consumable;
import buontyhunter.model.Consumables.DropAmmoGiver;

public class ConsumableGraphicsImpl implements ConsumableGraphics{

    private Graphics2D g2;
	private SceneCamera camera;
	private SwingAssetProvider assetManager;

    public ConsumableGraphicsImpl(final Graphics2D g2, final SceneCamera camera, final SwingAssetProvider assetManager){
        this.g2 = g2;
        this.camera = camera;
        this.assetManager = assetManager;
    }

    @Override
    public void drawConsumable(final Consumable obj, final int xInPixel, final int yInPixel) {
        var consumable = (Consumable)obj;
        var point = camera.getObjectPointInScene(consumable.getPos());

		if(point.isPresent()){


            Image toDraw = null;

            if(consumable instanceof DropAmmoGiver){
                toDraw = this.assetManager.getImage(ImageType.arrow);
            }

            g2.drawImage(toDraw, xInPixel, 
                        yInPixel, null);
        }
    }
    
}

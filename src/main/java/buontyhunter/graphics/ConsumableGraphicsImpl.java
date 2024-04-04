package buontyhunter.graphics;

import java.awt.*;

import buontyhunter.common.ImageType;
import buontyhunter.model.consumables.Consumable;
import buontyhunter.model.consumables.DropAmmoGiver;
import buontyhunter.model.consumables.DropDoblonsGiver;
import buontyhunter.model.consumables.DropHealthGiver;
import buontyhunter.model.consumables.PowerUpDamage;
import buontyhunter.model.consumables.PowerUpDurability;
import buontyhunter.model.consumables.PowerUpSpeed;

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
            }else if(consumable instanceof DropDoblonsGiver){
                toDraw = this.assetManager.getImage(ImageType.doblon);
            }
            else if(consumable instanceof DropHealthGiver){
                toDraw = this.assetManager.getImage(ImageType.potion);
            }
            else if(consumable instanceof PowerUpDurability){
                toDraw = this.assetManager.getImage(ImageType.hammer);
            }
            else if(consumable instanceof PowerUpSpeed){
                toDraw = this.assetManager.getImage(ImageType.speedUp);
            }
            else if(consumable instanceof PowerUpDamage){
                toDraw = this.assetManager.getImage(ImageType.damageUp);
            }

            g2.drawImage(toDraw, xInPixel, 
                        yInPixel, null);
        }
    }
    
}

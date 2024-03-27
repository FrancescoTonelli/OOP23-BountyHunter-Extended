package buontyhunter.graphics;

import java.awt.*;

import buontyhunter.common.ImageType;
import buontyhunter.common.Point2d;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.RectBoundingBox;
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
    public void drawConsumable(final Consumable obj, final Point2d pos, final BoundingBox bBox) {
        if(camera.inScene(pos)){
            Image toDraw = null;

            if(obj instanceof DropAmmoGiver){
                toDraw = this.assetManager.getImage(ImageType.arrow);
            }

            g2.drawImage(toDraw, (int)obj.getPos().x, 
                        (int)obj.getPos().x, (int)((RectBoundingBox)obj.getBBox()).getWidth(), 
                        (int)((RectBoundingBox)obj.getBBox()).getHeight(), null);
        }
    }
    
}

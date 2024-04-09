package buontyhunter.graphics;

import buontyhunter.model.GameObject;
import buontyhunter.model.PongPanel;
import buontyhunter.model.World;

public class PongPanelGraphicsComponent implements GraphicsComponent {

    @Override
    public void update(GameObject obj, Graphics w, World world) {
        w.drawPongPanel((PongPanel) obj);
    }

}

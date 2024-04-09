package buontyhunter.input;

import buontyhunter.model.GameObject;
import buontyhunter.model.World;

public class PongInputComponent implements InputComponent {

    @Override
    public void update(GameObject ball, InputController c, World w) {
        if (c.isAttackUp()) {

        } else if (c.isAttackDown()) {

        }
        System.out.println("PongInputComponent");
    }

}

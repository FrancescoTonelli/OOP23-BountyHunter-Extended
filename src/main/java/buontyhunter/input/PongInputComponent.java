package buontyhunter.input;

import buontyhunter.model.GameObject;
import buontyhunter.model.PongPanel;
import buontyhunter.model.World;
import buontyhunter.model.AI.AIPong;
import buontyhunter.model.AI.AIPongImpl;

public class PongInputComponent implements InputComponent {

    private final AIPong ai;

    public PongInputComponent() {
        this.ai = new AIPongImpl();
    }

    @Override
    public void update(GameObject ball, InputController c, World w) {
        if (c.isAttackUp()) {
            ((PongPanel) ball).playerMoveUp();
        } else if (c.isAttackDown()) {
            ((PongPanel) ball).playerMoveDown();
        }
        ai.movePaddle((PongPanel) ball);
    }

}

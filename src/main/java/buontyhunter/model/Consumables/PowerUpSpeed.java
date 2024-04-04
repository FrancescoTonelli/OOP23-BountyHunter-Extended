package buontyhunter.model.consumables;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.GraphicsComponent;
import buontyhunter.input.InputComponent;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.PlayerEntity;
import buontyhunter.physics.PhysicsComponent;

public class PowerUpSpeed extends Consumable{

    private static double speedMultiplier = 1.5;
    private static int duration = 3000;

    public PowerUpSpeed(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, InputComponent input,
            GraphicsComponent graph, PhysicsComponent phys, int id) {
        super(type, pos, vel, box, input, graph, phys, id);
    }

    @Override
    public void apply(PlayerEntity player) {
        SpeedThread t = new SpeedThread(player);
        t.start();
    }
    
    private class SpeedThread extends Thread{

        private final PlayerEntity player;

        public SpeedThread(PlayerEntity player){
            this.player = player;
        }

        @Override
        public void run() {
            
            try {
                this.player.setMovementSpeed(this.player.getMovementSpeed() * speedMultiplier);
                Thread.sleep(duration);
                this.player.setMovementSpeed(this.player.getMovementSpeed() / speedMultiplier);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

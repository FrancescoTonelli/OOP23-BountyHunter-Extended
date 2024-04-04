package buontyhunter.model.consumables;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.GraphicsComponent;
import buontyhunter.input.InputComponent;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.PlayerEntity;
import buontyhunter.physics.PhysicsComponent;

public class PowerUpDamage extends Consumable{

    private static float damageMultiplier = 2;
    private static int duration = 3000;

    public PowerUpDamage(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, InputComponent input,
            GraphicsComponent graph, PhysicsComponent phys, int id) {
        super(type, pos, vel, box, input, graph, phys, id);
    }

    @Override
    public void apply(PlayerEntity player) {
        DamageThread t = new DamageThread(player);
        t.start();
    }

    private class DamageThread extends Thread{

        private final PlayerEntity player;

        public DamageThread(PlayerEntity player){
            this.player = player;
        }

        @Override
        public void run() {
            
            try {
                this.player.setDamageMultiplier(this.player.getDamageMultiplier() * damageMultiplier);
                Thread.sleep(duration);
                this.player.setDamageMultiplier(this.player.getDamageMultiplier() / damageMultiplier);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}

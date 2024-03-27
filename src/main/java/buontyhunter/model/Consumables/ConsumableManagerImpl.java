package buontyhunter.model.Consumables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.ConsumablesGraphicsComponent;
import buontyhunter.input.NullInputComponent;
import buontyhunter.model.CollisionDetector;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.PlayerEntity;
import buontyhunter.model.RectBoundingBox;
import buontyhunter.physics.ConsumablesPhysicsComponent;

public class ConsumableManagerImpl implements ConsumableManager{

    final List<Consumable> consumablesList;
    final CollisionDetector collisionDetector;
    private static int standardBBoxDimension = 1;

    public ConsumableManagerImpl(){
        consumablesList = new ArrayList<>();
        collisionDetector = new CollisionDetector();
    }

    @Override
    public void addConsumable(Consumable newConsumable) {
        consumablesList.add(newConsumable);
    }

    @Override
    public void deleteConsumable(Consumable consumable) {
        consumablesList.remove(consumable);
    }

    @Override
    public void generateNewDrop(PlayerEntity player, Point2d position) {
        Random rand = new Random();
        int val = rand.nextInt(10);
        if(true || val <= 2){
            this.consumablesList.add(new DropAmmoGiver(GameObjectType.Consumable, position, new Vector2d(0, 0), 
            new RectBoundingBox(position, standardBBoxDimension, standardBBoxDimension), 
            new NullInputComponent(), new ConsumablesGraphicsComponent(), new ConsumablesPhysicsComponent()));

        }else if(val <= 5){
            /* Spawn healthGiver */
        }
        else{
            /* Spawn moneyGiver */
        }
    }

    @Override
    public List<Consumable> getAllConsumables() {
        return this.consumablesList;
    }

    @Override
    public void applyConsumable(PlayerEntity player, Consumable consumable) {
        this.getAllConsumables().stream().filter(i -> i.equals(consumable)).forEach(i -> i.apply(player));
    }
    
}

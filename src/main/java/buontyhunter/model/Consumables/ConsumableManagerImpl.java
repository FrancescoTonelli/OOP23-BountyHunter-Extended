package buontyhunter.model.Consumables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import buontyhunter.common.Point2d;
import buontyhunter.graphics.ConsumablesGraphicsComponent;
import buontyhunter.input.NullInputComponent;
import buontyhunter.model.CollisionDetector;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.PlayerEntity;
import buontyhunter.model.RectBoundingBox;
import buontyhunter.physics.NullPhysicsComponent;

public class ConsumableManagerImpl implements ConsumableManager{

    final List<Consumable> consumablesList;
    final CollisionDetector collisionDetector;
    private static int standardBBoxDimension = 1;

    public ConsumableManagerImpl(){
        consumablesList = new ArrayList<>();
        collisionDetector = new CollisionDetector();
    }

    @Override
    public List<Consumable> isOnConsumables(PlayerEntity plr) {
        return consumablesList.stream().filter(i -> collisionDetector.isColliding((RectBoundingBox)i.getBBox(), plr.getPos())).toList();
    }

    @Override
    public void applyEachConsumableUnderPlayer(PlayerEntity plr) {
        isOnConsumables(plr).stream().forEach(i -> {i.apply(plr); this.deleteConsumable(i);});
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
    public void generateNewDrop(Point2d position) {
        Random rand = new Random();
        int val = rand.nextInt(10);
        if(val <= 2){
            this.consumablesList.add(new DropAmmoGiver(GameObjectType.Consumable, position, null, 
                                    new RectBoundingBox(position, standardBBoxDimension, standardBBoxDimension), 
                                    new NullInputComponent(), new ConsumablesGraphicsComponent(), new NullPhysicsComponent()));
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
    
}

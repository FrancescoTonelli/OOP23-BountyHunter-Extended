package buontyhunter.model.Consumables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.ConsumablesGraphicsComponent;
import buontyhunter.input.NullInputComponent;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.PlayerEntity;
import buontyhunter.model.RectBoundingBox;
import buontyhunter.physics.ConsumablesPhysicsComponent;

public class ConsumableManagerImpl implements ConsumableManager{

    private List<Consumable> consumablesList;
    private int last_id;

    public ConsumableManagerImpl(){
        consumablesList = new ArrayList<>();
        last_id = 0;
    }

    @Override
    public void generateNewDrop(PlayerEntity player, Point2d position) {
        Random rand = new Random();
        int val = rand.nextInt(10);
        if(val <= 1){
            this.consumablesList.add(new DropAmmoGiver(GameObjectType.Consumable, position, new Vector2d(0, 0), 
            genBoundingBox(position), 
            new NullInputComponent(), new ConsumablesGraphicsComponent(), new ConsumablesPhysicsComponent(), last_id));

        }else if(val <= 3){
            this.consumablesList.add(new DropHealthGiver(GameObjectType.Consumable, position, new Vector2d(0, 0), 
            genBoundingBox(position), 
            new NullInputComponent(), new ConsumablesGraphicsComponent(), new ConsumablesPhysicsComponent(), last_id));
        }
        else{
            this.consumablesList.add(new DropDoblonsGiver(GameObjectType.Consumable, position, new Vector2d(0, 0),
            genBoundingBox(position), new NullInputComponent(), new ConsumablesGraphicsComponent(),
            new ConsumablesPhysicsComponent(), last_id));
        }
        last_id++;
    }

    private RectBoundingBox genBoundingBox(Point2d position){
        return new RectBoundingBox(new Point2d(position.x - 0.3, position.y - 0.3), 1, 1);
    }

    @Override
    public List<Consumable> getAllConsumables() {
        return this.consumablesList;
    }

    @Override
    public void applyConsumable(PlayerEntity player, Consumable consumable) {
        this.getAllConsumables().stream().filter(i -> i.getId() == consumable.getId()).forEach(i -> {i.apply(player); i.use();});
        
        this.consumablesList = new ArrayList<>(this.getAllConsumables().stream().filter(i->!i.isUsed()).toList());
    }
    
}

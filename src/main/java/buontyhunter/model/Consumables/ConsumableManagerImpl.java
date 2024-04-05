package buontyhunter.model.consumables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.ConsumablesGraphicsComponent;
import buontyhunter.input.NullInputComponent;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.PlayerEntity;
import buontyhunter.model.RectBoundingBox;
import buontyhunter.model.Tile;
import buontyhunter.model.World;
import buontyhunter.physics.ConsumablesPhysicsComponent;

public class ConsumableManagerImpl implements ConsumableManager{

    private List<Consumable> consumablesList;
    private List<Consumable> powerUpsInUse;
    private int last_id;
    private static int powerUpQty = 100;

    public ConsumableManagerImpl(){
        consumablesList = new ArrayList<>();
        powerUpsInUse = new ArrayList<>();
        last_id = 0;
    }

    @Override
    public void generateNewDrop(Point2d position) {
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
        this.getAllConsumables().stream().filter(i -> i.getId() == consumable.getId()).forEach(i -> {
            i.apply(player);
            i.use();
            if(i instanceof PowerUpDamage || i instanceof PowerUpSpeed){
                this.powerUpsInUse.add(i);
            }
        });
        
        this.consumablesList = new ArrayList<>(this.getAllConsumables().stream().filter(i->!i.isUsed()).toList());
    }

    @Override
    public void generatePowerUp(World w) {

        List<Tile> avaiableTiles = w.getTileManager().getTiles().stream().flatMap(List::stream)
                                    .filter(t -> t.isTraversable()).collect(Collectors.toList());

        if (avaiableTiles.size() == 0) {
            return;
        }

        var random = new Random();
        for (int i = 0; i < powerUpQty; i++) {
            this.consumablesList.add(selectPowerUp(avaiableTiles.get(random.nextInt(avaiableTiles.size())).getPoint(), i));
            this.last_id++;
        }
    }

    private Consumable selectPowerUp(Point2d position, int selector){
        int normalizedSelector = selector;
        while(normalizedSelector > 2){
            normalizedSelector -= 3;
        }

        switch(normalizedSelector){
            case 0:
                return new PowerUpDamage(GameObjectType.Consumable, position, new Vector2d(0, 0), 
                genBoundingBox(position), 
                new NullInputComponent(), new ConsumablesGraphicsComponent(), new ConsumablesPhysicsComponent(), last_id);
            case 1:
                return new PowerUpDurability(GameObjectType.Consumable, position, new Vector2d(0, 0), 
                genBoundingBox(position), 
                new NullInputComponent(), new ConsumablesGraphicsComponent(), new ConsumablesPhysicsComponent(), last_id);
            default:
                return new PowerUpSpeed(GameObjectType.Consumable, position, new Vector2d(0, 0), 
                genBoundingBox(position), 
                new NullInputComponent(), new ConsumablesGraphicsComponent(), new ConsumablesPhysicsComponent(), last_id);
        }
    }

    @Override
    public void disableUsedPowerUps(PlayerEntity player, boolean disableAll) {
        if(disableAll){
            this.powerUpsInUse.stream().filter(i -> i instanceof PowerUpDamage).forEach(i -> ((PowerUpDamage)i).disable(player));
            this.powerUpsInUse.stream().filter(i -> i instanceof PowerUpSpeed).forEach(i -> ((PowerUpSpeed)i).disable(player));
            this.powerUpsInUse = new ArrayList<>();
        }
        else{
            this.powerUpsInUse.stream()
            .filter(i -> i instanceof PowerUpDamage 
                && timeInMillisPassed(((PowerUpDamage)i).getUsedTime()) > ((PowerUpDamage)i).getDurationInMillis())
            .forEach(i -> ((PowerUpDamage)i).disable(player));

            this.powerUpsInUse.stream()
            .filter(i -> i instanceof PowerUpSpeed 
                && timeInMillisPassed(((PowerUpSpeed)i).getUsedTime()) > ((PowerUpSpeed)i).getDurationInMillis())
            .forEach(i -> ((PowerUpSpeed)i).disable(player));

            List<Consumable> leftPowerUps = new ArrayList<>();
            leftPowerUps.addAll(this.powerUpsInUse.stream()
                                .filter(i -> i instanceof PowerUpDamage 
                                    && timeInMillisPassed(((PowerUpDamage)i).getUsedTime()) <= ((PowerUpDamage)i).getDurationInMillis())
                                .collect(Collectors.toList()));
            leftPowerUps.addAll(this.powerUpsInUse.stream()
                                .filter(i -> i instanceof PowerUpSpeed 
                                    && timeInMillisPassed(((PowerUpSpeed)i).getUsedTime()) <= ((PowerUpSpeed)i).getDurationInMillis())
                                .collect(Collectors.toList()));
            this.powerUpsInUse = leftPowerUps;
            
        }
        
    }

    private long timeInMillisPassed(long pastTime){
        return System.currentTimeMillis() - pastTime;
    }
    
}

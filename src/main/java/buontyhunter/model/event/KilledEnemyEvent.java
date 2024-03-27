package buontyhunter.model.event;

import buontyhunter.model.WorldEvent;
import buontyhunter.model.AI.enemySpawner.EnemyType;

public class KilledEnemyEvent implements WorldEvent{
    private EnemyType killedType;

    /**
     * create a new KilledEnemyEvent , it is generated when an enemy is killed
     * @param killedType the type of the killed enemy
     */
    public KilledEnemyEvent(EnemyType killedType) {
        this.killedType = killedType;
    }

    /**
     * get the type of the killed enemy
     * @return the type of the killed enemy
     */
    public EnemyType getKilledType() {
        return killedType;
    }

}

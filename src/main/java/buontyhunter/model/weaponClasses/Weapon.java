package buontyhunter.model.weaponClasses;

import buontyhunter.common.Point2d;
import buontyhunter.common.Direction;
import buontyhunter.model.FighterEntity;
import buontyhunter.model.PlayerEntity;
import buontyhunter.model.RectBoundingBox;

public abstract class Weapon {
    protected int damage;
    protected double attackSpeed; // the higher the attack speed, the slower the attack
    protected int range;
    protected double weaponSpeed;
    protected Direction attackDirection;
    protected RectBoundingBox hitbox;
    protected FighterEntity owner;
    private WeaponType type;


    public Weapon(int damage, double attackSpeed, int range, double speed,  FighterEntity owner, WeaponType weaponType) {
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.range = range;
        this.weaponSpeed = speed;
        this.owner = owner;
        this.attackDirection = owner.getDirection();
        type = weaponType;

        //directAttack();
    }

    public void directAttack() {
        Point2d offet;
        switch (attackDirection) {
            case STAND_UP: {
                offet = new Point2d(owner.getPos().x, owner.getPos().y - range);
                hitbox = new RectBoundingBox(offet, -range, ((RectBoundingBox) owner.getBBox()).getWidth());
                break;
            }
            case STAND_DOWN: {
                offet = new Point2d(owner.getPos().x, owner.getPos().y + range);
                hitbox = new RectBoundingBox(owner.getPos(), range, ((RectBoundingBox) owner.getBBox()).getWidth());
                break;
            }
            case STAND_LEFT: {
                offet = new Point2d(owner.getPos().x - range, owner.getPos().y);
                hitbox = new RectBoundingBox(offet, - ((RectBoundingBox) owner.getBBox()).getWidth(), range);
                break;
            }
            case STAND_RIGHT: {
                offet = new Point2d(owner.getPos().x + range, owner.getPos().y);
                hitbox = new RectBoundingBox(owner.getPos(), ((RectBoundingBox) owner.getBBox()).getWidth(), range);
                break;
            }
            default: {
                break;
            }
        }
    }

    /**
     * get the damage of the weapon
     * @return the damage of the weapon
     */
    public int getDamage() {
        if(owner instanceof PlayerEntity){
            return (int)(damage * ((PlayerEntity)owner).getDamageMultiplier());
        }
        return damage;
    }

    /**
     * get the attack speed of the weapon
     * @return the attack speed of the weapon
     */
    public double getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * get the range of the weapon
     * @return the range of the weapon
     */
    public int getRange() {
        return range;
    }

    /**
     * get the speed of the weapon
     * @return the speed of the weapon
     */
    public double getWeaponSpeed() {
        return weaponSpeed;
    }

    /**
     * get the hitbox of the weapon
     * @return the hitbox of the weapon
     */
    public RectBoundingBox getHitbox() {
        return hitbox;
    }


    /**
     * get the type of the weapon
     * @return the type of the weapon
     */
    public WeaponType getWeaponType(){
        return type;
    }

    
    /**
     * set the damage of the weapon
     * @param damage the damage to set
     */
    public void setDamage(int damage) {
        if (damage <= 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        this.damage = damage;
    }

    /**
     * set the attack speed of the weapon
     * @param attackSpeed the attack speed to set
     */
    public void setAttackSpeed(int attackSpeed) {
        if (attackSpeed <= 0) {
            throw new IllegalArgumentException("Attack Speed cannot be negative");
        }
        this.attackSpeed = attackSpeed;
    }

    /**
     * set the range of the weapon
     * @param range the range to set
     */
    public void setRange(int range) {
        if (range <= 0) {
            throw new IllegalArgumentException("Range cannot be negative");
        }
        this.range = range;
    }

    /**
     * set the speed of the weapon
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        if (speed <= 0) {
            throw new IllegalArgumentException("Speed cannot be negative");
        }
        this.weaponSpeed = speed;
    }

    /**
     * set the hitbox of the weapon
     * @param hitbox the hitbox to set
     */
    public void setHitbox(RectBoundingBox hitbox) {
        if (hitbox == null) {
            throw new IllegalArgumentException("Hitbox cannot be null");
        }
        this.hitbox = hitbox;
    }

    /**
     * set the type of the weapon
     * @param wt the type to set
     */
    public void setWeaponType(WeaponType wt){
        type = wt;
    }

}
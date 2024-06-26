package buontyhunter.model.weaponClasses;
import buontyhunter.common.Point2d;
import buontyhunter.model.FighterEntity;
import buontyhunter.model.RectBoundingBox;

public class MeleeWeapon  extends Weapon{

    private final int maxDurability;
    private int durability;

    public MeleeWeapon(int damage, int attackSpeed, int range, double speed, FighterEntity owner, int durability, WeaponType weaponType) {
        super(damage, attackSpeed, range, speed,owner, weaponType);
        this.maxDurability=durability;
        this.durability = this.maxDurability;
    }

    /**
     * get the max durability of the weapon
     * @return the max durability of the weapon
     */
    public int getMaxDurability(){
        return maxDurability;
    }

    /**
     * set the durability of the weapon
     * @param a the durability to set
     */
    public void setDurability(int a){
        durability=a;
    }

    /**
     * get the durability of the weapon
     * @return the durability of the weapon
     */
    public int getDurability(){
        return this.durability;
    }

    /**
     * attack in the direction of the owner
     */
    @Override
    public void directAttack(){

        if(durability>0){
            attackDirection=owner.getDirection();

            RectBoundingBox a = ((RectBoundingBox)owner.getBBox());

            
            int offset=1;
            switch (attackDirection) {
                case STAND_LEFT: {
                    Point2d pos = new Point2d(owner.getPos().x-range, owner.getPos().y);
                    hitbox = new RectBoundingBox(pos, a.getWidth(), range);
                    break;
                }
                case STAND_RIGHT: {
                    Point2d pos = new Point2d(owner.getPos().x+offset, owner.getPos().y);
                    hitbox = new RectBoundingBox(pos, a.getWidth(), range);
                    break;
                }
                case STAND_UP: {
                    Point2d pos = new Point2d(owner.getPos().x , owner.getPos().y-range);
                    hitbox = new RectBoundingBox(pos, range, a.getWidth());
                    break;
                }
                case STAND_DOWN: {
                    Point2d pos = new Point2d(owner.getPos().x, owner.getPos().y+offset);
                    hitbox = new RectBoundingBox(pos, range, a.getWidth());
                    break;
                }
                default: {
                    break;
                }
            }
            owner.getDamagingArea().setBBox(hitbox);
            this.setDurability(this.getDurability()-1);
        } 
        else{
           owner.getDamagingArea().setShow(false);
            
        }
            
    }
        
}
    
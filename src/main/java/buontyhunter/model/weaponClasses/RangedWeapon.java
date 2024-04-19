package buontyhunter.model.weaponClasses;


import buontyhunter.common.Direction;
import buontyhunter.common.ImageType;
import buontyhunter.model.FighterEntity;
import buontyhunter.model.RectBoundingBox;
import buontyhunter.common.Point2d;

public class RangedWeapon extends Weapon {

    private Bullet bullet;
    private int ammo;
    // private List<Bullet> bullets = new ArrayList<Bullet>();

    public RangedWeapon(int damage, double attackSpeed, int range, double speed, ImageType sprite, FighterEntity owner, WeaponType weapontype) {
        super(damage, attackSpeed, range, speed, owner, weapontype);
        ammo=50;
    }

    /**
     * update the state of the bulle if it exists
     */
    public void getShot() {
        if (!owner.getDamagingArea().isShow()) {
            return;
        }
        if (bullet != null) {
            bullet.update();
        } else {
            owner.getDamagingArea().setShow(false);
        }
    }

    /*
     * public List<Bullet> getBullets() {
     * return bullets;
     * }
     */

    
     /**
      * set the ammo of the weapon
      * @param ammo the ammo to set
      */
    public void setAmmo(int ammo){
        this.ammo=ammo;
    }

    /**
     * subtract ammo from the weapon
     * @param ammo the ammo to subtract
     */
    public void subtractAmmo(int ammo){
        this.ammo-=ammo;
    }

    /**
     * add ammo to the weapon
     * @param ammo the ammo to add
     */
    public void addAmmo(int ammo){
        this.ammo+=ammo;
    }

    /**
     * get the ammo of the weapon
     * @return the ammo of the weapon
     */
    public int howManyAmmo(){
        return ammo;
    }

    /**
     * get the bullet of the weapon
     * @return the bullet of the weapon
     */
    public Bullet getBullet() {
        return bullet;
    }

    /**
     * create a bullet and send it in the direction of the owner
     */
    @Override
    public void directAttack() {
        if(ammo>0){
            bullet = new Bullet(owner.getDirection());
        }
        

    }

    public class Bullet {

        private double travelDistance;
        private Point2d pos;
        private Direction attackDirection;
        private int zigzag = 1;
        private int curving = 0;

        public Bullet( Direction direction) {
            travelDistance = 0;
            this.attackDirection = direction;
            pos = owner.getPos();
            hitbox = new RectBoundingBox(pos, 1, 1);

            if(owner.getWeapon().getWeaponType()==WeaponType.SHURIKENS){
                curving=1;
            }
            
        }

        //getters
        public Direction getDirection(){
            return attackDirection;
        }

        public int getZigZag(){
            return zigzag;
        }

        /**
         * update the state of the bullet
         */
        public void update() {

            if (travelDistance > range) {
                bullet = null;

            } else
            {

                if(travelDistance==0){
                    ((RangedWeapon)owner.getWeapon()).subtractAmmo(1);
                }

                switch (attackDirection) {
                    case STAND_UP: {
                        pos = new Point2d(pos.x + curving, pos.y - weaponSpeed);
                        hitbox = ((RectBoundingBox) hitbox).withPoint(pos);
                        break;
                    }
                    case STAND_DOWN: {
                        pos = new Point2d(pos.x + curving, pos.y + weaponSpeed);
                        hitbox = ((RectBoundingBox) hitbox).withPoint(pos);
                        break;
                    }
                    case STAND_LEFT: {
                        pos = new Point2d(pos.x - weaponSpeed, pos.y + curving);
                        hitbox = ((RectBoundingBox) hitbox).withPoint(pos);
                        break;
                    }
                    case STAND_RIGHT: {
                        pos = new Point2d(pos.x + weaponSpeed, pos.y + curving);
                        hitbox = ((RectBoundingBox) hitbox).withPoint(pos);
                        break;
                    }
                    default: {
                        break;
                    }
                }
                
                if(owner.getWeapon().getWeaponType()==WeaponType.SHURIKENS){
                    zigzag++;
                    
                    if(zigzag == 2){
                        zigzag = 0;
                        curving = curving * (-1);
                    }
                    
                }

                owner.getDamagingArea().setBBox(hitbox);
            }
            travelDistance += weaponSpeed;

        }

    }
}

package buontyhunter.model.armourClasses;

public class Armour {
    private int level;
    private int damageReduction;

    public Armour(int lvl, int dmgRed){
        level=lvl;
        damageReduction=dmgRed;
    }
    
    //Getters
    public int getLevel(){
        return level;
    }

    public int getdmgReduction(){
        return damageReduction;
    }

    //Setters
    public void setLevel(int set){
        level = set;
    }

    public void setdmgReduction(int set){
        damageReduction = set;
    }

    /**
     * Levels up the armour by 2 the first three times and by 1 until lvl 20
     * at lvl 20 the upgrading automatically fails
     * @return if the upgrade succeded
     */
    public boolean levelUp(){
        
        if(level<20)
        {
            if(level<3){
                damageReduction+=2;
            }
            else{
                damageReduction+=1;
            }

            level++;
            return true;
        }
        
        return false;
    }


}

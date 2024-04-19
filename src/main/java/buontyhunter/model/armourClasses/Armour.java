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

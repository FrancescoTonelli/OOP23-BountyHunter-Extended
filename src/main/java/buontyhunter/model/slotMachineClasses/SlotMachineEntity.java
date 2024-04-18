package buontyhunter.model.slotMachineClasses;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.HidableObject;
import buontyhunter.model.InterractableArea;
import buontyhunter.model.PlayerEntity;


public class SlotMachineEntity extends InterractableArea implements SlotMachine{
    
    private PlayerEntity player;

    public SlotMachineEntity(GameObjectType t, Point2d point, Vector2d vec, BoundingBox box, 
    HidableObject interf,PlayerEntity player){
        
        super(t, point, vec, box,interf);
        
        this.player=player;
    }


    public void roll(){

        ((SlotMachineBoard)this.getPanel()).roll();
    }

    
    public boolean play(){

        return ((SlotMachineBoard)this.getPanel()).play(player);
    }


    public WinCategories win(){

        return ((SlotMachineBoard)this.getPanel()).win();
    }

    
    public SlotMachineTilesTypes[][] getDisplayedTypes(){

        return ((SlotMachineBoard)this.getPanel()).getyDisplayedTipes();
    }
}

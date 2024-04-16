package buontyhunter.model.slotMachineClasses;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.HidableObject;
import buontyhunter.model.InterractableArea;
import buontyhunter.model.PlayerEntity;

import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;
import java.util.stream.*;

public class SlotMachineEntity extends InterractableArea implements SlotMachine{
    
    private PlayerEntity player;

    public SlotMachineEntity(GameObjectType t, Point2d point, Vector2d vec, BoundingBox box, 
    HidableObject interf,PlayerEntity player){
        
        super(t, point, vec, box,interf);

        this.player=player;
    }

	@Override
	public boolean roll() {
		if(((PlayerEntity)player).getDoblons()<5){
            return false;
        }
        else{
            ((PlayerEntity)player).withdrawDoblons(5);
        }
        return true;
        
	}

    private WinCategories winUpgrade(WinCategories winnin){

        switch (winnin) {
            case Lose:
                winnin = WinCategories.HalfRefund;
                break;

            case HalfRefund:
                winnin = WinCategories.Refund;
                break;
            
            case Refund:
                winnin = WinCategories.Double;
                break;
            
            case Double:
                winnin = WinCategories.Quintuple;
                break;
            
            case Quintuple:
                winnin = WinCategories.Jackpot;
                break;
            
            default:
                break;
        }

        return winnin;
    }

	@Override
	public WinCategories win() {
		
        Random r = new Random();
        WinCategories win = WinCategories.Lose;
        
        SlotMachineTilesTypes[][] arr = new SlotMachineTilesTypes[3][3];
        SlotMachineTilesTypes[] coll = {SlotMachineTilesTypes.zombie, SlotMachineTilesTypes.skelly, SlotMachineTilesTypes.knight, SlotMachineTilesTypes.wizard, SlotMachineTilesTypes.doblon, SlotMachineTilesTypes.hammer};

        for(int i=0; i < arr.length;i++){
            for(int j=0; j < arr[i].length;j++){
                arr[i][j]= coll[r.nextInt(6)];
            }
        }

        SlotMachineTilesTypes combo = arr[0][0];
        int streak = 0;
        for(int i=0; i < arr.length;i++){

            
            for(int j=0; j < arr[i].length;j++){
                
                if(j==0){
                    combo = arr[i][j];
                }
                else if(arr[i][j]==combo){
                    streak++;
                }

            }

            if(streak == arr[i].length){

                switch (combo) {
                    case zombie:
                        
                        winUpgrade(win);

                        break;
                
                    default:
                        break;
                }
            }

        }

        return win;

	}

}

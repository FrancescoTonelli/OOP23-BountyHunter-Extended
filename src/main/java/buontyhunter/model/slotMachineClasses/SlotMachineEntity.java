package buontyhunter.model.slotMachineClasses;

import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.HidableObject;
import buontyhunter.model.InterractableArea;
import buontyhunter.model.PlayerEntity;

import java.util.Random;

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
		
        SlotMachineTilesTypes[][] arr = new SlotMachineTilesTypes[3][3];
        SlotMachineTilesTypes[] coll = {SlotMachineTilesTypes.zombie, SlotMachineTilesTypes.skelly, SlotMachineTilesTypes.knight, SlotMachineTilesTypes.wizard, SlotMachineTilesTypes.doblon, SlotMachineTilesTypes.hammer};
        SlotMachineTilesTypes combo = arr[0][0];
        
        WinCategories win = WinCategories.Lose;
        Random r = new Random();
        int streak = 0;
        int hammerCount = 0;

        for(int i=0; i < arr.length;i++){
            for(int j=0; j < arr[i].length;j++){
                arr[i][j]= coll[r.nextInt(6)];
            }
        }

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
                    case skelly:

                        winUpgrade(win);
                        break;
                    case knight:

                        winUpgrade(win);
                        break;
                    case wizard:

                        winUpgrade(win);
                        winUpgrade(win);

                        break;
                    case doblon:

                        winUpgrade(win);
                        winUpgrade(win);

                        break;
                    case hammer:

                        hammerCount++;
                        break;
                    default:
                        break;
                }
            }

        }

        if(win==WinCategories.Lose || win==WinCategories.HalfRefund){
            switch (hammerCount) {
                case 1:
                    win=WinCategories.HalfRefund;
                    break;
                case 2:
                    win=WinCategories.Refund;
                    break;
                case 3:
                    win=WinCategories.Double;
                    break;
                default:
                    break;
            }
        }

        return win;

	}

}

package buontyhunter.model.slotMachineClasses;

import java.util.Random;

import buontyhunter.common.ImageType;
import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.GraphicsComponent;
import buontyhunter.input.InputComponent;
import buontyhunter.model.BoundingBox;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.HidableObject;
import buontyhunter.physics.PhysicsComponent;
import buontyhunter.model.PlayerEntity;

public class SlotMachineBoard extends HidableObject{

    // slot board size
    private int x = 3;
    private int y = 3;

    private int winCountdown =200;
    private boolean isResultDisplaying=false;
    private boolean isRolled=false;
    private WinCategories win = WinCategories.Lose;

    private SlotMachineTilesTypes[][] arr = new SlotMachineTilesTypes[x][y];
    private SlotMachineTilesTypes[] coll = {SlotMachineTilesTypes.zombie, SlotMachineTilesTypes.skelly, SlotMachineTilesTypes.knight, SlotMachineTilesTypes.wizard, SlotMachineTilesTypes.doblon, SlotMachineTilesTypes.hammer};
    

    public SlotMachineBoard(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, InputComponent input,
            GraphicsComponent graph, PhysicsComponent phys, boolean show) {
        
            super(type, pos, vel, box, input, graph, phys, show);

    }
    
    public boolean isResultDisplaying(){
        return isResultDisplaying;
    }

    public void winCountdown(){
        winCountdown--;
    }

    public void winCountdownReset(){
        winCountdown=200;
    }

    public boolean winCountdownOver(){
        if(winCountdown<0){
            return false;
        }
        return true;
    }

    public WinCategories currentWinCategories(){
        return win;
    }

    public void resetWinCategory(){
        win= WinCategories.Lose;
    }

    public SlotMachineTilesTypes[][] getyDisplayedTipes(){

        return arr;
    }

    public ImageType[][] getTileImages(){

        ImageType[][] imgList;

        if(isRolled){

            imgList= new ImageType[x][y];
    
            for(int i=0; i < arr.length;i++){
                for(int j=0; j < arr[i].length;j++){
                    
                    switch (arr[i][j]) {
                        case zombie:
                            
                            imgList[i][j]=ImageType.zombieFront;
    
                            break;
                        case skelly:
    
                            imgList[i][j]=ImageType.skellyFront1;
    
                            break;
                        case knight:
    
                            imgList[i][j]=ImageType.knightFront1;
                            
                            break;
                        case wizard:
    
                            imgList[i][j]=ImageType.wizardFront1;
    
                            break;
                        case doblon:
    
                            imgList[i][j]=ImageType.doblon;
    
                            break;
                        case hammer:
    
                            imgList[i][j]=ImageType.hammer;
    
                            break;
                        default:
                            break;
                    }
    
                }
            }
    
        }
        else{
            this.roll();
            imgList= new ImageType[x][y];
        }

        return imgList;

    }

    /**
     * This method is constantly called while not playing and it makes the various
     * tiles change at random
     * @return
     */
    public void roll(){

        Random r = new Random();
        
        for(int i=0; i < arr.length;i++){
            for(int j=0; j < arr[i].length;j++){
                arr[i][j]= coll[r.nextInt(6)];
            }
        }
        isRolled=true;
    }


    /**
     * Every time the play button is clicked this method checks if the player can afford it
     * 
     * @return true if the player can afford it, false otherwise
     */
    public boolean play(PlayerEntity player) {
		if(((PlayerEntity)player).getDoblons()<5){
            return false;
        }
        else{
            ((PlayerEntity)player).withdrawDoblons(5);
        }
        isResultDisplaying=true;
        return true;
        
	}
    

    /**
     * Generates a random combination of tiles and then checks if 
     * any row has a winning combination and rewards the player accordingly
     * 
     * @return the tipe of win as a WinCategories enum
     */
    public WinCategories win() {
		
        SlotMachineTilesTypes combo = arr[0][0];
        
        
        int streak = 0;
        int hammerCount = 0;


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


    
}

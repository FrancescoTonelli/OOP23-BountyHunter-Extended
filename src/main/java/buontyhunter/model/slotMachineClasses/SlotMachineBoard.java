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

    private int winCountdown = 120;
    private boolean buttonPressed = false;
    private boolean isResultDisplaying = false;
    private boolean isRolled = false;
    private boolean jackpotWon = false;

    

    private WinCategories win = WinCategories.Lose;

    private SlotMachineTilesTypes[][] arr = new SlotMachineTilesTypes[x][y];
    private SlotMachineTilesTypes[] coll = {SlotMachineTilesTypes.zombie, SlotMachineTilesTypes.skelly, SlotMachineTilesTypes.knight, SlotMachineTilesTypes.wizard, SlotMachineTilesTypes.doblon, SlotMachineTilesTypes.hammer};
    

    public SlotMachineBoard(GameObjectType type, Point2d pos, Vector2d vel, BoundingBox box, InputComponent input,
            GraphicsComponent graph, PhysicsComponent phys, boolean show) {
        
            super(type, pos, vel, box, input, graph, phys, show);

    }
    
    //Setters

    public void buttonBeeingPressed(){
        buttonPressed = true;
    }
    
    public void winCountdown(){
        winCountdown--;
    }
    
    //Resetters
    
    public void resultNoMoreDisplaying(){
        winCountdownReset();
        resetWinCategory();
        jackpotDeactivate();
        isResultDisplaying=false;
    }
    
    public void buttonReleased(){
        buttonPressed = false;
    }
    
    public void jackpotDeactivate(){
        jackpotWon=false;
    }
    
    public void winCountdownReset(){
        winCountdown=120;
    }
    
    public void resetWinCategory(){
        win= WinCategories.Lose;
    }
    
    //Getters 
    
    public boolean isResultDisplaying(){
        return isResultDisplaying;
    }
    
    public boolean isButtonBeeingPressed(){
        return buttonPressed;
    }
    
    public boolean isjackpotWon(){
        return jackpotWon;
    }
    
    public WinCategories currentWinCategories(){
        return win;
    }
    
    public boolean winCountdownOver(){
        if(winCountdown>0){
            return false;
        }
        return true;
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
    
                            imgList[i][j]=ImageType.skellyFront;
    
                            break;
                        case knight:
    
                            imgList[i][j]=ImageType.knightFront;
                            
                            break;
                        case wizard:
    
                            imgList[i][j]=ImageType.wizardFront;
    
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

        if(!isButtonBeeingPressed()){
            Random r = new Random();
            
            for(int i=0; i < arr.length;i++){
                for(int j=0; j < arr[i].length;j++){
                    arr[i][j]= coll[r.nextInt(6)];
    
                    //Always win cheats
    
                    //arr[i][j]=SlotMachineTilesTypes.wizard;
                }
            }
            isRolled=true;

        }

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
            if(!isResultDisplaying()){
                ((PlayerEntity)player).withdrawDoblons(5);
                WinCategories winning = this.win();
                switch (winning) {
                    case Lose:
                    
                        break;

                    case DoubleRefund:
                        ((PlayerEntity)player).depositDoblons(10);

                        break;
                    
                    case QuintupleRefund:
                        ((PlayerEntity)player).depositDoblons(25);

                        break;
                    
                    case SmallWin:
                        ((PlayerEntity)player).depositDoblons(50);

                        break;
                    
                    case BigWin:
                        ((PlayerEntity)player).depositDoblons(250);

                        break;
                    
                    case Jackpot:
                        ((PlayerEntity)player).depositDoblons(1000);
                        jackpotWon = true;

                        break;

                    default:

                        break;
                }

                isResultDisplaying=true;
            }

        }
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
        
        
        for(int i=0; i < arr.length;i++){
            int streak = 0;
            
            for(int j=0; j < arr[i].length;j++){

                if(j==0){
                    combo = arr[i][j];

                }
                
                if(arr[i][j]==combo){
                    streak++;

                }

            }


            if(streak == arr[i].length){

                switch (combo) {
                    case zombie:
                        if(win==WinCategories.Lose)
                            win = WinCategories.DoubleRefund;

                        break;
                    case skelly:
                        if(win==WinCategories.Lose||win==WinCategories.DoubleRefund)
                            win = WinCategories.QuintupleRefund;

                        break;
                    case hammer:
                        if(win==WinCategories.Lose||win==WinCategories.DoubleRefund)
                            win = WinCategories.QuintupleRefund;

                        break;
                    case knight:
                        if(win==WinCategories.Lose||win==WinCategories.DoubleRefund||win==WinCategories.QuintupleRefund)
                            win = WinCategories.SmallWin;
                        
                        break;
                    case doblon:
                        if(!(win==WinCategories.Jackpot||win==WinCategories.BigWin))
                            win = WinCategories.BigWin;
                        break;
                    case wizard:
                        if(!(win==WinCategories.Jackpot))
                            win = WinCategories.Jackpot;
                        break;
                    default:
                        break;
                }
            }

        }

        return win;

	}

}

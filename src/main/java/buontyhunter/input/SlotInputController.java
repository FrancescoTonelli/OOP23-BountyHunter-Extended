package buontyhunter.input;

import buontyhunter.model.GameObject;
import buontyhunter.model.World;
import buontyhunter.model.slotMachineClasses.SlotMachineBoard;

public class SlotInputController implements InputComponent {

    @Override
    public void update(GameObject board, InputController c, World w) {

        if(board instanceof SlotMachineBoard){
            SlotMachineBoard a = (SlotMachineBoard)board;

            if(a.isResultDisplaying()){
    
                if(a.winCountdownOver() ){
                    a.winCountdownReset();
                    a.resetWinCategory();
                }
                else{
                    a.winCountdown();
                }
    
            }
            else{
                a.roll();
            }
        }


    }
    
}

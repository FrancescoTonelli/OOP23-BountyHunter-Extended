package buontyhunter.input;

import buontyhunter.model.GameObject;
import buontyhunter.model.World;
import buontyhunter.model.slotMachineClasses.SlotMachineBoard;

public class SlotInputController implements InputComponent {

    private int chill=5;

    @Override
    public void update(GameObject board, InputController c, World w) {

        if(board instanceof SlotMachineBoard){
            SlotMachineBoard a = (SlotMachineBoard)board;

            if(a.isResultDisplaying()){
    
                if(a.winCountdownOver() ){
                    a.resultNoMoreDisplaying();
                }
                else{
                    a.winCountdown();
                }
    
            }
            else{
                if(chill<0){
                    a.roll();
                    chill=5;
                }
                else{
                    chill--;
                }
            }
        }


    }
    
}

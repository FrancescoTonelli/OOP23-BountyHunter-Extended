package buontyhunter.model.slotMachineClasses;

import buontyhunter.model.HidableObject;

public interface SlotMachine {
    

    /**
     * This method is constantly called while not playing and it makes the various
     * tiles change at random
     * @return
     */
    void roll();

    /**
     * Every time the play button is clicked this method checks if the player can afford it
     * 
     * @return true if the player can afford it, false otherwise
     */
    boolean play();


    /**
     * Generates a random combination of tiles and then checks if 
     * any row has a winning combination and rewards the player accordingly
     * 
     * @return the tipe of win as a WinCategories enum
     */
    WinCategories win();

    /**
     * Returns the current combination of the slot's board as an enum 2DList
     * 
     * @return the current displayed elements on the slot's board
     */
    public SlotMachineTilesTypes[][] getDisplayedTypes();

    /**
     * Returns the Panel of the object
     * 
     * @return the interface as an HidableObject
     */
    HidableObject getPanel();
}

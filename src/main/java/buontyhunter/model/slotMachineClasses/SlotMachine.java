package buontyhunter.model.slotMachineClasses;

import buontyhunter.model.HidableObject;

public interface SlotMachine {
    

    /**
     * Every time the roll button is clicked this method checks if the player can afford it
     * 
     * @return true if the player can afford it, false otherwise
     */
    boolean roll();


    /**
     * Generates a random combination of tiles and then checks if 
     * any row has a winning combination and rewards the player accordingly
     * 
     * @return the tipe of win as a WinCategories enum
     */
    WinCategories win();

    /**
     * Returns the Panel of the object
     * 
     * @return the interface as an HidableObject
     */
    HidableObject getPanel();
}

package buontyhunter.graphics;

import buontyhunter.model.*;
import buontyhunter.model.consumables.Consumable;
import buontyhunter.model.merchantClasses.MerchantEntity;
import buontyhunter.model.merchantClasses.MerchantMenu;
import buontyhunter.model.slotMachineClasses.SlotMachineBoard;
import buontyhunter.model.slotMachineClasses.SlotMachineEntity;
import buontyhunter.model.weaponClasses.RangedWeapon;
import buontyhunter.model.weaponClasses.Weapon;

public interface Graphics {
	/**
	 * this method is used to draw the player on the screen
	 * 
	 * @param obj the object to draw (player)
	 * @param w   the world
	 */
	void drawPlayer(GameObject obj, World w);

	/**
	 * this method is used to draw the map on the screen
	 * 
	 * @param tileManager the tile manager
	 * @param w           the world
	 */
	void drawMap(TileManager tileManager, World w);

	/**
	 * this method is used to draw the mini map on the screen
	 * 
	 * @param tileManager the tile manager
	 * @param w           the world
	 */
	void drawMiniMap(HidableObject tileManager, World w);

	/**
	 * this method is used to draw the navigator line on the screen
	 * 
	 * @param navigatorLine the navigator line
	 * @param w             the world
	 */
	void drawNavigatorLine(NavigatorLine navigatorLine, World w);

	/**
	 * this method is used to draw the health bar on the screen
	 * 
	 * @param healthBar the health bar
	 * @param w         the world
	 */
	void drawHealthBar(HealthBar healthBar, World w);

	/**
	 * this method is used to draw the teleporter on the screen
	 * 
	 * @param tp the teleporter to draw
	 * @param w  the world
	 */
	void drawTeleporter(Teleporter tp, World w);

	/**
	 * this method is used to draw the quest pannel on the screen
	 * 
	 * @param questPannel the quest pannel
	 * @param w           the world
	 */
	void drawQuestPannel(QuestPannel questPannel, World w);

	/**
	 * this method is used to draw the string under the player on the screen
	 * 
	 * @param s the string to draw
	 */
	void drawStringUnderPlayer(String s);

	/**
	 * this method is used to draw the quest journal on the screen
	 * 
	 * @param w the world
	 */
	void drawQuestJournal(World w);

	/**
	 * this method is used to draw the weapon on the screen
	 * 
	 * @param fe the fighter entity
	 */
	void drawWeapon(FighterEntity fe);

	/**
	 * this method is used to draw the enemy on the screen
	 * 
	 * @param obj the object to draw
	 * @param w   the world
	 */
	void drawEnemy(GameObject obj, World w);

	/**
	 * this method is used to draw progress bar on the screen
	 * 
	 * @param loadingBar the loading bar
	 * @param w          the ranged weapon
	 */
	void drawProgressBar(LoadingBar loadingBar, World w);

	/**
	 * this method is used to draw the bullet shoot from the Ranged Weapon on the
	 * screen
	 * 
	 * @param w the ranged weapon
	 */
	void drawBullet(RangedWeapon w);

	/**
	 * this method is used to draw the blacksmith panel on the screen
	 * 
	 * @param blacksmithPanel the blacksmith panel
	 * @param w               the world
	 */
	void drawBlacksmithPanel(BlacksmithPanel blacksmithPanel, World w);

	/**
	 * this method is used to draw Wizard boss on the screen
	 * 
	 * @param boss the WizardBossEntity panel
	 * @param w    the world
	 */
	void drawWizardBoss(WizardBossEntity boss, World w);

	/**
	 * this method is used to draw the inventory on the screen
	 * 
	 * @param inventory the inventory
	 * @param w         the world
	 */
	void drawInventory(InventoryObject inventory, World w);

	/**
	 * this method is used to draw the weapon icon on the screen
	 * 
	 * @param weapon    the weapon
	 * @param x         the x coordinate
	 * @param y         the y coordinate
	 * @param dimension the dimension
	 */
	void drawWeaponIcon(Weapon weapon, int x, int y, int dimension);

	/**
	 * this method is used to draw the durability bar on the screen
	 * 
	 * @param weapon the weapon
	 * @param x      the x coordinate
	 * @param y      the y coordinate
	 */
	void drawDurabilityBar(Weapon weapon, int x, int y);

	/**
	 * this method calls ConsumableGraphics to draw consumables
	 * 
	 * @param consumable the consumable to draw
	 */
	void drawConsumable(Consumable consumable);

	/**
	 * this method draw the pong game icon in the hub
	 * 
	 * @param pong the pong entity to draw
	 */
	void drawPongIcon(PongEntity pong, World w);

	/**
	 * this method draw the pong game panel
	 * 
	 * @param panel the panel to draw
	 */
	void drawPongPanel(PongPanel panel);

	/**
	 * this method draws the slotMachine icon in the hub
	 * 
	 * @param slot the SlotMachine entity to draw
	 */
	public void drawSlotMachine(SlotMachineEntity slot, World w);

	/**
	 * This method renders the slot machine's board
	 * 
	 * @param board the board to draw
	 */
	void drawSlotMachineBoard(SlotMachineBoard board, World w);

	/**
	 * this method draws the Merchant icon in the hub
	 * 
	 * @param merchant the MerchantEntity entity to draw
	 */
	void drawMerchant(MerchantEntity merchant, World w);

	/**
	 * This method draws the Merchant's menu
	 * 
	 * @param menu the menu to draw
	 */
	void drawMerchantMenu(MerchantMenu menu, World w);
}

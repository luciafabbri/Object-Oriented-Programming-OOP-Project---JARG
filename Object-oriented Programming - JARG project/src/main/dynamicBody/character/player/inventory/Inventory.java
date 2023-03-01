package main.dynamicBody.character.player.inventory;

/**
 * An interface used to store objects that the player can pick up by walking
 * into the environment
 */

public interface Inventory {

	/**
	 * @return player's coins stored in the inventory
	 */
	int getCoin();

	/**
	 * @return player's keys
	 */
	int getKey();

	/**
	 * Method used to add a new coin in the inventory
	 */
	void addCoin();

	/**
	 * Method used to add a new key
	 */
	void addKey();

}

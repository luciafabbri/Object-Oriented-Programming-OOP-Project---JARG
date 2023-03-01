package main.dynamicBody.character.player.inventory;

import main.dynamicBody.character.player.Player;

/**
 * Class that implements interface Inventory used to manage all the player's
 * objects picked up in each room
 */

public class InventoryImpl implements Inventory {

	private int coin;
	private int key;

	/**
	 * Default constructor
	 * 
	 * @param player, the player of which we will store the picked objects
	 */
	public InventoryImpl(Player player) {
		this.coin = 0;
		this.key = 0;
	}

	@Override
	public int getCoin() {
		return this.coin;
	}

	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public void addCoin() {
		this.coin += 1;
	}

	@Override
	public void addKey() {
		this.key += 1;
	}

}

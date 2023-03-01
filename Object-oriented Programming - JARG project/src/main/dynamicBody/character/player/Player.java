package main.dynamicBody.character.player;

import main.dynamicBody.character.player.inventory.Inventory;
import main.dynamicBody.character.player.movement.Movement;
import main.dynamicBody.character.player.movement.check.CheckPlayerMovement;
import main.dynamicBody.character.player.shoot.ShootingPlayer;
import main.levels.LevelComp;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * An interface that presents all the methods needed or that can be used with
 * regards to the player
 */

public interface Player extends main.dynamicBody.character.Character {

	/**
	 * Method used to set player's position
	 * 
	 * @param input, received from the keyboard
	 * @param level, player's current level
	 * @throws SlickException
	 */
	void setPosition(Input input, LevelComp level) throws SlickException;

	/**
	 * Method used to set the level in which the player should go
	 * 
	 * @param level, to set the new player's level
	 */
	void setLevel(int level);

	/**
	 * @return the player's current level
	 */
	int getLevel();

	/**
	 *
	 * Method used to get player's type of shooting bullet
	 * 
	 * @return player's bullet
	 */
	ShootingPlayer getShootingBullet();

	/**
	 * @return player's current room
	 */
	RoomModel getRoom();

	/**
	 * Methods used to set player's current room
	 * 
	 * @param room, the room where the player would be moved
	 */
	void setCurrentRoom(RoomModel room);

	/**
	 * Method used to update player's position
	 * 
	 * @param position, the coordinates of the player to be set
	 */
	void transitionPos(Pair<Integer, Integer> position);

	/**
	 * @return player's speed
	 */
	int getPlayerSpeed();

	/**
	 * Method used to get player's type of check
	 * 
	 * @return player's check
	 */
	CheckPlayerMovement getCheck();

	/**
	 * Method used to upgrade player's damage
	 * 
	 * @param upgrade, an int value to add to player's current damage
	 */
	void upgradeDamage(int upgrade);

	/**
	 * @return player's inventory
	 */
	Inventory getInventory();

	/**
	 * Method used to upgrade player's speed
	 * 
	 * @param upgrade, an int value to be added to player's speed in order to move
	 *                 faster in the dungeon
	 */
	void upgradePlayerSpeed(int upgrade);

	/**
	 * @return player's rate of fire
	 */
	int getRateOfFire();

	/**
	 * Method used to check the presence of any enemy in the room If the room is
	 * empty, the doors will open and the player will be able to change room
	 * 
	 * @param clearRoom, the boolean value to check
	 */
	void setClearRoom(boolean clearRoom);

	/**
	 * Method used to upgrade player's rate of fire
	 * 
	 * @param upgrade, an int value used to change player's current rate of fire in
	 *                 order to shoot faster
	 */
	void upgradeRateOfFire(int upgrade);

	/**
	 * Method used to upgrade player's max health
	 * 
	 * @param upgrade, an int value to be added to the player's max health
	 */
	void upgradeMaxHealth(int upgrade);

	/**
	 * @return player's current health
	 */
	int getCurrentHealth();

	/**
	 * @return player's max health
	 */
	int getMaxHealth();

	/**
	 * Method used to upgrade player's current health
	 * 
	 * @param heal, int value to be added to player's current health
	 */
	void heal(int heal);

	/**
	 * Method used to get player's movement (only used in class
	 * PlayerAndBulletCreationTest)
	 * 
	 * @return new current player's coordinates and his direction
	 */
	Movement getMove();

	/**
	 * Method used to reset player's stats
	 */
	void resetStats();

}

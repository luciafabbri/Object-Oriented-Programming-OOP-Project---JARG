package main.levels;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import main.dynamicBody.character.enemy.TypeEnemy;
import main.dynamicBody.move.Direction;
import main.worldModel.utilities.Pair;

public interface LevelComp {

	/**
	 * Method that creates a List of RoomImpl that contain the information of each
	 * room of the level
	 */
	void loadRooms();

	/**
	 * Method that returns the list created with loadRomms
	 * 
	 * @return List<RoomImpl>, the list of rooms that make up the level
	 */
	List<RoomImpl> getLevel();

	/**
	 * Method that returns the ID of the current loaded room
	 * 
	 * @return int, the ID of the room
	 */
	int getRoomID();

	/**
	 * Method that sets the current room loaded
	 * 
	 * @param roomID, the room ID that should be loaded
	 */
	void setRoomID(int roomID);

	/**
	 * Method that returns whether the Coin placed in the level has been obtained
	 * 
	 * @return true if Coin obtained, false otherwise
	 */
	public boolean isGotLevelCoin();

	/**
	 * Method that sets the boolean if the Coin has been obtained
	 * 
	 * @param gotLevelCoin, boolean
	 */
	public void setGotLevelCoin(boolean gotLevelCoin);

	/**
	 * Method that returns true if the game has been pause, otherwise false
	 * 
	 * @return true if menu paused, false otherwise
	 */
	public boolean isPauseMenu();

	/**
	 * Method that sets the boolean to check if the game has been paused
	 * 
	 * @param pauseMenu, the boolean
	 */
	public void setPauseMenu(boolean pauseMenu);

	/**
	 * Method that sets the boolean to check if the room has been changed
	 * 
	 * @param changedRoom, the boolean
	 */
	public void setChangedRoom(boolean changedRoom);

	/**
	 * Method that returns true if the room has been changed, otherwise false
	 * 
	 * @return true if room changes, false otherwise
	 */
	public boolean isChangedRoom();

	/**
	 * Method used to get a Map of all the enemies of a level, associated with their
	 * texture
	 * 
	 * @return the Map containing the textures
	 * @throws SlickException
	 * @see SlickException
	 */
	public Map<TypeEnemy, Set<Pair<Direction, Animation>>> checkAnimations() throws SlickException;

	/**
	 * Method that returns true if the game has been won, otherwise false
	 * 
	 * @return true if the player wins, false otherwise
	 */
	public boolean isGameWon();

	/**
	 * Method that sets whether the game has been won
	 * 
	 * @param gameWon, the boolean
	 */
	public void setGameWon(boolean gameWon);
}
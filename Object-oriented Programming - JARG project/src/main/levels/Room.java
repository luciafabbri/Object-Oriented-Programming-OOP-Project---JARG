package main.levels;

import java.util.Map;
import java.util.Optional;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

import main.worldModel.RoomModel;
import main.worldModel.utilities.enums.Door;

public interface Room {

	/**
	 * method that returns the tiles regarding the floor
	 * 
	 * @return Tile, the floor tiles
	 */
	Image getFloor();

	/**
	 * method that returns the tiles regarding the vertical walls
	 * 
	 * @return Image, the vertical walls tiles
	 */
	Image getWallVert();

	/**
	 * method that returns the tiles regarding the horizontal floor
	 * 
	 * @return Image, the horizontal wall tiles
	 */
	Image getWallHor();

	/**
	 * method that returns the tiles regarding the four corners
	 * 
	 * @return Image, the corners of the walls
	 */
	Image getCorners();

	/**
	 * method that returns the Map with the existing doors, based on which rooms are
	 * connected which
	 * 
	 * @return Map, with the value being other RoomDesigns that can exist, which is
	 *         associated with a Door
	 */
	Map<Door, Optional<RoomModel>> getDoorAccess();

	/**
	 * method that returns the RoomModel of the current room
	 * 
	 * @return RoomModel, containing the current room entities and objects
	 */
	RoomModel getRoom();

	/**
	 * method that returns the animated tiles with the western door
	 * 
	 * @return AnimatedTile
	 */
	Animation getDoorWest();

	/**
	 * method that returns the animated tiles with the northren door
	 * 
	 * @return Animation
	 */
	Animation getDoorNorth();

	/**
	 * method that returns the animated tiles with the eastern door
	 * 
	 * @return Animation
	 */
	Animation getDoorEast();

	/**
	 * method that returns the animated tiles with the southern door
	 * 
	 * @return Animation
	 */
	Animation getDoorSouth();

	/**
	 * Method that returns the tiles of the vertical top of the door
	 * 
	 * @return Image, the vertical top of the door
	 */
	Image getTopDoorVert();

	/**
	 * Method that returns the tiles of the horizontal top of the door
	 * 
	 * @return Image, the horizontal top of the door
	 */
	Image getTopDoorHor();

	/**
	 * Method that returns a boolean indicating if the key in the room has been
	 * taken
	 * 
	 * @return true if key has been taken, false otherwise
	 */
	public boolean isGotRoomKey();

	/**
	 * Method that sets the boolean regarding obtaining the key in the room
	 * 
	 * @param gotRoomKey, boolean
	 */
	public void setGotRoomKey(boolean gotRoomKey);
}
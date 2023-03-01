package main.dynamicBody.character.player.movement.check;

import java.util.Map;
import java.util.Optional;
import org.newdawn.slick.SlickException;

import main.dynamicBody.move.check.CheckPos;
import main.levels.LevelComp;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;
import main.worldModel.utilities.enums.Door;

/**
 * An interface used to check player's position in the current room According to
 * his position, he will be force to take specific actions
 */

public interface CheckPlayerMovement extends CheckPos {

	/**
	 * @param pos, coordinates of the player
	 * @param map, used to associate to each room its specific doors (based on the
	 *             position) In case the association room-door exists, the player is
	 *             allowed to pass by the specific doors
	 * @return true if the player is next to the door
	 */
	boolean checkDoors(Pair<Integer, Integer> pos, Map<Door, Optional<RoomModel>> map);

	/**
	 * Method used to check if the player is in collision with any entity (coin, key
	 * or modifiers) in the room In case he's above one of them, he will be able to
	 * recognize his type and he will do the specific action
	 * 
	 * @param room,  current room where the player is
	 * @param pos,   player's coordinates inside the room
	 * @param level, player's current level
	 * @return true if the player had a collision
	 * @throws SlickException
	 */
	boolean checkGameEntities(RoomModel room, Pair<Integer, Integer> pos, LevelComp level) throws SlickException;

	/**
	 * Method used to check if the player has found and he's above the stairs
	 * 
	 * @param room, current room where the player is
	 * @param pos,  player's coordinates inside the room
	 * @return true if the player is above the stairs
	 */
	boolean checkStairs(RoomModel room, Pair<Integer, Integer> pos);

	/**
	 * Method used to check in the player is in collision with any enemy in the room
	 * 
	 * @param room, current room where the player is
	 * @param pos,  player's coordinates inside the room
	 * @return true if the player had a collision
	 * @throws SlickException
	 */
	boolean checkEnemyRoom(RoomModel room, Pair<Integer, Integer> pos) throws SlickException;

}

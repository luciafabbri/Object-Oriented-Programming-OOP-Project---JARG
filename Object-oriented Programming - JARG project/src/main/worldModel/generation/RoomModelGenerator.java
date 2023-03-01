package main.worldModel.generation;

import org.newdawn.slick.SlickException;

import main.worldModel.RoomModel;

/**
 * An interface that models the rooms generators, the entities that
 * pseudo-randomly generate game rooms.
 *
 */
public interface RoomModelGenerator {

	/**
	 * @param index, integer used to identify the room inside a level
	 * @return a worldModel.RoomModel object, also known as the model of a room
	 * @throws SlickException
	 */
	RoomModel generateRoom(int index) throws SlickException;

}

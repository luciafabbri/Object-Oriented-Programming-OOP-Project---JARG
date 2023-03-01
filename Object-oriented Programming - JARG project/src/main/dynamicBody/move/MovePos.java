package main.dynamicBody.move;

import main.worldModel.utilities.Pair;

/**
 * An interface used to set next dynamic body's coordinates and direction in the
 * dungeon
 */

public interface MovePos {

	/**
	 * @param pos, dynamic body's current coordinates
	 * @param dir, dynamic body's current direction
	 * @return the possible next dynamic body's coordinates and direction
	 */
	Pair<Integer, Integer> nextPos(Pair<Integer, Integer> pos, Direction dir);

}

package main.dynamicBody.bullet.move;

import main.dynamicBody.move.Direction;
import main.dynamicBody.move.check.CheckPos;
import main.worldModel.utilities.Pair;

/**
 * An interface used to move bullet's position in the dungeon
 */

public interface MoveBull {

	/**
	 * @param pos,   bullet's current coordinates
	 * @param dir,   bullet's current direction
	 * @param check, type of bullet's check
	 * @return the possible next bullet's coordinates in the dungeon
	 */
	Pair<Integer, Integer> nextPos(Pair<Integer, Integer> pos, Direction dir, CheckPos check);

	/**
	 * @return true if the bullet is alive
	 */
	boolean isAlive();

}

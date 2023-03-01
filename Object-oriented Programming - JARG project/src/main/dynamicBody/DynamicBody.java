package main.dynamicBody;

/**
 * An interface used to represent all the methods that are in common with all
 * the dynamic bodies in the dungeon
 */

public interface DynamicBody {

	/**
	 * @return dynamic body dimension of type UpDownLeftRight, which means that for
	 *         each dynamic body has been checked its exacts pixel in order to be
	 *         displayed in the dungeon as real as possible
	 */
	UpDownLeftRight<Integer> getDimension();

	/**
	 * @return dynamic bodies's damage
	 */
	int getDamage();

	/**
	 * @return true if the dynamic body is alive
	 */
	boolean isAlive();

}

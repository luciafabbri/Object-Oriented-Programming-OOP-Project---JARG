package main.dynamicBody.bullet;

import main.dynamicBody.DynamicBody;
import main.dynamicBody.move.Direction;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

/**
 * An interface that defines all the main methods concerning bullets
 */

public interface Bullet extends DynamicBody {

	/**
	 * @return bullet's current position
	 */
	Pair<Integer, Integer> getPos();

	/**
	 * Method used to check if bullet's next coordinates are considered to be in a
	 * possible position
	 */
	void updatePos();

	/**
	 * @return bullet's current direction
	 */
	Direction getDirection();

	/**
	 * @return bullet's type direction
	 */
	TypeBullet getType();

	/**
	 * @return bullet's current room
	 */
	RoomModel getRoom();

}

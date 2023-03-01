package main.dynamicBody.bullet;

import main.dynamicBody.move.Direction;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

/**
 * Class that implements interface Bullet, used to create a bullet of type Enemy
 */

public class BulletEnemy extends BulletImpl implements Bullet {

	/**
	 * Default constructor
	 * 
	 * @param position,  bullet's position
	 * @param damage,    bullet's damage power
	 * @param direction, bullet's direction
	 * @param room,      bullet's current room
	 */
	public BulletEnemy(Pair<Integer, Integer> position, int damage, Direction direction, RoomModel room) {
		super(position, damage, direction, room, TypeBullet.ENEMY_BULL);
	}

}

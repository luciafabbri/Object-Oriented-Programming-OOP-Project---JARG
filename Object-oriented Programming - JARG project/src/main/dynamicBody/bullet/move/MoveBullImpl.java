package main.dynamicBody.bullet.move;

import main.dynamicBody.bullet.BulletDefault;
import main.dynamicBody.move.Direction;
import main.dynamicBody.move.check.CheckPos;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

/**
 * Class that implements interface MoveBull used to check if it's possible to
 * move bullet in his next position in the dungeon
 */

public class MoveBullImpl implements MoveBull {

	private boolean alive = true;
	private RoomModel currentRoom;

	/**
	 * Default constructor
	 * 
	 * @param room, bullet's current room
	 */
	public MoveBullImpl(RoomModel room) {
		currentRoom = room;
	}

	@Override
	public Pair<Integer, Integer> nextPos(Pair<Integer, Integer> pos, Direction dir, CheckPos check) {
		int speed = BulletDefault.SPEED.getValue();
		if (alive) {
			Pair<Integer, Integer> nextPos = new Pair<Integer, Integer>(pos.getX() + (dir.getAbscissa() * speed),
					pos.getY() + (dir.getOrdinate() * speed));
			alive = check.possiblePos(currentRoom, nextPos);
			return nextPos;
		}
		return pos;
	}

	@Override
	public boolean isAlive() {
		return alive;
	}

}

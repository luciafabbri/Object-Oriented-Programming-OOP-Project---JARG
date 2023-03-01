package main.dynamicBody.move.check;

import main.dynamicBody.DynamicBody;
import main.worldModel.RoomModel;
import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.Pair;

/**
 * Class that implements interface CheckPos used to check if a dynamic body is
 * going out of bounds or is in collision with an obstacles in the current room
 * According to his current direction, new dimensions will be set
 */

public class CheckPosImpl implements CheckPos, GameSettings {

	private DynamicBody entity;

	/**
	 * Default constructor
	 * 
	 * @param entity, the dynamic body's of which we have to check the next
	 *                available position
	 */
	public CheckPosImpl(DynamicBody entity) {
		this.entity = entity;
	}

	@Override
	public boolean possiblePos(RoomModel room, Pair<Integer, Integer> pos) {
		return !(isOutOfLimits(pos) || checkObstaclesRoom(room, pos));
	}

	/**
	 * Method used to check if a dynamic body is in collision with an obstacle in
	 * the dungeon
	 * 
	 * @param room, dynamic body's current room
	 * @param pos,  dynamic body's current position
	 * @return true if the dynamic body had a collision with an obstacle
	 */
	protected boolean checkObstaclesRoom(RoomModel room, Pair<Integer, Integer> pos) {
		boolean checkX, checkY;
		for (Pair<Integer, Integer> obst : room.getObstaclePositions()) {
			checkX = pos.getX() + entity.getDimension().getLeft() < obst.getX() + GameSettings.TILESIZE
					&& pos.getX() + entity.getDimension().getRight() > obst.getX();
			checkY = pos.getY() + entity.getDimension().getUp() < obst.getY() + GameSettings.OBST_DOWN
					&& pos.getY() + entity.getDimension().getDown() > obst.getY();
			if (checkX && checkY) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method used to check if a dynamic body's is going out of dungeon's bounds
	 * 
	 * @param pos, dynamic body's current coordinates
	 * @return true if the dynamic body is out of bounds
	 */
	private boolean isOutOfLimits(Pair<Integer, Integer> pos) {
		return ((pos.getX() + entity.getDimension().getLeft() < LIMITLEFT
				|| pos.getX() + entity.getDimension().getRight() > LIMITRIGHT)
				|| (pos.getY() + entity.getDimension().getUp() < LIMITUP
						|| pos.getY() + entity.getDimension().getDown() > LIMITDOWN));
	}

}

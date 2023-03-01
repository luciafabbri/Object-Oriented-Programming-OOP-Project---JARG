package main.dynamicBody.character.enemy.move.check;

import main.dynamicBody.move.Direction;
import main.dynamicBody.move.check.CheckPos;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

public interface CheckEnemy extends CheckPos {

	/**
	 * Methods use to change the Direction of monster
	 * 
	 * @param room, RoomModel where monster is located
	 * @param pos,  position of monster
	 * @param dir,  direction of monster
	 * @return a new Direction
	 */
	public Direction changeDir(RoomModel room, Pair<Integer, Integer> pos, Direction dir);

}

package main.dynamicBody.character.enemy.move;

import main.dynamicBody.move.Direction;
import main.worldModel.utilities.Pair;

/**
 * Interface use to move enemy
 */
public interface EnemyMovement {

	/**
	 * Method use to get the next position of enemy
	 * 
	 * @param pos,   Position of enemy
	 * @param speed, speed of enemy
	 * @param dir,   direction of enemy
	 * @return a Pair with next enemy's coordination
	 */
	public Pair<Integer, Integer> nextPos(Pair<Integer, Integer> pos, int speed, Direction dir);

	/**
	 * Method use to get the new direction of monster
	 * 
	 * @return the new Direction
	 */
	public Direction getDirection();

}

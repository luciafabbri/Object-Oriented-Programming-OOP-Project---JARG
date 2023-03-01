package main.dynamicBody.character.enemy.attack;

import main.dynamicBody.move.Direction;
import main.worldModel.utilities.Pair;

/**
 * Interface use to create bullets when monster attack
 */
public interface EnemyAttack {

	/**
	 * Method use to createBullets
	 * 
	 * @param pos, position where to spawn bullets
	 * @param dir, Direction of bullets
	 * @param dmg, damage that bullets do
	 */
	public void createBullets(Pair<Integer, Integer> pos, Direction dir, int dmg);

}

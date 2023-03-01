package main.dynamicBody.character.enemy.creator;

import main.dynamicBody.character.enemy.Enemy;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

/**
 * Interface that presents all the methods needed to create different type of
 * enemy
 */
public interface EnemyCreator {

	/**
	 * This methods create Monster with some specific characteristics based on the
	 * level where is located
	 * 
	 * @param lvl,    the number of the level where is located
	 * @param pos,    position of monster
	 * @param damage, damage of monster
	 * @param health, health of monster
	 * @param room,   the Room where monster is located
	 * @return Enemy created
	 */

	public Enemy getMonster(int lvl, Pair<Integer, Integer> pos, int health, int damage, RoomModel room);

	/**
	 * Create Boss
	 * 
	 * @param pos,  position of the Boss
	 * @param room, room of the Boss
	 * @return Boss
	 */
	public Enemy getBoss(Pair<Integer, Integer> pos, RoomModel room);

}

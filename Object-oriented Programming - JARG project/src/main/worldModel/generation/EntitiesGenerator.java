package main.worldModel.generation;

import org.newdawn.slick.SlickException;

/**
 * An interface that models the entity generator for rooms. A generation method
 * is present for each entity that may be present inside levels.
 *
 */
public interface EntitiesGenerator {

	/**
	 * @param numOfModifiers, number of modifier entities to be randomly generated
	 *                        in the room
	 * @throws SlickException
	 */
	void generateModifiers(int numOfModifiers) throws SlickException;

	/**
	 * Stairs generator. Each room is required to have either a Stairs object OR a
	 * Boss object.
	 * 
	 * @throws SlickException
	 */
	void generateStairs() throws SlickException;

	/**
	 * Boss generator. Each room is required to have either a Stairs object OR a
	 * Boss object.
	 * 
	 * @throws SlickException
	 */
	void generateBoss() throws SlickException;

	/**
	 * @param numOfEnemies, number of enemy entities to be randomly generated in the
	 *                      room
	 * @throws SlickException
	 */

	/**
	 * Key generator, each room contains a key that the player needs to pick up in
	 * order for doors to open
	 * 
	 * @throws SlickException
	 */
	void generateKey() throws SlickException;

	/**
	 * Coin collectible generator, there is only one coin per level, randomly
	 * generated in a level's room
	 * 
	 * @throws SlickException
	 */
	void generateCoin() throws SlickException;

	/**
	 * @param numOfEnemies, number of enemy entities to be randomly generated in the
	 *                      room
	 * 
	 * @throws SlickException
	 */
	void generateEnemies(int numOfEnemies) throws SlickException;

	/**
	 * @param numOfObstacles, number of obstacle entities to be randomly generated
	 *                        in the room
	 * @throws SlickException
	 */
	void generateObstacles(int numOfObstacles) throws SlickException;

}

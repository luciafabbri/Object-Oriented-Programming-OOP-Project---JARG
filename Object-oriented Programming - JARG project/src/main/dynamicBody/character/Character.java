package main.dynamicBody.character;

import java.util.Set;

import org.newdawn.slick.SlickException;

import main.dynamicBody.DynamicBody;
import main.dynamicBody.bullet.Bullet;
import main.dynamicBody.move.Direction;
import main.worldModel.utilities.Pair;

/**
 * An interface that extends interface DynamicBody in order to add new methods
 * and get information about characters
 */

public interface Character extends DynamicBody {

	/**
	 * @return character's coordinates
	 */
	Pair<Integer, Integer> getPosition();

	/**
	 * @return character's direction
	 */
	Direction getDirection();

	/**
	 * Method used to take character's damage from any other entity
	 * 
	 * @param damage, an int value to subtract to character's current health
	 * @throws SlickException
	 */
	void takeDamage(int damage) throws SlickException;

	/**
	 * @return character's bullet set
	 */
	Set<Bullet> getRoomBullets();

}

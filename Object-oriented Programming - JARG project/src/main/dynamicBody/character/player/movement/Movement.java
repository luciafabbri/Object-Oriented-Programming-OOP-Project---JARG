package main.dynamicBody.character.player.movement;

import org.newdawn.slick.Input;

import main.dynamicBody.move.Direction;
import main.worldModel.utilities.Pair;

/**
 * An interface that allows the player to move in the environment by pressing
 * keyboard's keys
 */

public interface Movement {

	/**
	 * According to the input received, this method will change player's coordinates
	 * and his direction
	 * 
	 * @param input, received from the keyboard
	 * @param pos,   player's current coordinates
	 * @param dir,   player's current direction
	 * @param speed, player's current speed
	 * @return the new coordinates of the player
	 */
	Pair<Integer, Integer> movePlayer(Input input, Pair<Integer, Integer> pos, Direction dir, int speed);

	/**
	 * @return player's direction
	 */
	Direction getDirection();

}

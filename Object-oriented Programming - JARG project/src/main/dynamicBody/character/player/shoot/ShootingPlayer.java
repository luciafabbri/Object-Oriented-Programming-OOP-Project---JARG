package main.dynamicBody.character.player.shoot;

import org.newdawn.slick.Input;

/**
 * An interface used to create player's bullets
 */

public interface ShootingPlayer {

	/**
	 * Method used to check if the space bare is pressed, which means that the
	 * player would start shooting
	 * 
	 * @param input, received from the keyboard
	 */
	void checkShooting(Input input);

}

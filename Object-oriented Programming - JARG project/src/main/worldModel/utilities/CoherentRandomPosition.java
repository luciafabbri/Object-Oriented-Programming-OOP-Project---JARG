package main.worldModel.utilities;

import java.util.Random;

/**
 * Implementation of the RandomPosition interface that is coherent to the game
 *
 */
public class CoherentRandomPosition implements RandomPosition {

	private final Random random = new Random();

	/**
	 * The generated position is within the set room limits of the game
	 */
	public Pair<Integer, Integer> generateRandomPosition() {

		final Integer xPos = GameSettings.TILESIZE
				+ random.nextInt(GameSettings.WIDTH / GameSettings.TILESIZE - 2) * GameSettings.TILESIZE;
		final Integer yPos = GameSettings.TILESIZE
				+ random.nextInt(GameSettings.HEIGHT / GameSettings.TILESIZE - 2) * GameSettings.TILESIZE;

		return new Pair<Integer, Integer>(xPos, yPos);
	}

}

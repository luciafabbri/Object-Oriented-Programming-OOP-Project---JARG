package main.worldModel.generation;

import java.io.IOException;

import main.worldModel.LevelModel;

/**
 * An interface that models the level generators, the entities that
 * pseudo-randomly generate game levels.
 *
 */
public interface LevelModelGenerator {

	/**
	 * @param levelNumber, the number of the level to be generated, acceptable
	 *                     numbers range from 1 to 4, as the game is made of 4
	 *                     levels
	 * @return a worldModel.LevelModel object, also known as the model of a level
	 * @throws IOException
	 */
	LevelModel generateLevel(Integer levelNumber) throws IOException;

}

package main.gameEntities;

import main.worldModel.utilities.Pair;
import main.worldModel.utilities.enums.Entities;

/**
 * Class that models an obstacle, an insurmountable game entity that the player
 * must walk around
 *
 */
public class Obstacle extends Insurmountable {

	public Obstacle(final Pair<Integer, Integer> position) {
		super(position, Entities.BOULDER);
	}

}

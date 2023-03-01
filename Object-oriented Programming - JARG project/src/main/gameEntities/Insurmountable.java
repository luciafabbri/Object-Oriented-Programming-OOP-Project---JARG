package main.gameEntities;

import main.worldModel.utilities.Pair;
import main.worldModel.utilities.enums.Entities;

/**
 * Class defining the insurmountable entities, those that the player cannot
 * surpass
 *
 */
public class Insurmountable extends GameEntity {

	public Insurmountable(final Pair<Integer, Integer> position, final Entities typeEnt) {
		super(position, typeEnt);
	}

}

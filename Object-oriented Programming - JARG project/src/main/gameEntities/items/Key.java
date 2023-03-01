package main.gameEntities.items;

import main.worldModel.utilities.Pair;
import main.worldModel.utilities.enums.Entities;

public class Key extends ItemImpl {

	/**
	 * Constructor for Key item
	 * 
	 * @param coord, to know in which position it is
	 * @throws SlickException
	 * @see SlickException
	 */
	public Key(final Pair<Integer, Integer> coord) {
		super(coord, Entities.KEY);
	}

}

package main.gameEntities;

import main.worldModel.utilities.Pair;
import main.worldModel.utilities.enums.Entities;

/**
 * Abstract class that defines the behavior of the game's inanimate entities
 *
 */
public abstract class GameEntity {

	private Pair<Integer, Integer> position;
	private Entities typeEnt;

	/**
	 * Constructor
	 * 
	 * @param position, the entity's position in the room
	 * @param typeEnt,  the type of the entity
	 */
	public GameEntity(final Pair<Integer, Integer> position, final Entities typeEnt) {
		this.position = position;
		this.typeEnt = typeEnt;
	}

	/**
	 * @return the entity's position
	 */
	public Pair<Integer, Integer> getPosition() {
		return position;
	}

	/**
	 * @return typeEnt, the entity's type
	 */
	public Entities getTypeEnt() {
		return typeEnt;
	}

}

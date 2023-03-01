package main.gameEntities.modifiers;

import main.dynamicBody.character.player.Stats;
import main.gameEntities.Pickupable;
import main.worldModel.utilities.Pair;
import main.worldModel.utilities.enums.Entities;

public class ModifiersImpl extends Pickupable {

	/**
	 * Variable containing which Stat the Modifier changes
	 */
	private Stats stat;
	/**
	 * Variable containing how much the Modifier changes
	 */
	private int modQty;

	/**
	 * Constructor for ModifiersImpl, to create Modifiers to pickup
	 * 
	 * @param position, to associate every Modifier with a position to be placed
	 * @param stat,     to associate which statistic is supposed to change in the
	 *                  Player
	 * @param qty,      to know how much the Modifier is supposed to increment or
	 *                  decrement
	 * @param name,     to associate a name with the Modifier
	 * @param texture,  to associate a texture with the Modifier
	 * @param typeEnt   to denote which type of Modifier it is
	 */
	public ModifiersImpl(final Pair<Integer, Integer> position, final Stats stat, final int qty, final String name,
			final Entities typeEnt) {
		super(position, typeEnt);
		this.stat = stat;
		this.modQty = qty;
	}

	/**
	 * Method that returns the Stat of the Modifier
	 * 
	 * @return Stats
	 */
	public Stats getStat() {
		return stat;
	}

	/**
	 * Method that returns how much the Modifier changes
	 * 
	 * @return int, the quantity
	 */
	public int getModQty() {
		return modQty;
	}
}

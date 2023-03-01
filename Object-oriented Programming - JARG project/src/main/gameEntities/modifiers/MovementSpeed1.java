package main.gameEntities.modifiers;

import main.dynamicBody.character.player.Stats;
import main.worldModel.utilities.Pair;
import main.worldModel.utilities.enums.Entities;

public class MovementSpeed1 extends ModifiersImpl {

	/**
	 * Method used to create MovementSpeed modifier
	 * 
	 * @param coord
	 */
	public MovementSpeed1(Pair<Integer, Integer> coord) {
		super(coord, Stats.MOV_SPEED, 1, "Movemnet Speed Upgrade", Entities.MOVEMENTSPEED1);
	}
}

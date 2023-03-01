package main.gameEntities.modifiers;

import main.dynamicBody.character.player.Stats;
import main.worldModel.utilities.Pair;
import main.worldModel.utilities.enums.Entities;

public class AttackSpeed1 extends ModifiersImpl {

	/**
	 * Method used to create AttackSpeed modifier
	 * 
	 * @param coord
	 */
	public AttackSpeed1(Pair<Integer, Integer> coord) {
		super(coord, Stats.PROJ_SPEED, 15, "ROF Upgrade", Entities.ATTACKSPEED1);
	}
}

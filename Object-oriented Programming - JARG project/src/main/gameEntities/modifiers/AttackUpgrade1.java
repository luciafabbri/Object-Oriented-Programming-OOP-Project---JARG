package main.gameEntities.modifiers;

import main.dynamicBody.character.player.Stats;
import main.worldModel.utilities.Pair;
import main.worldModel.utilities.enums.Entities;

public class AttackUpgrade1 extends ModifiersImpl {

	/**
	 * Method used to create AttackUpgrade modifier
	 * 
	 * @param coord
	 */
	public AttackUpgrade1(Pair<Integer, Integer> coord) {
		super(coord, Stats.ATTACK, 10, "Ebony Bow", Entities.ATTACKUPGRADE1);
	}
}

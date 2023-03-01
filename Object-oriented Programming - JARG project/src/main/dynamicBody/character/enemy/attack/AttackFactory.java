package main.dynamicBody.character.enemy.attack;

import main.dynamicBody.character.enemy.Enemy;
import main.worldModel.RoomModel;

/**
 * Factory use to get the type of the Attack of an Enemy
 */
public class AttackFactory {

	/**
	 * Method use from an Enemy to get the type of the Attack
	 * 
	 * @param typeAttack, the type of attack
	 * @param room,       room where enemy is located
	 * @param enemy,      the enemy that will use this type of attack
	 * @return the EnemyAttack
	 */
	public EnemyAttack selectAttack(TypeAttack typeAttack, RoomModel room, Enemy enemy) {
		switch (typeAttack) {
		case ONE_SIDE:
			return new OneSideAtt(room, enemy);

		case TWO_SIDE:
			return new TwoSideAtt(room, enemy);

		case FOUR_SIDE:
			return new FourSideAtt(room, enemy);

		case TRIPLE:
			return new TripleAtt(room, enemy);

		default:
			throw new IllegalArgumentException("The Attack of the TypeAttack isn't implemented");

		}
	}
}

package main.dynamicBody.character.enemy.dimension;

import main.dynamicBody.character.enemy.TypeEnemy;

/**
 * Factory use to get dimension of Enemy
 */
public class EnemyDimFactory {
	/**
	 * Method use to know the dimension of a monster
	 * 
	 * @param mon, TypeMonster of the monster
	 * @return the a Pair<DimensionMonster, DimensionMonster> of the TypeEnemy
	 */
	public EnemyDimension getDimensionMoster(TypeEnemy mon) {
		switch (mon) {
		case PLANT:
			return EnemyDimension.PLANT;
		case BOWMAN:
		case NINJA:
		case MAGE:
		case BOSS:
			return EnemyDimension.DEFAULT;

		default:
			throw new IllegalArgumentException("The Dimension of the TypeEnemy isn't implemented");
		}

	}
}

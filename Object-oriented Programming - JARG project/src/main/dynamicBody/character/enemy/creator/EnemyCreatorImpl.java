package main.dynamicBody.character.enemy.creator;

import main.dynamicBody.character.enemy.Enemy;
import main.dynamicBody.character.enemy.EnemyImpl;
import main.dynamicBody.character.enemy.TypeEnemy;
import main.dynamicBody.character.enemy.attack.TypeAttack;
import main.dynamicBody.character.enemy.boss.BossImpl;
import main.dynamicBody.character.enemy.move.TypeMove;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

/**
 * Class that implements interface EnemyCreator
 */
public class EnemyCreatorImpl implements EnemyCreator {

	@Override
	public Enemy getMonster(int type, Pair<Integer, Integer> pos, int health, int damage, RoomModel room) {
		switch (type) {
		case 1:
			return new EnemyImpl(pos, damage, health, TypeMove.STRAIGHT, TypeAttack.ONE_SIDE, room, TypeEnemy.BOWMAN);
		case 2:
			return new EnemyImpl(pos, damage, health, TypeMove.TELEPORT, TypeAttack.TWO_SIDE, room, TypeEnemy.MAGE);
		case 3:
			return new EnemyImpl(pos, damage, health, TypeMove.RANDOM, TypeAttack.TRIPLE, room, TypeEnemy.NINJA);
		case 4:
			return new EnemyImpl(pos, damage, health, TypeMove.IMMOBILIZED, TypeAttack.FOUR_SIDE, room,
					TypeEnemy.PLANT);
		default:
			throw new IllegalArgumentException("Number isn't between 1 and 4");
		}
	}

	@Override
	public Enemy getBoss(Pair<Integer, Integer> pos, RoomModel room) {
		return new BossImpl(pos, room);
	}

}

package main.dynamicBody.character.enemy.move;

import main.dynamicBody.character.enemy.Enemy;
import main.worldModel.RoomModel;

/**
 * Factory use to get the type of the Movement of an Enemy
 */
public class MoveFactory {

	/**
	 * Method use from an Enemy to get the type of the Movement
	 * 
	 * @param typeAttack, the type of attack
	 * @param room,       room where enemy is located
	 * @param enemy,      the enemy that will use this type of attack
	 * @return the EnemyMovement
	 */
	public EnemyMovement selectMove(TypeMove typeMove, RoomModel room, Enemy enemy) {
		switch (typeMove) {
		case STRAIGHT:
			return new StraightMove(room, enemy);

		case TELEPORT:
			return new TeleportMove(room, enemy);

		case RANDOM:
			return new RandomMove(room, enemy);

		case IMMOBILIZED:
			return new ImmobilizedMove();

		case TO_PLAYER:
			return new ToPlayerMove(room, enemy);

		default:
			throw new IllegalArgumentException("The Movemenet of the TypeMove isn't implemented");

		}
	}

}

package main.dynamicBody.character.enemy.attack;

import main.dynamicBody.character.enemy.Enemy;
import main.dynamicBody.move.Direction;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

/**
 * Class implements EnemyAttack use to create 3 bullet in 3 different direction
 * when enemy attack
 */
public class TripleAtt implements EnemyAttack {

	private RoomModel currentRoom;
	private Enemy enemy;

	/**
	 * Default Constructor
	 * 
	 * @param room,  room where bullet spawn
	 * @param enemy, the Enemy that create this Bullet
	 */
	public TripleAtt(RoomModel room, Enemy enemy) {
		currentRoom = room;
		this.enemy = enemy;

	}

	@Override
	public void createBullets(Pair<Integer, Integer> pos, Direction dir, int dmg) {
		EnemyAttack attOne = new OneSideAtt(currentRoom, enemy);
		EnemyAttack attTwo = new TwoSideAtt(currentRoom, enemy);

		attOne.createBullets(pos, dir, dmg);
		attTwo.createBullets(pos, dir, dmg);

	}

}

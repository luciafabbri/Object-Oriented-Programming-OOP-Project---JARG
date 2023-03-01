package main.dynamicBody.character.enemy.attack;

import main.dynamicBody.bullet.Bullet;
import main.dynamicBody.bullet.BulletEnemy;
import main.dynamicBody.bullet.DistanceBull;
import main.dynamicBody.bullet.TypeBullet;
import main.dynamicBody.character.enemy.Enemy;
import main.dynamicBody.move.Direction;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

/**
 * Class implements EnemyAttack use to create 1 bullet when enemy attack
 */
public class OneSideAtt implements EnemyAttack {

	private RoomModel currentRoom;
	private Enemy enemy;

	/**
	 * Default Constructor
	 * 
	 * @param room,  room where bullet spawn
	 * @param enemy, the Enemy that create this Bullet
	 */
	public OneSideAtt(RoomModel room, Enemy enemy) {
		currentRoom = room;
		this.enemy = enemy;
	}

	@Override
	public void createBullets(Pair<Integer, Integer> pos, Direction dir, int dmg) {

		Bullet bull = new BulletEnemy(DistanceBull.calculateBullPos(dir, enemy, TypeBullet.ENEMY_BULL), dmg, dir,
				currentRoom);

		enemy.addBullet(bull);

	}

}

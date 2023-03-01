package main.dynamicBody.character.enemy.attack;

import java.util.List;

import main.dynamicBody.bullet.Bullet;
import main.dynamicBody.bullet.BulletEnemy;
import main.dynamicBody.bullet.DistanceBull;
import main.dynamicBody.bullet.TypeBullet;
import main.dynamicBody.character.enemy.Enemy;
import main.dynamicBody.move.Direction;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

/**
 * Class implements EnemyAttack use to create 2 bullet when enemy attack in 2
 * different direction
 */
public class TwoSideAtt implements EnemyAttack {

	private RoomModel currentRoom;
	private Enemy enemy;

	/**
	 * Default Constructor
	 * 
	 * @param room,  room where bullet spawn
	 * @param enemy, the Enemy that create this Bullet
	 */
	public TwoSideAtt(RoomModel room, Enemy enemy) {
		currentRoom = room;
		this.enemy = enemy;
	}

	@Override
	public void createBullets(Pair<Integer, Integer> pos, Direction dir, int dmg) {
		List<Direction> dirSpawn = Direction.getNearDistance(dir, 2);
		List<Direction> bullDir = Direction.getNearDistance(dir, 1);

		for (int i = 0; i < 2; i++) {
			Bullet bull = new BulletEnemy(DistanceBull.calculateBullPos(dirSpawn.get(i), enemy, TypeBullet.ENEMY_BULL),
					dmg, bullDir.get(i), currentRoom);

			enemy.addBullet(bull);
		}

	}

}

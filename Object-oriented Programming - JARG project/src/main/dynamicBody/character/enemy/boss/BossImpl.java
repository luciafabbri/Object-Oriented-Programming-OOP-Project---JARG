package main.dynamicBody.character.enemy.boss;

import main.dynamicBody.character.enemy.Enemy;
import main.dynamicBody.character.enemy.EnemyImpl;
import main.dynamicBody.character.enemy.TypeEnemy;
import main.dynamicBody.character.enemy.attack.TypeAttack;
import main.dynamicBody.character.enemy.boss.BossDefault;
import main.dynamicBody.character.enemy.move.TypeMove;
import main.dynamicBody.move.Direction;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

/**
 * Enumeration used to set enemy's image based on his current direction
 */
public class BossImpl extends EnemyImpl implements Enemy {

	/**
	 * Default constructor
	 * 
	 * @param pos,    position of the Boss
	 * @param damage, damage of the Boss
	 * @param speed,  speed of the Boss
	 * @param health, max health of the Boss
	 * @param move,   TypeMove of Boss
	 * @param dir,    Direction of Boss
	 * @param att,    TypeAttack of Boss
	 * @param room,   RoomModel where Boss spawn
	 */
	public BossImpl(Pair<Integer, Integer> pos, int damage, int speed, int health, TypeMove move, Direction dir,
			TypeAttack att, RoomModel room) {
		super(pos, damage, speed, health, move, dir, att, room, TypeEnemy.BOSS);
	}

	/**
	 * Constructor with Boss default value
	 * 
	 * @param pos,  position of the Boss
	 * @param room, RoomModel where Boss spawn
	 */
	public BossImpl(Pair<Integer, Integer> pos, RoomModel room) {
		this(pos, BossDefault.DAMAGE.getValue(), 1, BossDefault.HEALTH.getValue(), TypeMove.getBossMove(),
				Direction.getRandomDir(), TypeAttack.getBossAtt(), room);
	}

}

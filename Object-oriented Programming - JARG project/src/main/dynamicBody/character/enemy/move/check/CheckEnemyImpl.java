package main.dynamicBody.character.enemy.move.check;

import main.dynamicBody.character.Character;
import main.dynamicBody.move.Direction;
import main.dynamicBody.move.check.CheckPosImpl;
import main.worldModel.RoomModel;
import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.Pair;

/**
 * Class that implements interface CheckEnemy used to check and then to force
 * the enemy to do the specific chosen actions
 */
public class CheckEnemyImpl extends CheckPosImpl implements CheckEnemy, GameSettings {

	private int x, y;
	private Direction newDir;
	private Character character;

	public CheckEnemyImpl(Character character) {
		super(character);
		this.character = character;
	}

	@Override
	public Direction changeDir(RoomModel room, Pair<Integer, Integer> pos, Direction dir) {

		if (pos.getX() + character.getDimension().getRight() > LIMITRIGHT) {
			this.x = -dir.getAbscissa();
		} else if (pos.getX() + character.getDimension().getLeft() < LIMITLEFT) {
			this.x = -dir.getAbscissa();
		} else {
			this.x = dir.getAbscissa();
		}

		if (pos.getY() + character.getDimension().getDown() > LIMITDOWN) {
			this.y = -dir.getOrdinate();
		} else if (pos.getY() + character.getDimension().getUp() < LIMITUP) {
			this.y = -dir.getOrdinate();
		} else {
			this.y = dir.getOrdinate();
		}

		if (checkObstaclesRoom(room, pos)) {
			for (Pair<Integer, Integer> obst : room.getObstaclePositions()) {
				if (pos.getX() + character.getDimension().getLeft() < obst.getX() + GameSettings.TILESIZE
						|| pos.getX() + character.getDimension().getRight() > obst.getX()) {
					this.x = -dir.getAbscissa();
				}
				if (pos.getY() + character.getDimension().getDown() < obst.getY() + GameSettings.OBST_DOWN
						|| pos.getY() + character.getDimension().getUp() > obst.getY()) {
					this.y = -dir.getOrdinate();
				}
			}
		}

		for (Direction d : Direction.values()) {
			if (d.getAbscissa() == x && d.getOrdinate() == y) {
				this.newDir = d;
			}
		}

		return newDir;
	}

}

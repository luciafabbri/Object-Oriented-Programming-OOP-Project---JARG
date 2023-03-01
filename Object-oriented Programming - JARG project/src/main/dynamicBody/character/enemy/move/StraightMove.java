package main.dynamicBody.character.enemy.move;

import main.dynamicBody.character.Character;
import main.dynamicBody.character.enemy.move.check.CheckEnemy;
import main.dynamicBody.character.enemy.move.check.CheckEnemyImpl;
import main.dynamicBody.move.Direction;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

/**
 * Class that implement EnemyMovement use when enemy's movement is Straight
 */
public class StraightMove implements EnemyMovement {

	private CheckEnemy check;
	private Direction nextDir = null;

	private RoomModel currentRoom;

	/**
	 * Default constructor
	 * 
	 * @param room,      room where character is
	 * @param character, the character who need to move
	 */
	public StraightMove(RoomModel room, Character character) {
		currentRoom = room;
		check = new CheckEnemyImpl(character);
	}

	@Override
	public Pair<Integer, Integer> nextPos(Pair<Integer, Integer> pos, int speed, Direction dir) {

		Pair<Integer, Integer> nextPos = new Pair<Integer, Integer>(pos.getX() + (dir.getAbscissa() * speed),
				pos.getY() + (dir.getOrdinate() * speed));
		nextDir = dir;

		if (check.possiblePos(currentRoom, nextPos)) {
			return nextPos;
		} else {
			this.nextDir = check.changeDir(currentRoom, nextPos, dir);
			return pos;

		}

	}

	@Override
	public Direction getDirection() {
		if (nextDir == null) {
			throw new IllegalStateException(" Direction isn't Initialized ");
		}
		return nextDir;
	}
}

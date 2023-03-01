package main.dynamicBody.character.enemy.move;

import main.dynamicBody.move.Direction;
import main.worldModel.utilities.Pair;

/**
 * Class that implement EnemyMovement use when enemy's movement is Immobilized
 */
public class ImmobilizedMove implements EnemyMovement {

	private Direction nextDir;

	public ImmobilizedMove() {
	}

	@Override
	public Pair<Integer, Integer> nextPos(Pair<Integer, Integer> pos, int speed, Direction dir) {
		nextDir = dir;
		return pos;
	}

	@Override
	public Direction getDirection() {
		return nextDir;
	}

}

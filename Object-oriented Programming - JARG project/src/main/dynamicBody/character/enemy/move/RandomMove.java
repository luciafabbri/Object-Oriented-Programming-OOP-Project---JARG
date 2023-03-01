package main.dynamicBody.character.enemy.move;

import java.util.Random;

import main.dynamicBody.character.Character;
import main.dynamicBody.move.Direction;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

/**
 * Class that implement EnemyMovement use when enemy's movement is Random
 */
public class RandomMove implements EnemyMovement {

	private static final int MINIMUM_STEPS = 250;
	private static final int RANDOM_ADD = 130;

	private EnemyMovement move;
	private int moveCounter = 0;
	private Random random = new Random();
	private Direction nextDir;

	/**
	 * Default constructor
	 * 
	 * @param room,      room where character is
	 * @param character, the character who need to move
	 */
	public RandomMove(RoomModel room, Character character) {
		move = new StraightMove(room, character);
	}

	@Override
	public Pair<Integer, Integer> nextPos(Pair<Integer, Integer> pos, int speed, Direction dir) {
		Pair<Integer, Integer> nextPos = move.nextPos(pos, speed, dir);
		if (moveCounter <= 0 || enableMov(dir)) {
			moveCounter = random.nextInt(RANDOM_ADD) + MINIMUM_STEPS;
			nextDir = Direction.getRandomDir();
			while (nextDir.equals(dir)) {
				nextDir = Direction.getRandomDir();
			}
			nextPos = pos;
		} else {
			moveCounter--;
			nextDir = dir;
		}
		return nextPos;
	}

	@Override
	public Direction getDirection() {
		if (nextDir == null) {
			throw new IllegalStateException(" Direction isn't Initialized ");
		}
		return nextDir;
	}

	private boolean enableMov(Direction dir) {
		return move.getDirection() != dir;
	}
}

package main.dynamicBody.character.enemy.move;

import java.util.Random;

import main.dynamicBody.character.Character;
import main.dynamicBody.character.enemy.move.check.CheckEnemy;
import main.dynamicBody.character.enemy.move.check.CheckEnemyImpl;
import main.dynamicBody.move.Direction;
import main.worldModel.RoomModel;
import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.Pair;

/**
 * Class that implement EnemyMovement use when enemy's movement is and Teleport
 */
public class TeleportMove implements EnemyMovement {

	private static final int SLEEP_TIME = 5000;

	private CheckEnemy check;
	private Random rand = new Random();
	private Pair<Integer, Integer> newPos;
	private Direction nextDir = null;

	private RoomModel currentRoom;

	private long startMillis = 0;
	private long stopMillis;

	/**
	 * Default constructor
	 * 
	 * @param room,      room where character is
	 * @param character, the character who need to move
	 */
	public TeleportMove(RoomModel room, Character character) {
		currentRoom = room;
		check = new CheckEnemyImpl(character);
	}

	@Override
	public Pair<Integer, Integer> nextPos(Pair<Integer, Integer> pos, int speed, Direction dir) {

		stopMillis = System.currentTimeMillis();
		if (stopMillis - startMillis > SLEEP_TIME) {
			int x, y;
			do {
				x = rand.nextInt(GameSettings.WIDTH);
				y = rand.nextInt(GameSettings.HEIGHT);
				newPos = new Pair<>(x, y);
			} while (!check.possiblePos(currentRoom, newPos));
			nextDir = Direction.getRandomDir();
			startMillis = System.currentTimeMillis();
		} else {
			this.nextDir = dir;
			this.newPos = pos;
		}

		return newPos;
	}

	@Override
	public Direction getDirection() {
		if (nextDir == null) {
			throw new IllegalStateException(" Direction isn't Initialized ");
		}
		return nextDir;
	}

}

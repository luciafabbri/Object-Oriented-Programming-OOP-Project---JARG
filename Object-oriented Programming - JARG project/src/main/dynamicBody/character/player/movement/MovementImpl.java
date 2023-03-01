package main.dynamicBody.character.player.movement;

import main.coordination.SoundBoardFactory;
import main.dynamicBody.move.Direction;
import main.worldModel.utilities.Pair;
import org.newdawn.slick.Input;

/**
 * Class that implements interface Movement used to move the player through the
 * different rooms in the dungeon
 */

public class MovementImpl implements Movement {

	private Direction direction;
	private int newSpeed;

	/**
	 * Default constructor
	 */
	public MovementImpl() {
	}

	@Override
	public Pair<Integer, Integer> movePlayer(Input input, Pair<Integer, Integer> pos, Direction dir, int speed) {
		this.direction = dir;
		this.newSpeed = speed;

		/**
		 * UP input
		 */
		if (input.isKeyDown(Input.KEY_W)) {
			this.direction = Direction.NORTH;
			SoundBoardFactory.playFootsteps();
			return new Pair<Integer, Integer>(pos.getX(), pos.getY() - newSpeed);
		}

		/**
		 * DOWN input
		 */
		if (input.isKeyDown(Input.KEY_S)) {
			this.direction = Direction.SOUTH;
			SoundBoardFactory.playFootsteps();
			return new Pair<Integer, Integer>(pos.getX(), pos.getY() + newSpeed);
		}

		/**
		 * LEFT input
		 */
		if (input.isKeyDown(Input.KEY_A)) {
			this.direction = Direction.WEST;
			SoundBoardFactory.playFootsteps();
			return new Pair<Integer, Integer>(pos.getX() - newSpeed, pos.getY());
		}

		/**
		 * RIGHT input
		 */
		if (input.isKeyDown(Input.KEY_D)) {
			this.direction = Direction.EAST;
			SoundBoardFactory.playFootsteps();
			return new Pair<Integer, Integer>(pos.getX() + newSpeed, pos.getY());
		}
		return pos;
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}

}
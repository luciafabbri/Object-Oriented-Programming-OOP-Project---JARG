package main.dynamicBody.bullet;

import main.dynamicBody.UpDownLeftRight;
import main.dynamicBody.bullet.dimension.BulletDimFactory;
import main.dynamicBody.character.Character;
import main.dynamicBody.move.Direction;
import main.worldModel.utilities.Pair;

/**
 * Class use to get distance of bullet from character
 */
public final class DistanceBull {

	private static BulletDimFactory dimFactory = new BulletDimFactory();

	/**
	 * Private constructor to prevent instantiation
	 */
	private DistanceBull() {
	}

	/**
	 * Method that calculate the position where spawn bullet
	 * 
	 * @param dir,       direction where to spawn
	 * @param character, the Character who create that
	 * @return return a Pair with the coordinates
	 */
	public static Pair<Integer, Integer> calculateBullPos(Direction dir, Character character, TypeBullet type) {
		Pair<Integer, Integer> distance = calcDistance(dir, character, type);
		return new Pair<Integer, Integer>(character.getPosition().getX() + distance.getX(),
				character.getPosition().getY() + distance.getY());
	}

	/**
	 * Method used to calculate the distance where the bullet should be created
	 * 
	 * @param dir
	 * @param character
	 * @return
	 */
	private static Pair<Integer, Integer> calcDistance(Direction dir, Character character, TypeBullet type) {
		UpDownLeftRight<Integer> dim = dimFactory.getDimensionBullet(type).getX().getDimension();
		int distanceSpawn = BulletDefault.DISTANCESPAWNBULL.getValue();
		switch (dir) {
		case NORTH:
			return new Pair<Integer, Integer>(0, -(dim.getDown() + distanceSpawn));
		case SOUTH:
			return new Pair<Integer, Integer>(0, (character.getDimension().getDown() - dim.getUp()) + distanceSpawn);
		case EAST:
			return new Pair<Integer, Integer>((character.getDimension().getRight() - dim.getLeft()) + distanceSpawn, 0);
		case WEST:
			return new Pair<Integer, Integer>(character.getDimension().getLeft() - (dim.getRight() + distanceSpawn), 0);
		default:
			throw new IllegalArgumentException();
		}
	}

}

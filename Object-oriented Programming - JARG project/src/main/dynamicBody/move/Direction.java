package main.dynamicBody.move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import main.dynamicBody.character.enemy.move.CircularList;

/**
 * Enumeration used to represent all the different type of dynamic body's
 * directions
 */

public enum Direction {

	NORTH_WEST(-1, -1), NORTH(0, -1), NORTH_EAST(1, -1), EAST(1, 0), SOUTH_EAST(1, 1), SOUTH(0, 1), SOUTH_WEST(-1, 1),
	WEST(-1, 0);

	private int abscissa;
	private int ordinate;

	/**
	 * Default constructor
	 * 
	 * @param x, x axe value
	 * @param y, y axe value
	 */
	Direction(final int x, final int y) {
		this.abscissa = x;
		this.ordinate = y;
	}

	public int getAbscissa() {
		return this.abscissa;
	}

	public int getOrdinate() {
		return this.ordinate;
	}

	public static Direction getRandomDir() {
		Random random = new Random();
		return values()[(random.nextInt(4)) * 2 + 1];
	}

	public static List<Direction> getNearDistance(Direction dir, int distance) {
		CircularList<Direction> directions = new CircularList<Direction>();
		Collections.addAll(directions, Direction.values());

		List<Direction> dirAndNear = new ArrayList<>();
		int index = directions.indexOf(dir);

		dirAndNear.add(directions.get((index + distance) % directions.size()));
		dirAndNear.add(directions.get((index - distance) % directions.size()));

		return dirAndNear;
	}

	public static List<Direction> getDirectionList(boolean i) {
		return Arrays.asList(Direction.values()).stream().filter(x -> (x.getAbscissa() * x.getOrdinate() == 0) == i)
				.collect(Collectors.toList());
	}
}

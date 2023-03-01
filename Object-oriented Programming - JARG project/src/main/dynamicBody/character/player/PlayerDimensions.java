package main.dynamicBody.character.player;

import main.dynamicBody.UpDownLeftRight;
import main.worldModel.utilities.Pair;

/**
 * Enumeration used to get player's dimensions based on his current direction
 */

public enum PlayerDimensions {

	DIMENSIONS(0, 63, 15, 46);

	private UpDownLeftRight<Integer> dimensions;

	/**
	 * Default constructor
	 * 
	 * @param up,    player's up dimension
	 * @param down,  player's down dimension
	 * @param left,  player's left dimension
	 * @param right, player's right dimension
	 */
	PlayerDimensions(int up, int down, int left, int right) {
		this.dimensions = new UpDownLeftRight<>(up, down, left, right);
	}

	public static Pair<PlayerDimensions, PlayerDimensions> getPlayerDimensions(PlayerImpl player) {
		return new Pair<>(DIMENSIONS, DIMENSIONS);
	}

	public UpDownLeftRight<Integer> getDimensions() {
		return this.dimensions;
	}

}

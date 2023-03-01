package main.dynamicBody.character.enemy.dimension;

import main.dynamicBody.UpDownLeftRight;

/**
 * Enumeration used to get enemy's dimensions based on his current direction
 */

public enum EnemyDimension {

	/**
	 * Size of different Type of Enemy
	 */

	DEFAULT(0, 63, 15, 46),

	PLANT(0, 63, 0, 63),

	BOSS(0, 62, 11, 54);

	private UpDownLeftRight<Integer> dim;

	/**
	 * Default constructor
	 * 
	 * @param up,    position of up's pixel
	 * @param down,  position of down's pixel
	 * @param left,  position of left's pixel
	 * @param right, position of right's pixel
	 */
	EnemyDimension(int up, int down, int left, int right) {
		this.dim = new UpDownLeftRight<>(up, down, left, right);
	}

	/**
	 * Get the dimension of an Enemy
	 * 
	 * @return
	 */
	public UpDownLeftRight<Integer> getDimension() {
		return dim;
	}

}

package main.dynamicBody.character.enemy;

/**
 * Enumeration use to locate the default's value for enemy
 */
public enum EnemyDefault {

	/**
	 * Rate of Fire of bullet Speed of Enemy
	 */
	ROF(2000), SPEED(2);

	private int value;

	EnemyDefault(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}

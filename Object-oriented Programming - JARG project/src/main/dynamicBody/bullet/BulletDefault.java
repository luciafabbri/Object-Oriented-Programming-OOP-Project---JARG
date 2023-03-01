package main.dynamicBody.bullet;

public enum BulletDefault {

	/**
	 * constant value used to represent the default distance between bullet and the
	 * dynamic body that has used it in order to attack
	 */
	DISTANCESPAWNBULL(10), SPEED(3);

	private int value;

	/**
	 * Default constructor
	 * 
	 * @param value,
	 */
	BulletDefault(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}

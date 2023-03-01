package main.dynamicBody.character.enemy.boss;

/**
 * Enumeration use to locate the default's value for boss
 */
public enum BossDefault {

	HEALTH(1000), SPEED(15), DAMAGE(100);

	private int value;

	BossDefault(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}

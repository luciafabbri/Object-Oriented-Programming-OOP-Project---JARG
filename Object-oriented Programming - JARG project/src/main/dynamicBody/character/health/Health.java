package main.dynamicBody.character.health;

/**
 * An interface that defines all the aspects of the class Health, used to get
 * information about player's and enemies's lives
 */

public interface Health {

	/**
	 * @return the value of the dynamic body's current health
	 */
	int getCurrentHealth();

	/**
	 * @param dmg, an int value to be subtracted to the dynamic body's current
	 *             health
	 */
	void takeDmg(int damage);

	/**
	 * @return true if dynamic body's health value is greater than 0
	 */
	boolean isAlive();

	/**
	 * @return the value of the dynamic body's max health
	 */
	int getMaxHealth();

	/**
	 * @param health, an int value to be subtracted to the dynamic body's current
	 *                health
	 */
	void setCurrentHealth(int health);

	/**
	 * @param upgrade, an int value to be added to the dynamic body's max health
	 */
	void upgradeMaxHealth(int upgrade);

	/**
	 * @param heal, an int value to be added to the dynamic body's current life
	 */
	void heal(int heal);

}

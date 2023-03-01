package main.dynamicBody.character.health;

/**
 * Class that implements interface Health used to represent all the characters's
 * health aspects in the dungeon
 */

public class HealthImpl implements Health {

	private int maxHealth;
	private int currentHealth;

	/**
	 * Default constructor
	 * 
	 * @param health, character's initial health
	 */
	public HealthImpl(int health) {
		this.maxHealth = health;
		this.currentHealth = health;
	}

	@Override
	public int getMaxHealth() {
		return this.maxHealth;
	}

	@Override
	public int getCurrentHealth() {
		return this.currentHealth;
	}

	@Override
	public void setCurrentHealth(int health) {
		this.currentHealth = health;
	}

	@Override
	public void takeDmg(int damage) {
		this.currentHealth = this.currentHealth - damage;
	}

	@Override
	public boolean isAlive() {
		return this.currentHealth > 0;
	}

	@Override
	public void upgradeMaxHealth(int upgrade) {
		this.maxHealth = this.maxHealth + upgrade;
	}

	@Override
	public void heal(int heal) {
		this.currentHealth = this.currentHealth + heal;
		if (this.currentHealth > this.maxHealth) {
			this.currentHealth = this.maxHealth;
		}
	}

}

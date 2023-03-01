package main.dynamicBody.character.enemy;

import main.dynamicBody.bullet.Bullet;

/**
 * Interface that presents all the methods needed or that can be used with
 * regards to the enemy
 */
public interface Enemy extends main.dynamicBody.character.Character {

	/**
	 * Update the position of the enemy
	 */
	public void updatePos();

	/**
	 * @return the TypeEnemy of the enemy
	 */
	public TypeEnemy getTypeEnemy();

	/**
	 * Method use by Enemy.attack to put the bullet in the Set<Bullet>
	 * 
	 * @param bullet, the Bullet to add
	 */
	public void addBullet(Bullet bullet);

	/**
	 * Method use attack with the enemy
	 */
	public void attack();

}

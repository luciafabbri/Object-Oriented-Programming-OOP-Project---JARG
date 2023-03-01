package main.dynamicBody.character.player.shoot;

import org.newdawn.slick.Input;

import main.coordination.SoundBoard;
import main.coordination.SoundBoardFactory;
import main.dynamicBody.bullet.Bullet;
import main.dynamicBody.bullet.BulletPlayerImpl;
import main.dynamicBody.bullet.DistanceBull;
import main.dynamicBody.bullet.TypeBullet;
import main.dynamicBody.character.player.Player;

/**
 * Class that implements interface BulletMovement used to check if the user is
 * pressing the space bar in order to make the player start shooting, by
 * creating a new bullet of type player
 */

public class ShootingPlayerImpl implements ShootingPlayer {

	private Player player;

	/**
	 * Variable used to start counting milliseconds to track the passing of time Set
	 * to zero because the count repeats itself each time the player start pressing
	 * the keyboard
	 */
	private long startMillis = 0;

	/**
	 * Variable used to stop counting milliseconds to track the passing of time If
	 * the difference between start and stop is major than the variable representing
	 * player's rate of fire, the player will be able to shoot repeated bullets
	 * (because space bar it's still pressed)
	 */
	private long stopMillis;

	/**
	 * Default constructor
	 * 
	 * @param player, player that will start shooting
	 */
	public ShootingPlayerImpl(Player player) {
		this.player = player;
	}

	@Override
	public void checkShooting(Input input) {
		stopMillis = System.currentTimeMillis();
		if (input.isKeyDown(Input.KEY_SPACE) && (stopMillis - startMillis > player.getRateOfFire())) {
			this.shoot();
			startMillis = System.currentTimeMillis();
		}
	}

	/**
	 * Method used to make the player start shooting in the direction that he's
	 * going A new BulletPlayerImpl() object is created
	 */
	private void shoot() {
		Bullet bullet = new BulletPlayerImpl(
				DistanceBull.calculateBullPos(player.getDirection(), player, TypeBullet.PLAYER_BULL),
				player.getDamage(), player.getDirection(), player.getRoom());
		if (bullet.isAlive()) {
			player.getRoomBullets().add(bullet);
			SoundBoardFactory.getEntitySound(SoundBoard.mainCharacterShoot);
		}
	}

}

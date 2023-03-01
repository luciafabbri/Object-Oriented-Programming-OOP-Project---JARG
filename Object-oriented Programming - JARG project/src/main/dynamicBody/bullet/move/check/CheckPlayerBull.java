package main.dynamicBody.bullet.move.check;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.SlickException;

import main.coordination.SoundBoard;
import main.dynamicBody.DynamicBody;
import main.dynamicBody.character.enemy.Enemy;
import main.dynamicBody.move.check.CheckPos;
import main.dynamicBody.move.check.CheckPosImpl;
import main.worldModel.RoomModel;
import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.Pair;

/**
 * Class used to represent all the specific checks for a bullet of type player
 */

public class CheckPlayerBull extends CheckPosImpl implements GameSettings, CheckPos {

	private DynamicBody entity;

	/**
	 * Default constructor
	 * 
	 * @param entity, the type of entity associated with this type of bullet's check
	 */
	public CheckPlayerBull(DynamicBody entity) {
		super(entity);
		this.entity = entity;
	}

	@Override
	public boolean possiblePos(RoomModel room, Pair<Integer, Integer> pos) {
		return (!checkEnemy(room, pos) && super.possiblePos(room, pos));
	}

	/**
	 * Method used to check if player's bullet is in collision with any enemy in the
	 * current room
	 * 
	 * @param room, bullet's current room
	 * @param pos,  bullet's current coordinates
	 * @return true if the bullet had a collision with an enemy, otherwise return
	 *         false
	 */
	private boolean checkEnemy(RoomModel room, Pair<Integer, Integer> pos) {
		boolean checkX, checkY;
		Set<Enemy> enemySet = room.getEnemySet();
		for (Enemy enemy : enemySet) {
			checkX = pos.getX() + entity.getDimension().getLeft() < enemy.getPosition().getX()
					+ enemy.getDimension().getRight()
					&& pos.getX() + entity.getDimension().getRight() > enemy.getPosition().getX()
							+ enemy.getDimension().getLeft();
			checkY = pos.getY() + entity.getDimension().getUp() < enemy.getPosition().getY()
					+ enemy.getDimension().getDown()
					&& pos.getY() + entity.getDimension().getDown() > enemy.getPosition().getY();
			if (checkX && checkY) {
				try {
					enemy.takeDamage(this.entity.getDamage());
				} catch (SlickException e) {
					Logger.getLogger(SoundBoard.class.getName()).log(Level.SEVERE, null, e);
				}
				return true;
			}
		}
		return false;
	}

}
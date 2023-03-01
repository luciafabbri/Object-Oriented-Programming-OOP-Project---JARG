package main.dynamicBody.bullet.move.check;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.SlickException;

import main.coordination.SoundBoard;
import main.coordination.init.StateCoord;
import main.dynamicBody.DynamicBody;
import main.dynamicBody.character.player.Player;
import main.dynamicBody.move.check.CheckPos;
import main.dynamicBody.move.check.CheckPosImpl;
import main.worldModel.RoomModel;
import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.Pair;

/**
 * Class used to represent all the specific checks for a bullet of type Enemy
 */

public class CheckMonsBull extends CheckPosImpl implements GameSettings, CheckPos {

	private DynamicBody entity;
	private Player player = StateCoord.getPlayer();

	/**
	 * Default constructor
	 * 
	 * @param entity, the type of entity associated with this type of bullet's check
	 */
	public CheckMonsBull(DynamicBody entity) {
		super(entity);
		this.entity = entity;
	}

	@Override
	public boolean possiblePos(RoomModel room, Pair<Integer, Integer> pos) {
		return super.possiblePos(room, pos) && !checkCharacters(room, pos);
	}

	/**
	 * Method used to check if enemy's bullet is in collision with any enemy in the
	 * current room
	 * 
	 * @param room, bullet's current room
	 * @param pos,  bullet's current coordinates
	 * @return true if the bullet had a collision with the player, otherwise return
	 *         false
	 */
	private boolean checkCharacters(RoomModel room, Pair<Integer, Integer> pos) {
		boolean checkX, checkY;
		checkX = pos.getX() + entity.getDimension().getLeft() < player.getPosition().getX()
				+ player.getDimension().getRight()
				&& pos.getX() + entity.getDimension().getRight() > player.getPosition().getX()
						+ player.getDimension().getLeft();
		checkY = pos.getY() + entity.getDimension().getUp() < player.getPosition().getY()
				+ player.getDimension().getDown()
				&& pos.getY() + entity.getDimension().getDown() > player.getPosition().getY();
		if (checkX && checkY) {
			try {
				player.takeDamage(entity.getDamage());
			} catch (SlickException e) {
				Logger.getLogger(SoundBoard.class.getName()).log(Level.SEVERE, null, e);
			}
			return true;
		}
		return false;
	}
}

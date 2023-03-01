package main.dynamicBody.character.player.movement.check;

import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.Pair;

public class DoorCheck {

	/**
	 * Method that checks for the exact shape of the Door that the Player needs to
	 * go through (Northern door)
	 * 
	 * @param pos, the Player coordinates
	 * @return true if the Player is inside the door boundaries, otherwise false
	 */
	public boolean doorNorth(final Pair<Integer, Integer> pos) {
		return (pos.getX() > (GameSettings.TILESIZE * 9) - 11 && pos.getX() < (GameSettings.TILESIZE * 9) + 11)
				&& (pos.getY() < GameSettings.TILESIZE);
	}

	/**
	 * Method that checks if the Player coordinates are inside the "transition area"
	 * to the next room (Northern door)
	 * 
	 * @param pos, the Player coordinates
	 * @return true if the Player is inside the designated area, otherwise false
	 */
	public boolean transNorth(final Pair<Integer, Integer> pos) {
		return (pos.getX() > (GameSettings.TILESIZE * 9) - 11 && pos.getX() < (GameSettings.TILESIZE * 9) + 11)
				&& (pos.getY() <= -12);
	}

	/**
	 * Method that checks for the exact shape of the Door that the Player needs to
	 * go through (Western door)
	 * 
	 * @param pos, the Player coordinates
	 * @return true if the Player is inside the door boundaries, otherwise false
	 */
	public boolean doorWest(final Pair<Integer, Integer> pos) {
		return (pos.getX() < GameSettings.TILESIZE) && (pos.getY() + 48 > (GameSettings.TILESIZE * 5)
				&& pos.getY() + 48 < (GameSettings.TILESIZE * 5 + 44));
	}

	/**
	 * Method that checks if the Player coordinates are inside the "transition area"
	 * to the next room (Western door)
	 * 
	 * @param pos, the Player coordinates
	 * @return true if the Player is inside the designated area, otherwise false
	 */
	public boolean transWest(final Pair<Integer, Integer> pos) {
		return (pos.getX() < GameSettings.TILESIZE / 4) && (pos.getY() + 48 > (GameSettings.TILESIZE * 5)
				&& pos.getY() + 48 < (GameSettings.TILESIZE * 5 + 44));
	}

	/**
	 * Method that checks for the exact shape of the Door that the Player needs to
	 * go through (Eastern door)
	 * 
	 * @param pos, the Player coordinates
	 * @return true if the Player is inside the door boundaries, otherwise false
	 */
	public boolean doorEast(final Pair<Integer, Integer> pos) {
		return ((pos.getX() > GameSettings.WIDTH - GameSettings.TILESIZE * 2)
				&& (pos.getY() + 48 > (GameSettings.TILESIZE * 5)
						&& pos.getY() + 48 < (GameSettings.TILESIZE * 5 + 44)));
	}

	/**
	 * Method that checks if the Player coordinates are inside the "transition area"
	 * to the next room (Eastern door)
	 * 
	 * @param pos, the Player coordinates
	 * @return true if the Player is inside the designated area, otherwise false
	 */
	public boolean transEast(final Pair<Integer, Integer> pos) {
		return ((pos.getX() > GameSettings.WIDTH - (GameSettings.TILESIZE * 3) / 2 + 15)
				&& (pos.getY() + 48 > (GameSettings.TILESIZE * 5)
						&& pos.getY() + 48 < (GameSettings.TILESIZE * 5 + 44)));
	}

	/**
	 * Method that checks for the exact shape of the Door that the Player needs to
	 * go through (Southern door)
	 * 
	 * @param pos, the Player coordinates
	 * @return true if the Player is inside the door boundaries, otherwise false
	 */
	public boolean doorSouth(final Pair<Integer, Integer> pos) {
		return (pos.getX() > (GameSettings.TILESIZE * 9) - 11 && pos.getX() < (GameSettings.TILESIZE * 9) + 11)
				&& (pos.getY() > GameSettings.HEIGHT - GameSettings.TILESIZE * 2 && pos.getY() < GameSettings.HEIGHT);
	}

	/**
	 * Method that checks if the Player coordinates are inside the "transition area"
	 * to the next room (Southern door)
	 * 
	 * @param pos, the Player coordinates
	 * @return true if the Player is inside the designated area, otherwise false
	 */
	public boolean transSouth(final Pair<Integer, Integer> pos) {
		return (pos.getX() > (GameSettings.TILESIZE * 9) - 11 && pos.getX() < (GameSettings.TILESIZE * 9) + 11)
				&& (pos.getY() > GameSettings.HEIGHT - (GameSettings.TILESIZE * 3) / 2 + 20);
	}
}

package main.tiles;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

public class AnimatedTile {

	/**
	 * Method that returns the animation of the Tile
	 * 
	 * @return Animation
	 * @throws SlickException
	 */
	public static Animation getAnimatedTile(final AnimatedTileImage tile) throws SlickException {
		Animation tmp;
		switch (tile) {
		case DOORNORTH:
			tmp = AnimatedTileImage.DOORNORTH.getAnimatedTileImage();
			break;
		case DOOREAST:
			tmp = AnimatedTileImage.DOOREAST.getAnimatedTileImage();
			break;
		case DOORSOUTH:
			tmp = AnimatedTileImage.DOORSOUTH.getAnimatedTileImage();
			break;
		case DOORWEST:
			tmp = AnimatedTileImage.DOORWEST.getAnimatedTileImage();
			break;
		default:
			throw new IllegalArgumentException("AnimatedTile image not found");
		}
		return tmp;
	}
}

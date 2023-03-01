package main.tiles;

import java.io.IOException;
import java.net.MalformedURLException;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile {

	/**
	 * Method that returns the texture of the Tile
	 * 
	 * @return Image, the texture of the Tile
	 * @throws SlickException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @see SlickException
	 * @see MalformedURLException
	 * @see IOException
	 */
	public static Image getTileTexture(final TileImage image) throws SlickException {
		Image tmp;
		switch (image) {
		case CORNER1:
			tmp = TileImage.CORNER1.getTileImage();
			break;
		case DOORTOP1:
			tmp = TileImage.DOORTOP1.getTileImage();
			break;
		case DOORTOP2:
			tmp = TileImage.DOORTOP2.getTileImage();
			break;
		case FLOOR1:
			tmp = TileImage.FLOOR1.getTileImage();
			break;
		case WALLHOR1:
			tmp = TileImage.WALLHOR1.getTileImage();
			break;
		case WALLHOR2:
			tmp = TileImage.WALLHOR2.getTileImage();
			break;
		default:
			throw new IllegalArgumentException("Tile image not found");
		}
		return tmp;
	}
}

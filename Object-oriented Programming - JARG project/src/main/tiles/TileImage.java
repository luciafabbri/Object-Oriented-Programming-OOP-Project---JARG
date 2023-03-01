package main.tiles;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.coordination.init.LoadNatives;
import main.worldModel.utilities.GameSettings;

public enum TileImage {

	CORNER1(GameSettings.RESPATH + "res" + GameSettings.SEP + "walls" + GameSettings.SEP + "wall_inner_corner_1.png"),
	DOORTOP1(GameSettings.RESPATH + "res" + GameSettings.SEP + "walls" + GameSettings.SEP + "doors" + GameSettings.SEP
			+ "Door_top1.png"),
	DOORTOP2(GameSettings.RESPATH + "res" + GameSettings.SEP + "walls" + GameSettings.SEP + "doors" + GameSettings.SEP
			+ "Door_top2.png"),
	FLOOR1(GameSettings.RESPATH + "res" + GameSettings.SEP + "floor" + GameSettings.SEP + "floor_1.png"),
	WALLHOR1(GameSettings.RESPATH + "res" + GameSettings.SEP + "walls" + GameSettings.SEP + "wall_inner_down_1.png"),
	WALLHOR2(GameSettings.RESPATH + "res" + GameSettings.SEP + "walls" + GameSettings.SEP + "wall_inner_left_1.png");

	String image;

	/**
	 * Default constructor
	 * 
	 * @param String, a string where you can find the spritesheet
	 */
	TileImage(String image) {
		this.image = image;
	}

	/**
	 * Method used to return the image of a Character
	 * 
	 * @return image, of the character
	 * @throws SlickException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public Image getTileImage() throws SlickException {
		try {
			if (!LoadNatives.isJar(TileImage.class.getResource("TileImage.class").toString())) {
				return new Image(new URL("file:///" + image).openStream(), image, false);
			} else {
				return new Image(image);
			}
		} catch (MalformedURLException e) {
			Logger.getLogger(TileImage.class.getName()).log(Level.SEVERE, null, e);
		} catch (SlickException e) {
			Logger.getLogger(TileImage.class.getName()).log(Level.SEVERE, null, e);
		} catch (IOException e) {
			Logger.getLogger(TileImage.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;
	}
}

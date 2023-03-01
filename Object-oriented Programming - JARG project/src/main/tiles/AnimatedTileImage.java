package main.tiles;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.coordination.init.LoadNatives;
import main.worldModel.utilities.GameSettings;

public enum AnimatedTileImage {

	DOORNORTH(GameSettings.RESPATH + "res" + GameSettings.SEP + "walls" + GameSettings.SEP + "doors" + GameSettings.SEP
			+ "door2.png"),
	DOOREAST(GameSettings.RESPATH + "res" + GameSettings.SEP + "walls" + GameSettings.SEP + "doors" + GameSettings.SEP
			+ "door3.png"),
	DOORSOUTH(GameSettings.RESPATH + "res" + GameSettings.SEP + "walls" + GameSettings.SEP + "doors" + GameSettings.SEP
			+ "door4.png"),
	DOORWEST(GameSettings.RESPATH + "res" + GameSettings.SEP + "walls" + GameSettings.SEP + "doors" + GameSettings.SEP
			+ "door1.png");

	String image;

	/**
	 * Default constructor
	 * 
	 * @param String, a string where you can find the spritesheet
	 */
	AnimatedTileImage(String image) {
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
	public Animation getAnimatedTileImage() throws SlickException {
		try {
			if (!LoadNatives.isJar(AnimatedTileImage.class.getResource("AnimatedTileImage.class").toString())) {
				return new Animation(new SpriteSheet(new Image(new URL("file:///" + image).openStream(), image, false),
						GameSettings.TILESIZE, GameSettings.TILESIZE), 100);
			} else {
				return new Animation(new SpriteSheet(new Image(image), GameSettings.TILESIZE, GameSettings.TILESIZE),
						100);
			}
		} catch (MalformedURLException e) {
			Logger.getLogger(AnimatedTileImage.class.getName()).log(Level.SEVERE, null, e);
		} catch (SlickException e) {
			Logger.getLogger(AnimatedTileImage.class.getName()).log(Level.SEVERE, null, e);
		} catch (IOException e) {
			Logger.getLogger(AnimatedTileImage.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;
	}
}

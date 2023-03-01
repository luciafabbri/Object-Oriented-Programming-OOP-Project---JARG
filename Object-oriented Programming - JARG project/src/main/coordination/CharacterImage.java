package main.coordination;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.coordination.init.LoadNatives;
import main.worldModel.utilities.GameSettings;

public enum CharacterImage {

	FRONT_PLAYER(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "mainChar6_front.png"),
	BACK_PLAYER(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "mainChar6_back.png"),
	LEFT_PLAYER(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "mainChar6_left.png"),
	RIGHT_PLAYER(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "mainChar6_right.png"),

	FRONT_BOWMAN(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Enemy1_front.png"),
	BACK_BOWMAN(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Enemy1_back.png"),
	LEFT_BOWMAN(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Enemy1_left.png"),
	RIGHT_BOWMAN(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Enemy1_right.png"),

	FRONT_MAGE(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Enemy3_front.png"),
	BACK_MAGE(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Enemy3_back.png"),
	LEFT_MAGE(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Enemy3_left.png"),
	RIGHT_MAGE(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Enemy3_right.png"),

	FRONT_NINJA(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Enemy2_front.png"),
	BACK_NINJA(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Enemy2_back.png"),
	LEFT_NINJA(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Enemy2_left.png"),
	RIGHT_NINJA(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Enemy2_right.png"),

	PLANT(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies" + GameSettings.SEP
			+ "Plan.png"),

	FRONT_BOSS(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Boss_front.png"),
	BACK_BOSS(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Boss_back.png"),
	LEFT_BOSS(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Boss_left.png"),
	RIGHT_BOSS(GameSettings.RESPATH + "res" + GameSettings.SEP + "chars" + GameSettings.SEP + "enemies"
			+ GameSettings.SEP + "Boss_right.png"),

	PLAYER_BULLET(GameSettings.RESPATH + "res" + GameSettings.SEP + "proj" + GameSettings.SEP + "MainProj.png"),

	ENEMY_BULLET(GameSettings.RESPATH + "res" + GameSettings.SEP + "proj" + GameSettings.SEP + "EnemyProj.png");

	String image;

	/**
	 * Default constructor
	 * 
	 * @param String, a string where you can find the spritesheet
	 */
	CharacterImage(String image) {
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
	public Image getImage() throws SlickException {
		try {
			if (!LoadNatives.isJar(CharacterImage.class.getResource("CharacterImage.class").toString())) {
				return new Image(new URL("file:///" + image).openStream(), image, false);
			} else {
				return new Image(image);
			}
		} catch (MalformedURLException e) {
			Logger.getLogger(CharacterImage.class.getName()).log(Level.SEVERE, null, e);
		} catch (SlickException e) {
			Logger.getLogger(CharacterImage.class.getName()).log(Level.SEVERE, null, e);
		} catch (IOException e) {
			Logger.getLogger(CharacterImage.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;
	}
}

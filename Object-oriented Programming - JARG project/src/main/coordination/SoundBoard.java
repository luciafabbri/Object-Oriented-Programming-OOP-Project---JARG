package main.coordination;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import main.coordination.init.LoadNatives;
import main.worldModel.utilities.GameSettings;

/**
 * Enum listing the texture for each GameEntity object
 *
 */
public enum SoundBoard {

	mainCharacterHurt(GameSettings.RESPATH + "res" + GameSettings.SEP + "audio" + GameSettings.SEP + "mainChar"
			+ GameSettings.SEP + "hurtSound.ogg"),
	mainCharacterShoot(GameSettings.RESPATH + "res" + GameSettings.SEP + "audio" + GameSettings.SEP + "bow"
			+ GameSettings.SEP + "bow_fired.ogg"),
	enemyDamageHurt(GameSettings.RESPATH + "res" + GameSettings.SEP + "audio" + GameSettings.SEP + "enemy"
			+ GameSettings.SEP + "takeDamage.ogg"),
	mainCharacterFootsteps(GameSettings.RESPATH + "res" + GameSettings.SEP + "audio" + GameSettings.SEP + "footsteps"
			+ GameSettings.SEP + "footsteps.ogg"),
	keyPickUp(GameSettings.RESPATH + "res" + GameSettings.SEP + "audio" + GameSettings.SEP + "pickups"
			+ GameSettings.SEP + "keyPickup.ogg"),
	coinPickUp(GameSettings.RESPATH + "res" + GameSettings.SEP + "audio" + GameSettings.SEP + "pickups"
			+ GameSettings.SEP + "coinPickup.ogg"),
	modPickUp(GameSettings.RESPATH + "res" + GameSettings.SEP + "audio" + GameSettings.SEP + "pickups"
			+ GameSettings.SEP + "modifierPickup.ogg"),
	doorOpen(GameSettings.RESPATH + "res" + GameSettings.SEP + "audio" + GameSettings.SEP + "doors" + GameSettings.SEP
			+ "door_open.ogg"),
	ominousMusic(GameSettings.RESPATH + "res" + GameSettings.SEP + "audio" + GameSettings.SEP + "music"
			+ GameSettings.SEP + "Ominous_Music.ogg");

	String sound;

	SoundBoard(String image) {
		this.sound = image;
	}

	/**
	 * Method used to get the chosen sound
	 * 
	 * @return Sound
	 * @throws SlickException
	 * @throws MalformedURLException
	 * @see SlickException
	 * @see MalformedURLException
	 */
	public Sound getSound() throws SlickException {
		try {
			if (!LoadNatives.isJar(SoundBoard.class.getResource("SoundBoard.class").toString())) {
				return new Sound(new URL("file:///" + sound));
			} else {
				return new Sound(sound);
			}
		} catch (MalformedURLException e) {
			Logger.getLogger(SoundBoard.class.getName()).log(Level.WARNING, null, e);
		} catch (SlickException e) {
			Logger.getLogger(SoundBoard.class.getName()).log(Level.WARNING, null, e);
		}
		return null;
	}

}

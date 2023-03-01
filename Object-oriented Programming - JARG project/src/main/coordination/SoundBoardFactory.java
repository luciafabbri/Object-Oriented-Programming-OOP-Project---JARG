package main.coordination;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundBoardFactory {

	/**
	 * Variable containing the footsteps to dynamically check if they're being
	 * played
	 */
	private static Sound footsteps;

	/**
	 * Method used to retrieve the footsteps sound
	 * 
	 * @throws SlickException
	 * @see SlickException
	 */
	public static void storeSound() throws SlickException {
		SoundBoardFactory.footsteps = SoundBoard.mainCharacterFootsteps.getSound();
	}

	/**
	 * Method used to get and play sounds
	 * 
	 * @param sound
	 */
	public static void getEntitySound(final SoundBoard sound) {
		Sound tmpEnt = null;
		switch (sound) {
		case mainCharacterHurt:
			try {
				tmpEnt = SoundBoard.mainCharacterHurt.getSound();
			} catch (SlickException e) {
				Logger.getLogger(SoundBoard.class.getName()).log(Level.WARNING, null, e);
			}
			break;
		case mainCharacterShoot:
			try {
				tmpEnt = SoundBoard.mainCharacterShoot.getSound();
			} catch (SlickException e) {
				Logger.getLogger(SoundBoard.class.getName()).log(Level.WARNING, null, e);
			}
			break;
		case enemyDamageHurt:
			try {
				tmpEnt = SoundBoard.enemyDamageHurt.getSound();
			} catch (SlickException e) {
				Logger.getLogger(SoundBoard.class.getName()).log(Level.WARNING, null, e);
			}
			break;
		case keyPickUp:
			try {
				tmpEnt = SoundBoard.keyPickUp.getSound();
			} catch (SlickException e) {
				Logger.getLogger(SoundBoard.class.getName()).log(Level.WARNING, null, e);
			}
			break;
		case coinPickUp:
			try {
				tmpEnt = SoundBoard.coinPickUp.getSound();
			} catch (SlickException e) {
				Logger.getLogger(SoundBoard.class.getName()).log(Level.WARNING, null, e);
			}
			break;
		case modPickUp:
			try {
				tmpEnt = SoundBoard.modPickUp.getSound();
			} catch (SlickException e) {
				Logger.getLogger(SoundBoard.class.getName()).log(Level.WARNING, null, e);
			}
			break;
		case doorOpen:
			try {
				tmpEnt = SoundBoard.doorOpen.getSound();
			} catch (SlickException e) {
				Logger.getLogger(SoundBoard.class.getName()).log(Level.WARNING, null, e);
			}
			break;
		default:
			throw new IllegalArgumentException("Sound is missing");
		}

		playSound(tmpEnt);
	}

	/**
	 * Method used to play sounds
	 * 
	 * @param tmp, the chosen sound
	 */
	private static void playSound(final Sound tmp) {
		if (!tmp.playing())
			tmp.play(1.0f, 0.4f);
	}

	/**
	 * Method used to only play footsteps dynamically
	 */
	public static void playFootsteps() {
		if (!footsteps.playing())
			footsteps.play(1.0f, 0.4f);
	}

	public static void loopMusic() {
		try {
			SoundBoard.ominousMusic.getSound().loop(1.0f, 0.04f);
		} catch (SlickException e) {
			Logger.getLogger(SoundBoard.class.getName()).log(Level.WARNING, null, e);
		}
	}

}

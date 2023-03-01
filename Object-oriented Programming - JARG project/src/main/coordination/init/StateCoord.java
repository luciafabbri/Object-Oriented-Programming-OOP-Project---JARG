package main.coordination.init;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import main.coordination.UIs.Menu;
import main.coordination.maingame.GameController;
import main.dynamicBody.character.player.Player;
import main.dynamicBody.character.player.PlayerImpl;
import main.dynamicBody.move.Direction;
import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.Pair;

public class StateCoord extends StateBasedGame {

	/**
	 * Variable containing initializing of Player
	 */
	private static Player player;
	/**
	 * Variable containing the name of the Window
	 */
	private static final String GAMENAME = "JARG";
	/**
	 * Variable containing the ID for the first level
	 */
	private static final int LEVEL1 = 1;

	/**
	 * Constructor for StateCoord
	 * 
	 * @param name, which is the name of the Window
	 * @throws SlickException
	 * @see SlickException
	 */
	public StateCoord(String name) throws SlickException {
		super(name);
		StateCoord.player = new PlayerImpl(new Pair<>(GameSettings.TILESIZE, GameSettings.TILESIZE), Direction.SOUTH,
				0);

		this.addState(new Menu());
		this.addState(new GameController(LEVEL1, player));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
//		DOCUMENTATION SAYS THAT INITSTATES IS BROKEN
	}

	/**
	 * Main method for initializing the OpenGL context of the game, and the Player
	 * resource itself
	 * 
	 * @param args
	 * @throws SlickException
	 * @throws IOException
	 * @see SlickException
	 * @see IOException
	 */
	public static void main(String[] args) throws SlickException, IOException {
		LoadNatives.loadLibs();

		String destPath;

		if (!LoadNatives.isJar(StateCoord.class.getResource("StateCoord.class").toString())) {
			if (LoadNatives.isWindows()) {
				destPath = System.getProperty("java.io.tmpdir") + "jarg" + GameSettings.SEP + "libJars";
			} else {
				destPath = System.getProperty("java.io.tmpdir") + File.separator + "jarg" + GameSettings.SEP + "libJars"
						+ File.separator;
			}
		} else {
			destPath = "." + GameSettings.SEP + "lib" + GameSettings.SEP + "libJars";
		}

		// Set the path for Slick2D libraries
		System.setProperty("java.library.path", new File(destPath).getAbsolutePath());

		// Set the path for Slick2D natives
		System.setProperty("org.lwjgl.librarypath", new File(destPath).getAbsolutePath());

		// Set the path for JInput
		System.setProperty("net.java.games.input.librarypath", new File(destPath).getAbsolutePath());

		LoadNatives.disableAccessWarning();

		System.out.println();
		
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new StateCoord(GAMENAME));
			appgc.setDisplayMode(GameSettings.WIDTH, GameSettings.HEIGHT, false);
			appgc.setVSync(true);
			appgc.setSmoothDeltas(true);
			appgc.setShowFPS(false);
			appgc.setMaximumLogicUpdateInterval(7);
			appgc.setMinimumLogicUpdateInterval(10);
			appgc.start();
		} catch (SlickException e) {
			Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	/**
	 * Method used by the enemies to get reference of Player
	 * 
	 * @return Player
	 */
	public static Player getPlayer() {
		return player;
	}
}

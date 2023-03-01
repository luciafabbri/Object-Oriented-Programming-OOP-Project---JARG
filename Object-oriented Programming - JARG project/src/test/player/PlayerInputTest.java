package test.player;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import main.coordination.SoundBoardFactory;
import main.dynamicBody.character.player.Player;
import main.dynamicBody.character.player.PlayerImpl;
import main.dynamicBody.move.Direction;
import main.levels.LevelComp;
import main.levels.LevelCompImpl;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

/**
 * Class used to create a test for input using Slick2D library and update
 * player's current coordinates and position
 */

public class PlayerInputTest extends BasicGame {

	@SuppressWarnings("unused")
	private AppGameContainer app;
	private Input input;

	private static Player testPlayer;
	private static LevelComp testLevel;
	private static RoomModel testRoom;

	/** Input variables to check, true if pressed */
	private boolean space;
	private boolean inputA;
	private boolean inputW;
	private boolean inputD;
	private boolean inputS;

	private boolean dirA;
	private boolean dirW;
	private boolean dirD;
	private boolean dirS;

	/**
	 * Default constructor of this test class
	 */
	public PlayerInputTest() {
		super(" INPUT TEST ");
	}

	public void init(GameContainer container) throws SlickException {
		SoundBoardFactory.storeSound();

		if (container instanceof AppGameContainer) {
			app = (AppGameContainer) container;
		}
		input = container.getInput();
		try {
			testLevel = new LevelCompImpl(1);
		} catch (IOException e) {
			Logger.getLogger(LevelComp.class.getName()).log(Level.SEVERE, null, e);
		}
		testRoom = testLevel.getLevel().get(0).getRoom();
		testPlayer = new PlayerImpl(new Pair<>(128, 128), Direction.SOUTH, testRoom.getRoomID());
		testPlayer.setCurrentRoom(testRoom);
	}

	public void render(GameContainer container, Graphics g) {
		g.drawString("Premere i tasti 'W' 'A' 'S' 'D' per verificare il movimento del player", 10, 30);
		g.drawString("Premere la 'space bar' per verificare l'azione di attacco del player", 10, 50);

		g.drawString("Il player sta sparando: " + space, 10, 100);

		g.drawString("KEY UP (W): " + inputW, 10, 150);
		g.drawString("NORTH: " + dirW, 10, 170);

		g.drawString("KEY DOWN (S): " + inputS, 10, 210);
		g.drawString("SOUTH: " + dirS, 10, 230);

		g.drawString("KEY LEFT (A): " + inputA, 10, 270);
		g.drawString("WEST: " + dirA, 10, 290);

		g.drawString("KEY RIGHT (D): " + inputD, 10, 330);
		g.drawString("EAST: " + dirD, 10, 350);

		g.drawString("Posizione corrente del player", 10, 400);
		g.drawString("X: " + testPlayer.getPosition().getX() + " | Y: " + testPlayer.getPosition().getY(), 10, 420);
		g.drawString("Direzione corrente del player:  " + testPlayer.getDirection(), 10, 440);
	}

	public void update(GameContainer container, int delta) throws SlickException {
		space = container.getInput().isKeyDown(Input.KEY_SPACE);

		inputA = container.getInput().isKeyDown(Input.KEY_A);
		inputW = container.getInput().isKeyDown(Input.KEY_W);
		inputD = container.getInput().isKeyDown(Input.KEY_D);
		inputS = container.getInput().isKeyDown(Input.KEY_S);

		dirA = container.getInput().isKeyDown(Input.KEY_A);
		dirW = container.getInput().isKeyDown(Input.KEY_W);
		dirD = container.getInput().isKeyDown(Input.KEY_D);
		dirS = container.getInput().isKeyDown(Input.KEY_S);

		testPlayer.setPosition(input, testLevel);
		testPlayer.getShootingBullet().checkShooting(input);
	}

	/**
	 * This is the entry point to the test
	 * 
	 * @param argv, arguments passed into the test
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new PlayerInputTest());
			container.setDisplayMode(800, 600, false);
			container.start();
		} catch (SlickException e) {
			Logger.getLogger(AppGameContainer.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}

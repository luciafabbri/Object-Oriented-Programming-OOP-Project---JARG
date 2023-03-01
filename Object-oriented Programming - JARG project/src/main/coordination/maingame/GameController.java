package main.coordination.maingame;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.coordination.SoundBoardFactory;
import main.coordination.UIs.UI;
import main.coordination.UIs.UILogic;
import main.dynamicBody.character.player.Player;
import main.levels.LevelComp;
import main.levels.LevelCompImpl;
import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.Pair;

public class GameController extends BasicGameState {

	/**
	 * Variable that contains the data of the Player
	 */
	private Player player;
	/**
	 * Variable containing the Graphics class
	 */
	private GameView graphics;
	/**
	 * Variable containing the Logic class
	 */
	private ModelCommunicator logic;
	/**
	 * Variable containing the UI class
	 */
	private UI ui;
	/**
	 * Variable containing the UI logic
	 */
	private UILogic uilogic;
	/**
	 * Variable containing the Level class
	 */
	private LevelComp level;
	/**
	 * Variable containing which input is received from every computer controller
	 */
	private Input input;
	/**
	 * Variable containing the current level being displayed
	 */
	private int levelID;

	/**
	 * Constructor to build initial level
	 * 
	 * @param state,  indicates which level to load first
	 * @param player, indicates the Player environment to keep track of
	 * @throws SlickException
	 * @see SlickException
	 */
	public GameController(final int state, final Player player) throws SlickException {
		super();
		this.levelID = state;
		this.player = player;
		SoundBoardFactory.storeSound();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {

		try {
			level = new LevelCompImpl(this.levelID);
		} catch (IOException e) {
			Logger.getLogger(Level.class.getName()).log(Level.SEVERE, null, e);
		}
		logic = new ModelCommunicatorImpl(level, player, arg1, arg0, this);

		player.setCurrentRoom(level.getLevel().get(0).getRoom());
		player.transitionPos(new Pair<>(GameSettings.WIDTH / 2 - GameSettings.TILESIZE, GameSettings.TILESIZE));

		graphics = new GameViewImpl(level, player);

		uilogic = new UILogic(player, level, arg0, this, arg1, player);
		ui = new UI(player, arg0.getGraphics(), level, uilogic);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		input = arg0.getInput();

		graphics.render(input);

		ui.drawUI();

//		arg2.drawString("X: " + player.getPosition().getX() + " | Y: " +player.getPosition().getY(), GameSettings.WIDTH - GameSettings.TILESIZE * 5, 0);
//		arg2.drawString("Level: " + this.getID() + " | Room: " +level.getRoomID(), GameSettings.WIDTH - GameSettings.TILESIZE * 5, 16);
//		arg2.drawString("Stairs: " + level.getLevel().get(level.getRoomID()).getRoom().areStairsPresent(), GameSettings.WIDTH - GameSettings.TILESIZE * 5, 32);

		arg2.clearClip();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		input = arg0.getInput();
		uilogic.update();

		logic.update();
	}

	/**
	 * Method that returns current level
	 * 
	 * @return int, that indicates which current level is loaded
	 */
	public int getID() {
		return levelID;
	}

	/**
	 * Method that sets the ID for the current level to be played
	 * 
	 * @param levelID, an int that specifies the level being played
	 */
	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}
}

package main.coordination.UIs;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import main.coordination.SoundBoardFactory;
import main.coordination.maingame.GameController;
import main.dynamicBody.character.player.Player;
import main.levels.LevelComp;
import main.worldModel.utilities.GameSettings;

public class UILogic {

	/**
	 * Variable containing the boolean for whether the cursor is over the Play
	 * button or not
	 */
	private boolean hoverButtonPlay;
	/**
	 * Variable containing the boolean for whether the cursor is over the Quit
	 * button or not
	 */
	private boolean hoverButtonQuit;
	/**
	 * Variable containing the data for the GameContainer
	 */
	private GameContainer game;
	/**
	 * Variable containing the data of the current level
	 */
	private LevelComp level;
	/**
	 * Variable containing the boolean for the fade effect
	 */
	private boolean firstStop;
	/**
	 * Variable containing the boolean for the fade effect
	 */
	private boolean secondStop;
	/**
	 * Variable containing the boolean for the fade effect
	 */
	private boolean thirdStop;
	/**
	 * Variable containing the milliseconds to check how much time has passed
	 */
	private long timeNow;
	/**
	 * Variable containing the milliseconds to check how much time has passed
	 */
	private long timeEnd;
	/**
	 * Variable containing the int passed to the UI to make the fade effect
	 */
	private int index;
	/**
	 * Variable containing the data of the GameController
	 */
	private GameController gameController;
	/**
	 * Variable containing the data of the StateBasedGame
	 */
	private StateBasedGame state;
	/**
	 * Variable containing the data of the Player
	 */
	private Player player;

	/**
	 * Variable containing the amount of time for fading in
	 */
	private int fadeIn;
	/**
	 * Variable containing the amount of time for fading out
	 */
	private int fadeOut;

	/**
	 * Public constructor for UILogic
	 * 
	 * @param player,         the player data
	 * @param level,          the current level data
	 * @param game,           the GameContainer data
	 * @param gameController, the GameController data
	 * @param state,          the StateBasedGame data
	 * @param playerTmp,      the Player data
	 * @throws SlickException
	 * @see SlickException
	 */
	public UILogic(final Player player, final LevelComp level, final GameContainer game,
			final GameController gameController, final StateBasedGame state, final Player playerTmp)
			throws SlickException {
		this.game = game;
		this.level = level;
		this.gameController = gameController;
		this.state = state;
		this.player = playerTmp;

		this.firstStop = true;
		this.secondStop = false;
		this.thirdStop = false;

		this.timeEnd = 0;
		this.index = 0;
		this.fadeIn = 2;
		this.fadeOut = 2;
	}

	/**
	 * Method used to check if the game has been won
	 * 
	 * @throws SlickException
	 * @see SlickException
	 */
	public void update() throws SlickException {

		if (level.isGameWon())
			this.wonMenuUpdate();
	}

	/**
	 * Method used to coordinates the logic for the 'Won' screen
	 * 
	 * @throws SlickException
	 * @see SlickException
	 */
	private void wonMenuUpdate() throws SlickException {
		int x = Mouse.getX();
		int y = Mouse.getY();

		if ((x > (GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8)
				&& x < (GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8 + 240))
				&& (y < (GameSettings.HEIGHT / 2 - 120) && y > (GameSettings.HEIGHT / 2 - 160))) {
			hoverButtonPlay = true;
			if (game.getInput().isMousePressed(0)) {
				player.resetStats();
				SoundBoardFactory.storeSound();
				gameController.setLevelID(1);
				state.init(game);
				state.enterState(0);
			}
		} else {
			hoverButtonPlay = false;
		}

		if ((x > (GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8)
				&& x < (GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8 + 240))
				&& (y < (GameSettings.HEIGHT / 2 - 220) && y > (GameSettings.HEIGHT / 2 - 260))) {
			hoverButtonQuit = true;
			if (game.getInput().isMousePressed(0)) {
				game.exit();
			}
		} else {
			hoverButtonQuit = false;
		}
	}

	/**
	 * Method used to check the fade for the coin text
	 * 
	 * @return int, the index of the alpha channel
	 */
	public int congratsTextEachCoin() {
		timeNow = System.currentTimeMillis();

		if (this.firstStop) {

			if (timeNow - timeEnd > fadeIn) {
				index++;
				timeEnd = System.currentTimeMillis();

				if (index >= 255) {
					this.firstStop = false;
					this.secondStop = true;
				}

			}
		} else if (this.secondStop) {

			if (timeNow - timeEnd > fadeOut + 1) {
				this.secondStop = false;
				this.thirdStop = true;
				timeEnd = System.currentTimeMillis();
			}

		} else if (this.thirdStop) {

			if (timeNow - timeEnd > fadeIn) {
				index--;
				timeEnd = System.currentTimeMillis();

				if (index <= 0) {
					this.thirdStop = false;
				}

			}
		}

		return index;
	}

	/**
	 * Method used to check the fade for the won screen
	 * 
	 * @return int, the index of the alpha channel
	 */
	public int fadeWonMenu() {
		timeNow = System.currentTimeMillis();

		if (this.firstStop) {
			if (timeNow - timeEnd > fadeIn - 1) {
				index++;
				timeEnd = System.currentTimeMillis();

				if (index >= 255) {
					this.firstStop = false;
				}
			}
		}
		return index;
	}

	/**
	 * Method used to check if the cursor hovers over the Play button, or not
	 * 
	 * @return boolean
	 */
	public boolean isHoverButtonPlay() {
		return hoverButtonPlay;
	}

	/**
	 * Method used to check if the cursor hovers over the Quit button, or not
	 * 
	 * @return boolean
	 */
	public boolean isHoverButtonQuit() {
		return hoverButtonQuit;
	}

}

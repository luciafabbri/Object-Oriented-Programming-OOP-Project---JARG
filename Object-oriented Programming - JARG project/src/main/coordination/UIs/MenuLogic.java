package main.coordination.UIs;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import main.coordination.SoundBoardFactory;
import main.worldModel.utilities.GameSettings;

public class MenuLogic {
	/**
	 * Variable containing which input has been given from any controller
	 */
	private Input input;
	/**
	 * Variable containing the current X coordinates of the mouse cursor
	 */
	private int x;
	/**
	 * Variable containing the current Y coordinates of the mouse cursor
	 */
	private int y;
	/**
	 * Variable that is true if the mouse cursor is on the Start button, otherwise
	 * false
	 */
	private boolean hoverButtonStart;
	/**
	 * Variable that is true if the mouse cursor is on the End button, otherwise
	 * false
	 */
	private boolean hoverButtonEnd;
	/**
	 * Variable that is true if the mouse cursor is on the Tutorial button,
	 * otherwise false
	 */
	private boolean hoverButtonTutorial;
	/**
	 * Variable that is true if the tutorial screen is selected
	 */
	private boolean tutorialScreen;
	/**
	 * Variable that is true if the mouse cursor is on the Back button, otherwise
	 * false
	 */
	private boolean hoverButtonBack;
	/**
	 * Variable containing the GameContainer data
	 */
	private GameContainer game;
	/**
	 * Variable containing the GameContainer data
	 */
	private StateBasedGame state;

	/**
	 * Public constructor for MenuLogic
	 * 
	 * @param arg0, contains GameContainer data
	 * @param arg1, contains StateBasedGmame data
	 * @throws SlickException
	 * @see SlickException
	 */
	public MenuLogic(final GameContainer arg0, final StateBasedGame arg1) throws SlickException {
		this.game = arg0;
		this.state = arg1;

		hoverButtonStart = false;
		hoverButtonEnd = false;
		this.hoverButtonTutorial = false;
		this.tutorialScreen = false;
		this.hoverButtonBack = false;
	}

	/**
	 * Method that returns true if the cursor is over the Start button, otherwise
	 * false
	 * 
	 * @return boolean
	 */
	public boolean isHoverButtonStart() {
		return hoverButtonStart;
	}

	/**
	 * Method that returns true if the cursor is over the End button, otherwise
	 * false
	 * 
	 * @return boolean
	 */
	public boolean isHoverButtonEnd() {
		return hoverButtonEnd;
	}

	/**
	 * Method that returns true if the cursor is over the Tutorial button, otherwise
	 * false
	 * 
	 * @return boolean
	 */
	public boolean isHoverButtonTutorial() {
		return hoverButtonTutorial;
	}

	/**
	 * Method that returns true if the cursor is over the Back button, otherwise
	 * false
	 * 
	 * @return boolean
	 */
	public boolean isHoverButtonBack() {
		return hoverButtonBack;
	}

	/**
	 * Method that returns true if the Tutorial button has been pressed, otherwise
	 * false
	 * 
	 * @return boolean
	 */
	public boolean isTutorialScreen() {
		return tutorialScreen;
	}

	/**
	 * Method that coordinates the logic of the buttons of the main menu
	 * 
	 * @throws SlickException
	 * @see SlickException
	 */
	public void mainMenuUpdate() throws SlickException {
		x = Mouse.getX();
		y = Mouse.getY();

		input = game.getInput();

		if ((x > (GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8)
				&& x < (GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8 + 240))
				&& (y < ((GameSettings.HEIGHT / 2) - 20) && y > ((GameSettings.HEIGHT / 2) - 60))) {
			hoverButtonStart = true;
			if (input.isMousePressed(0)) {
				SoundBoardFactory.loopMusic();
				state.enterState(1);
			}
		} else {
			hoverButtonStart = false;
		}

		if ((x > (GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8)
				&& x < (GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8 + 240))
				&& (y < (GameSettings.HEIGHT / 2 - 120) && y > (GameSettings.HEIGHT / 2 - 160))) {
			hoverButtonEnd = true;
			if (input.isMousePressed(0)) {
				game.exit();
			}
		} else {
			hoverButtonEnd = false;
		}

		if ((x > (GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8)
				&& x < (GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8 + 240))
				&& (y < (GameSettings.HEIGHT / 2 - 220) && y > (GameSettings.HEIGHT / 2 - 260))) {
			hoverButtonTutorial = true;
			if (input.isMousePressed(0)) {
				tutorialScreen = true;
			}
		} else {
			hoverButtonTutorial = false;
		}
	}

	/**
	 * Method that coordinates the logic of the tutorial screen
	 */
	public void tutorialMenuUpdate() {
		x = Mouse.getX();
		y = Mouse.getY();

		input = game.getInput();

		int width = GameSettings.TILESIZE;
		int height = (GameSettings.TILESIZE / 2) + 60;

		if ((x > width && x < width + 240) && ((y < (height + 45) + 40 && y > height + 45))) {
			hoverButtonBack = true;
			if (input.isMousePressed(0)) {
				tutorialScreen = false;
			}
		} else {
			hoverButtonBack = false;
		}
	}

}

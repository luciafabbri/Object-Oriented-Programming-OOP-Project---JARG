package main.coordination.UIs;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.worldModel.utilities.GameSettings;

public class Menu extends BasicGameState {

	/**
	 * Variable containing the logics of the menu
	 */
	private MenuLogic logic;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(final GameContainer arg0, final StateBasedGame arg1) throws SlickException {
		this.logic = new MenuLogic(arg0, arg1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		// Here I will render the main screen via buttons
		arg2.setColor(Color.black);
		arg2.drawRect(0, 0, GameSettings.WIDTH, GameSettings.HEIGHT);

		if (!logic.isTutorialScreen()) {
			this.mainMenu(arg2);
		} else {
			this.tutorialMenu(arg2);
		}

	}

	/**
	 * Method that displays the Tutorial screen
	 * 
	 * @param arg2, the graphics variable to print the screen
	 */
	private void tutorialMenu(final Graphics arg2) {
		arg2.setColor(Color.white);

		int width = GameSettings.TILESIZE;
		int height = (GameSettings.TILESIZE / 2) + 60;

		arg2.drawString("You are a Knight Archer, on your quest to defeat the evil Knight Darklord.", width, height);
		arg2.drawString(
				"To play the game, use the WASD keys to move the Archer in the four cardinal directions (North, West, East and South).",
				width, height + 50);
		arg2.drawString("And to pause your adventure, press the Escape button.", width, height + 50 * 2);
		arg2.drawString("In each room you will found many objects:", width, height + 50 * 3);
		arg2.drawString(
				"-Enemies, that will try to defeat you, use your bow and arrow via the Space key to annihilate them!",
				width, height + 50 * 4);
		arg2.drawString("-Keys, that you need to get in order to open the doors to the next room!", width,
				height + 50 * 5);
		arg2.drawString(
				"-Upgrades, that your Hero can pick up in order to get Stronger, Faster and more Resilient to damage.",
				width, height + 50 * 6);
		arg2.drawString("-Coins, ancient relics stolen by the Darklord from your family Crypt.", width,
				height + 50 * 7);
		arg2.drawString(
				"Now you have everything you need to know to defeat Evil and bring peace to your family, Good Luck!.",
				width, height + 50 * 8);

		if (logic.isHoverButtonBack()) {
			arg2.setColor(Color.gray);
		} else {
			arg2.setColor(Color.white);
		}
		arg2.fillRect(width, height + 50 * 10, 240, 40);

		arg2.setColor(Color.black);
		arg2.drawString("Back", width + 100, (height + 50 * 10) + 10);
	}

	/**
	 * Method that displays the main menu screen
	 * 
	 * @param arg2, the graphics variable to print the screen
	 */
	private void mainMenu(final Graphics arg2) {
		if (logic.isHoverButtonStart()) {
			arg2.setColor(Color.gray);
		} else {
			arg2.setColor(Color.white);
		}
		arg2.fillRect(GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8, GameSettings.HEIGHT / 2 + 20, 240, 40);

		if (logic.isHoverButtonEnd()) {
			arg2.setColor(Color.gray);
		} else {
			arg2.setColor(Color.white);
		}
		arg2.fillRect(GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8, GameSettings.HEIGHT / 2 + 120, 240, 40);

		if (logic.isHoverButtonTutorial()) {
			arg2.setColor(Color.gray);
		} else {
			arg2.setColor(Color.white);
		}
		arg2.fillRect(GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8, GameSettings.HEIGHT / 2 + 220, 240, 40);

		arg2.setColor(Color.black);
		arg2.drawString("Start Game", (GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8) + 70,
				(GameSettings.HEIGHT / 2) + 30);

		arg2.setColor(Color.black);
		arg2.drawString("Quit Game", (GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8) + 70,
				(GameSettings.HEIGHT / 2) + 130);

		arg2.setColor(Color.black);
		arg2.drawString("How to Play", (GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8) + 70,
				(GameSettings.HEIGHT / 2) + 230);

		arg2.scale(2, 2);

		arg2.setColor(Color.white);
		arg2.drawString("JARG", GameSettings.WIDTH / 4 - 40, 60);
		arg2.drawString("Just Another RogueLike Game", GameSettings.WIDTH / 4 - 130, 100);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if (!logic.isTutorialScreen()) {
			logic.mainMenuUpdate();
		} else {
			logic.tutorialMenuUpdate();
			;
		}
	}

	/**
	 * Method that isn't used, must have because of inheritance
	 */
	@Override
	public int getID() {
		return 0;
	}

}

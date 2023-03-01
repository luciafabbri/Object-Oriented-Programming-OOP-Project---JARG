package main.coordination.UIs;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import main.dynamicBody.character.player.Player;
import main.gameEntities.EntityImageFactory;
import main.levels.LevelComp;
import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.enums.Entities;

public class UI {

	/**
	 * Variable containing data of current Player environment
	 */
	private Player player;
	/**
	 * Variable containing data regarding the Level
	 */
	private LevelComp level;
	/**
	 * Variable containing data of current Graphics environment
	 */
	private Graphics graphics;
	/**
	 * Variable containing data of the Font used for the UI
	 */
	private Font font;
	/**
	 * Variable containing data of TrueTypeFont to build Font
	 */
	private TrueTypeFont tmp;
	/**
	 * Variable containing data to choose Color (in this case white)
	 */
	private Color color;
	/**
	 * Variable containing data to choose the Color for the emerging text
	 */
	private Color textGeneral;
	/**
	 * Variable containing data to choose the Color for the background while paused
	 */
	private Color pauseBackground;
	/**
	 * Variable containing data of the UI logic
	 */
	private UILogic logic;

	/**
	 * Constructor for UI
	 * 
	 * @param level
	 * @param arg0
	 * @param player,   containing data of current Player
	 * @param graphics, containing data of current Graphics
	 * @throws SlickException
	 * @see SlickException
	 */
	public UI(final Player player, final Graphics graphics, final LevelComp level, final UILogic logic)
			throws SlickException {
		this.player = player;
		this.level = level;
		this.graphics = graphics;
		this.logic = logic;

		this.font = new Font("Default", Font.ROMAN_BASELINE, 30);
		this.tmp = new TrueTypeFont(font, false);

		this.textGeneral = new Color(255, 255, 255, 0);
		this.color = new Color(72, 59, 58, 75);
		this.pauseBackground = new Color(0, 0, 0, 150);

	}

	/**
	 * Method used to draw each element of the UI
	 * 
	 * @throws SlickException
	 * @see SlickException
	 */
	public void drawUI() throws SlickException {
		graphics.setColor(color);

		graphics.fillRect(0, 0, GameSettings.TILESIZE, GameSettings.TILESIZE * 3);

		EntityImageFactory.getEntityTexture(Entities.UICOIN).draw(-5, GameSettings.TILESIZE, 40, 40);
		tmp.drawString(30, GameSettings.TILESIZE + 2, Integer.toString(player.getInventory().getCoin()), Color.white);

		this.healthUpdate();

		this.pauseMenu();

		graphics.setColor(Color.white);

		if (level.isGameWon()) {
			this.wonMenu();
		}

		if (level.isGotLevelCoin()) {
			if (player.getInventory().getCoin() == GameSettings.TOTCOINS) {
				this.congratsTextFinal();
			} else {
				this.congratsTextEachCoin();
			}
		}

	}

	/**
	 * Method used to display the screen while the game is paused
	 */
	private void pauseMenu() {
		if (this.level.isPauseMenu()) {

			graphics.setColor(pauseBackground);

			graphics.fillRect(0, 0, GameSettings.WIDTH, GameSettings.HEIGHT);

			graphics.scale(2, 2);

			graphics.setColor(Color.white);

			graphics.drawString("Pause Menu", GameSettings.TILESIZE * 4, GameSettings.TILESIZE * 2);

			graphics.scale(1, 1);

		}
	}

	/**
	 * Method used to display the 'Won' screen
	 */
	private void wonMenu() {
		graphics.setColor(new Color(0, 0, 0, logic.fadeWonMenu()));

		graphics.fillRect(0, 0, GameSettings.WIDTH, GameSettings.HEIGHT);

		Color tmp3 = new Color(255, 255, 255, logic.fadeWonMenu());

		this.graphics.setColor(tmp3);

		if (logic.isHoverButtonPlay()) {
			graphics.setColor(Color.gray);
		} else {
			graphics.setColor(Color.white);
		}

		graphics.fillRect(GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8, GameSettings.HEIGHT / 2 + 120, 240, 40);

		if (logic.isHoverButtonQuit()) {
			graphics.setColor(Color.gray);
		} else {
			graphics.setColor(Color.white);
		}

		this.graphics.fillRect(GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8, GameSettings.HEIGHT / 2 + 220, 240, 40);

		graphics.setColor(Color.black);
		graphics.drawString("Main Menu", (GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8) + 70,
				(GameSettings.HEIGHT / 2) + 130);

		graphics.setColor(Color.black);
		graphics.drawString("Quit Game", (GameSettings.WIDTH / 2 - GameSettings.WIDTH / 8) + 70,
				(GameSettings.HEIGHT / 2) + 230);

		graphics.setColor(Color.white);
		graphics.scale(2, 2);
		graphics.drawString("You won!", GameSettings.WIDTH / 4 - 55, 130);

	}

	/**
	 * Method that updates the health bar dynamically based on the players health
	 */
	private void healthUpdate() {
		float healthPer = (((3f * (float) GameSettings.TILESIZE) / 4f * 6f) - 8f) / (float) player.getMaxHealth();

		float remainingHealth = ((float) player.getMaxHealth() - (float) player.getCurrentHealth()) * healthPer;

		graphics.setColor(Color.lightGray);
		graphics.fillRect(GameSettings.TILESIZE / 2, GameSettings.TILESIZE / 6, (3 * GameSettings.TILESIZE) / 4 * 6,
				(3 * GameSettings.TILESIZE) / 4);

		graphics.setColor(Color.black);
		graphics.fillRect(GameSettings.TILESIZE / 2 + 4, GameSettings.TILESIZE / 6 + 4,
				((3 * GameSettings.TILESIZE) / 4 * 6) - 8, (3 * GameSettings.TILESIZE) / 4 - 8);

		graphics.setColor(Color.red);
		graphics.fillRect(GameSettings.TILESIZE / 2 + 4, GameSettings.TILESIZE / 6 + 4,
				(((3 * GameSettings.TILESIZE) / 4 * 6) - 8) - remainingHealth, (3 * GameSettings.TILESIZE) / 4 - 8);
	}

	/**
	 * Method used to alert the player to the amount of coins picked up
	 */
	private void congratsTextEachCoin() {
		textGeneral = new Color(255, 255, 255, logic.congratsTextEachCoin());

		tmp.drawString(GameSettings.TILESIZE * 5 + GameSettings.TILESIZE / 2, GameSettings.TILESIZE / 4,
				"Alright! Remaining coins to collect: "
						+ Integer.toString(GameSettings.TOTCOINS - player.getInventory().getCoin()),
				textGeneral);
	}

	/**
	 * Method used to alert the player for picking up all the coins
	 */
	private void congratsTextFinal() {
		textGeneral = new Color(255, 255, 255, logic.congratsTextEachCoin());

		tmp.drawString(GameSettings.TILESIZE * 5 + GameSettings.TILESIZE / 2, GameSettings.TILESIZE / 4,
				"Congratulations! You got all the coins!", textGeneral);
	}
}

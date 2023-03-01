package main.coordination.maingame;

import java.util.Iterator;
import java.util.Set;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import main.coordination.SoundBoard;
import main.coordination.SoundBoardFactory;
import main.dynamicBody.bullet.Bullet;
import main.dynamicBody.character.enemy.Enemy;
import main.dynamicBody.character.enemy.TypeEnemy;
import main.dynamicBody.character.player.Player;
import main.dynamicBody.character.player.movement.check.DoorCheck;
import main.levels.LevelComp;
import main.levels.Room;
import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.Pair;
import main.worldModel.utilities.enums.Door;

public class ModelCommunicatorImpl implements ModelCommunicator {

	/**
	 * Variable containing the current Level environment
	 */
	private LevelComp level;
	/**
	 * Variable containing the current Player environment
	 */
	private Player player;
	/**
	 * Variable containing the current RoomImpl environment
	 */
	private Room currentRoom;
	/**
	 * Variable used to decide when to play the sound regarding the opening of the
	 * doors
	 */
	private boolean playSound;

	/**
	 * Variable containing the data to play the "opening doors" sound
	 */
	private StateBasedGame states;
	/**
	 * Variable containing the data regarding GameContainer
	 */
	private GameContainer game;
	/**
	 * Variable containing the data regarding GameController
	 */
	private GameController gameController;
	/**
	 * Variable containing the data regarding room prior to the current one
	 */
	private Room previousRoom;

	private boolean cheats;

	/**
	 * Constructor for LogicImpl
	 * 
	 * @param gameController
	 * @param level,         to let LogicImpl keep track of the current state of the
	 *                       level layout
	 * @param player,        to let LogicImpl keep track of the current state of the
	 *                       Player
	 * @throws SlickException
	 * @see SlickException
	 */
	public ModelCommunicatorImpl(final LevelComp level, final Player player, final StateBasedGame state,
			final GameContainer game, GameController gameController) throws SlickException {
		this.level = level;
		this.player = player;
		this.currentRoom = level.getLevel().get(level.getRoomID());
		this.previousRoom = level.getLevel().get(level.getRoomID());
		this.playSound = true;
		this.states = state;
		this.game = game;
		this.gameController = gameController;
		this.cheats = false;
	}

	/**
	 * Method that coordinates all game logic
	 */
	public void update() throws SlickException {
		this.setRoomCleared();
		this.changeLevel();
		this.dieUpdate();

		this.pauseMenu(game.getInput());

		if (!level.isPauseMenu() && !level.isGameWon()) {
			this.moveMain(game.getInput());
			this.shootMain(game.getInput());

			this.moveEnemies();
			this.shootEnemies();

			this.switchRooms(game.getInput());
		}
		if (game.getInput().isKeyPressed(Input.KEY_L) && game.getInput().isKeyPressed(Input.KEY_P))
			cheats = !cheats;

		this.cheatMode();
	}

	private void cheatMode() {
		if (cheats) {
			player.heal(100);
		}
	}

	/**
	 * Method used to change level if player is on stairs
	 * 
	 * @throws SlickException
	 * @see SlickException
	 */
	private void changeLevel() throws SlickException {
		if (player.getCheck().checkStairs(level.getLevel().get(level.getRoomID()).getRoom(), player.getPosition())) {
			gameController.setLevelID(gameController.getID() + 1);
			gameController.init(game, states);
		}
	}

	/**
	 * Method used to check if the player has died
	 * 
	 * @throws SlickException
	 * @see SlickException
	 */
	private void dieUpdate() throws SlickException {
		if (!player.isAlive()) {
			player.resetStats();
			gameController.setLevelID(1);
			states.init(game);
			SoundBoardFactory.storeSound();
			states.enterState(0);
		}
	}

	/**
	 * Method used to pause the game
	 * 
	 * @param input, the appropriate key (Escape key)
	 */
	private void pauseMenu(final Input input) {
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			this.level.setPauseMenu(!this.level.isPauseMenu());
	}

	/**
	 * Method used to check if the Player has used one of the doors to, and then
	 * changes the room to the appropriate one
	 * 
	 * @param input //TODO DELETE THE PARAM CAUSE ITS JUST FOR TESTING
	 */
	private void switchRooms(final Input input) {
		DoorCheck check = new DoorCheck();

		if ((check.transEast(player.getPosition()) || (input.isKeyPressed(Input.KEY_RIGHT)) && cheats)
				&& checkEmpty(Door.EAST)) {
			level.setRoomID(getRoomID(Door.EAST));
			player.transitionPos(
					new Pair<>(GameSettings.TILESIZE, GameSettings.TILESIZE * 5 - GameSettings.TILESIZE / 2));
			level.setChangedRoom(true);
		} else if ((check.transWest(player.getPosition()) || (input.isKeyPressed(Input.KEY_LEFT) && cheats))
				&& checkEmpty(Door.WEST)) {
			level.setRoomID(getRoomID(Door.WEST));
			player.transitionPos(new Pair<>(GameSettings.LIMITRIGHT - GameSettings.TILESIZE,
					GameSettings.TILESIZE * 5 - GameSettings.TILESIZE / 2));
			level.setChangedRoom(true);
		} else if ((check.transNorth(player.getPosition()) || (input.isKeyPressed(Input.KEY_UP) && cheats))
				&& checkEmpty(Door.NORTH)) {
			level.setRoomID(getRoomID(Door.NORTH));
			player.transitionPos(new Pair<>(GameSettings.TILESIZE * 9, GameSettings.LIMITDOWN - GameSettings.TILESIZE));
			level.setChangedRoom(true);
		} else if ((check.transSouth(player.getPosition()) || (input.isKeyPressed(Input.KEY_DOWN) && cheats))
				&& checkEmpty(Door.SOUTH)) {
			level.setRoomID(getRoomID(Door.SOUTH));
			player.transitionPos(new Pair<>(GameSettings.TILESIZE * 9, GameSettings.TILESIZE));
			level.setChangedRoom(true);
		}
		player.setCurrentRoom(level.getLevel().get(level.getRoomID()).getRoom());

		currentRoom = level.getLevel().get(level.getRoomID());

		if (currentRoom.getRoom().getRoomID() != previousRoom.getRoom().getRoomID()) {
			this.cleanPreviousRoom();
			this.previousRoom = currentRoom;
		}

		if (!currentRoom.isGotRoomKey())
			playSound = true;

	}

	/**
	 * Method used to remove the enemy projectiles from the previous visited room
	 */
	private void cleanPreviousRoom() {
		Set<Enemy> enemy = previousRoom.getRoom().getEnemySet();

		enemy.forEach(e -> {
			Iterator<Bullet> enemyIt = e.getRoomBullets().iterator();
			while (enemyIt.hasNext()) {
				Bullet tmp = enemyIt.next();

				if (tmp.getRoom().getRoomID() != currentRoom.getRoom().getRoomID())
					enemyIt.remove();
			}
		});
	}

	/**
	 * Method that sets a variable inside Player to check if the room is clear of
	 * enemies
	 */
	private void setRoomCleared() {
		player.setClearRoom(currentRoom.isGotRoomKey());

		if (currentRoom.isGotRoomKey() && playSound) {
			SoundBoardFactory.getEntitySound(SoundBoard.doorOpen);
			playSound = false;
		}
	}

	/**
	 * Method that calls the movement method inside Player to move him, changing his
	 * coordinates
	 * 
	 * @param input, so that the Player can see in which direction to move
	 * @throws SlickException
	 * @see SlickException
	 */
	private void moveMain(final Input input) throws SlickException {
		player.setPosition(input, level);
	}

	/**
	 * Method that calls the shoot method inside Player
	 * 
	 * @param input, so that the Player can check if the right button for shooting
	 *               has been pressed
	 */
	private void shootMain(final Input input) {
		player.getShootingBullet().checkShooting(input);

		this.moveMainProj();
	}

	/**
	 * Method called by shootMain so that the movement of the Player bullets it's
	 * consequential to their creation
	 */
	private void moveMainProj() {
		Iterator<Bullet> it = player.getRoomBullets().iterator();

		while (it.hasNext()) {
			it.next().updatePos();
		}

		this.eliminateMainProj();
	}

	/**
	 * Method called by moveMainProj so that after moving each bullet, it also
	 * checks if the bullet needs to be destroyed
	 */
	private void eliminateMainProj() {
		Iterator<Bullet> it = player.getRoomBullets().iterator();

		while (it.hasNext()) {
			Bullet tmp = it.next();

			if (!tmp.isAlive() || tmp.getRoom().getRoomID() != currentRoom.getRoom().getRoomID())
				it.remove();
		}
	}

	/**
	 * Method calls the movement method for every enemy placed inside each room
	 */
	private void moveEnemies() {
		level.getLevel().get(level.getRoomID()).getRoom().getEnemySet().forEach(s -> s.updatePos());

		this.eliminateEnemies();
	}

	/**
	 * Method that calls the shoot method for every enemy placed inside each room
	 */
	private void shootEnemies() {
		level.getLevel().get(level.getRoomID()).getRoom().getEnemySet().forEach(s -> s.attack());

		this.moveEnemyProj();
	}

	/**
	 * Method called by shootMain so that the movement of each of the Enemy bullets
	 * it's consequential to their creation
	 */
	private void moveEnemyProj() {
		Set<Enemy> enemy = level.getLevel().get(level.getRoomID()).getRoom().getEnemySet();

		enemy.forEach(e -> {
			Iterator<Bullet> enemyIt = e.getRoomBullets().iterator();
			while (enemyIt.hasNext()) {
				enemyIt.next().updatePos();

			}
		});

		this.eliminateEnemyProj();
	}

	/**
	 * Method called by moveEnemyProj so that after moving each bullet, it also
	 * checks if the bullet needs to be destroyed
	 */
	private void eliminateEnemyProj() {
		Set<Enemy> enemy = currentRoom.getRoom().getEnemySet();

		enemy.forEach(e -> {
			Iterator<Bullet> enemyIt = e.getRoomBullets().iterator();
			while (enemyIt.hasNext()) {
				Bullet tmp = enemyIt.next();

				if (!tmp.isAlive() || tmp.getRoom().getRoomID() != currentRoom.getRoom().getRoomID())
					enemyIt.remove();
			}
		});

	}

	/**
	 * Method called by moveEnemies, so each time a Enemy is moved it also checks if
	 * the Enemy is alive or not
	 */
	private void eliminateEnemies() {
		Iterator<Enemy> it = level.getLevel().get(level.getRoomID()).getRoom().getEnemySet().iterator();

		while (it.hasNext()) {
			Enemy tmp = it.next();

			if (!tmp.isAlive()) {
				if (tmp.getTypeEnemy().equals(TypeEnemy.BOSS))
					level.setGameWon(true);

				it.remove();
			}
		}
	}

	/**
	 * Method called by switchRooms, to check if a Door in a certain cardinal
	 * directions is present or not
	 * 
	 * @param door, to filter the appropriate value in the map
	 * @return true if the RoomModel paired with the Door used as a filter is
	 *         present, otherwise false
	 */
	private boolean checkEmpty(final Door door) {
		return level.getLevel().get(level.getRoomID()).getDoorAccess().entrySet().stream()
				.filter(s -> s.getKey().equals(door)).findFirst().get().getValue().isPresent();
	}

	/**
	 * Method called by switchRooms, to get the roomId that belongs to the right
	 * Door the player has stepped into
	 * 
	 * @param door, to filter the appropriate RoomModel paired with the Door, to get
	 *              the right roomID
	 * @return an int, which is the roomID filtered
	 */
	private int getRoomID(final Door door) {
		return level.getLevel().get(level.getRoomID()).getDoorAccess().entrySet().stream()
				.filter(s -> s.getKey().equals(door)).findFirst().get().getValue().get().getRoomID();
	}

}

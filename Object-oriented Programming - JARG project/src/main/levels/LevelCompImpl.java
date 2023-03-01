package main.levels;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import main.coordination.ImageFactory;
import main.dynamicBody.character.enemy.TypeEnemy;
import main.dynamicBody.move.Direction;
import main.worldModel.LevelModel;
import main.worldModel.generation.LevelModelGeneratorImpl;
import main.worldModel.utilities.Pair;

public class LevelCompImpl implements LevelComp {

	/**
	 * Variable containing the List of the rooms of the level
	 */
	private List<RoomImpl> level = new ArrayList<>();
	/**
	 * Variable containing the general barebone worldModel of each room
	 */
	private LevelModel testLevel;
	/**
	 * Variable containing temporarly data of each room when adding it to the list
	 */
	private RoomImpl tmpRoom;
	/**
	 * Variable containing ID of the current room loaded
	 */
	private int roomID;
	/**
	 * Variable containing the boolean to check if the level coin has been taken
	 */
	private boolean gotLevelCoin;
	/**
	 * Variable containing the boolean to check if the pause menu has been activated
	 */
	private boolean pauseMenu;
	/**
	 * Variable containing the boolean to check if the room has changed
	 */
	private boolean changedRoom;
	/**
	 * Variable containing the boolean to check if the game has been won
	 */
	private boolean gameWon;

	/**
	 * Public constructor for LevelCompImpl
	 * 
	 * @param levelID
	 * @throws IOException
	 */
	public LevelCompImpl(final int levelID) throws IOException {
		testLevel = new LevelModelGeneratorImpl().generateLevel(levelID);

		this.loadRooms();
		this.pauseMenu = false;
		this.roomID = 0;
		this.changedRoom = false;
		this.gameWon = false;
	}

	@Override
	public void loadRooms() {
		for (int i = 0; i < testLevel.getRooms().size(); i++) {
			tmpRoom = new RoomImpl(testLevel.getRooms().get(i), testLevel.getDoorsLayout());

			level.add(tmpRoom);
		}
	}

	public Map<TypeEnemy, Set<Pair<Direction, Animation>>> checkAnimations() throws SlickException {
		Map<TypeEnemy, Set<Pair<Direction, Animation>>> tmpMap = new HashMap<>();

		level.forEach(s -> {
			s.getRoom().getEnemySet().forEach(d -> {

				if (!tmpMap.containsKey(d.getTypeEnemy())) {
					try {
						tmpMap.put(d.getTypeEnemy(), this.loadAnimations(d.getTypeEnemy()));
					} catch (SlickException e) {
						Logger.getLogger(LevelComp.class.getName()).log(Level.SEVERE, null, e);
					}
				}
			});
		});

		return tmpMap;
	}

	private Set<Pair<Direction, Animation>> loadAnimations(final TypeEnemy type) throws SlickException {
		Set<Pair<Direction, Animation>> tmpSet = new HashSet<>();

		tmpSet.add(new Pair<>(Direction.NORTH,
				ImageFactory.getAnimation(ImageFactory.getEnemyImage(type, Direction.NORTH))));
		tmpSet.add(new Pair<>(Direction.EAST,
				ImageFactory.getAnimation(ImageFactory.getEnemyImage(type, Direction.EAST))));
		tmpSet.add(new Pair<>(Direction.WEST,
				ImageFactory.getAnimation(ImageFactory.getEnemyImage(type, Direction.WEST))));
		tmpSet.add(new Pair<>(Direction.SOUTH,
				ImageFactory.getAnimation(ImageFactory.getEnemyImage(type, Direction.SOUTH))));

		return tmpSet;
	}

	public List<RoomImpl> getLevel() {
		return level;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public boolean isGotLevelCoin() {
		return gotLevelCoin;
	}

	public void setGotLevelCoin(boolean gotLevelCoin) {
		this.gotLevelCoin = gotLevelCoin;
	}

	public boolean isPauseMenu() {
		return pauseMenu;
	}

	public void setPauseMenu(boolean pauseMenu) {
		this.pauseMenu = pauseMenu;
	}

	public boolean isChangedRoom() {
		return changedRoom;
	}

	public void setChangedRoom(boolean changedRoom) {
		this.changedRoom = changedRoom;
	}

	public boolean isGameWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}
}

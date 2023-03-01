package main.worldModel.generation;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.SlickException;

import main.worldModel.*;
import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.graphs.BidirectionalGraph;

/**
 * Implementation of interface LevelModelGenerator
 *
 */
public class LevelModelGeneratorImpl implements LevelModelGenerator {

	private final Random random = new Random();
	protected Map<String, Integer> currentConfig = new HashMap<>();
	private final RoomsGraphGenerator graphGen = new RoomsGraphGeneratorImpl();
	private RoomModelGenerator roomGen;

	/**
	 * Method to retrieve the levels' configuration text files, containing all the
	 * ranges for random generation and other necessary stats. The configMap is
	 * implemented through a Map association.
	 * 
	 * @param levelNumber, the number of the level to be generated, acceptable
	 *                     numbers range from 1 to 4, as the game is made of 4
	 *                     levels
	 * @return the configMap for the required level
	 * @throws IOException
	 */
	private Map<String, Integer> getLevelConfig(Integer levelNumber) throws IOException {
		Map<String, Integer> configMap = new HashMap<>();
		File file = new File(GameSettings.RESPATH + "res" + GameSettings.SEP + "levelConfigs" + GameSettings.SEP
				+ "level" + levelNumber + ".txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			String[] words = line.split(" ");
			configMap.put(words[0], Integer.valueOf(words[1]));
		}
		br.close();
		return configMap;
	}

	@Override
	public LevelModel generateLevel(Integer levelNumber) throws IOException {
		LevelModelImpl level = new LevelModelImpl();
		currentConfig = getLevelConfig(levelNumber);
		// randomly pick number of rooms between configuration bounds
		final int numOfRooms = getCurrentConfig().get("minRooms")
				+ random.nextInt(1 + getCurrentConfig().get("maxRooms") - getCurrentConfig().get("minRooms"));
		// randomly pick special room ID
		final Integer specialRoomID = setSpecialRoom(numOfRooms);
		// randomly pick coin room ID
		final Integer coinRoomID = random.nextInt(numOfRooms);
		// set whether level is final level
		final boolean isFinalLevel = (levelNumber == 4);
		roomGen = new RoomModelGeneratorImpl(getCurrentConfig(), specialRoomID, coinRoomID, isFinalLevel);
		for (int i = 0; i < numOfRooms; i++) {
			try {
				level.addRoom(roomGen.generateRoom(i));
			} catch (SlickException e) {
				Logger.getLogger(LevelModel.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		BidirectionalGraph<RoomModel> graph = graphGen.generateRoomsGraph(level.getRooms());
		graphGen.generateDoorsLayout(graph);
		level.addGraph(graph);
		level.addDoorsLayout(graphGen.generateDoorsLayout(graph));
		return level;
	}

	/**
	 * the room that contains the stairs to the next level OR the final boss, is
	 * called special room, it cannot be room 0, it can be any other randomly chosen
	 * room
	 * 
	 * @param numOfRooms, total number of rooms in this level
	 * @return integer corresponding to the special room's ID
	 */
	private int setSpecialRoom(int numOfRooms) {
		return random.nextInt(numOfRooms - 1) + 1;
	}

	public Map<String, Integer> getCurrentConfig() {
		return currentConfig;
	}

}

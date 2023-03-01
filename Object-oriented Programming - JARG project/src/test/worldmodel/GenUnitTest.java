package test.worldmodel;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import main.worldModel.LevelModel;
import main.worldModel.RoomModel;
import main.worldModel.generation.LevelModelGeneratorImpl;
import main.worldModel.utilities.graphs.RoomBFS;

/**
 * JUnit test for level generation
 *
 */
public class GenUnitTest {

	private final LevelModelGeneratorImpl generator = new LevelModelGeneratorImpl();
	private final Random random = new Random();
	private final RoomBFS roomBfs = new RoomBFS();
	private LevelModel testLevel;
	private RoomModel testRoom;
	private Map<String, Integer> levelConfig;

	@org.junit.Before
	public void initTest() throws IOException {

		testLevel = generator.generateLevel(3);
		// random room for fair testing
		testRoom = testLevel.getRooms().get(random.nextInt((int) testLevel.getRooms().stream().count()));
		levelConfig = generator.getCurrentConfig();

	}

	@org.junit.Test
	public void roomsTest() {

		long numOfRooms = testLevel.getRooms().stream().count();

		assertTrue(numOfRooms >= levelConfig.get("minRooms"));
		assertTrue(numOfRooms <= levelConfig.get("maxRooms"));

	}

	@org.junit.Test
	public void entitiesTest() {

		long numOfObj = testRoom.getPickupablesSet().stream().count();

		assertTrue(numOfObj >= levelConfig.get("minObjects"));
		assertTrue(numOfObj <= levelConfig.get("maxObjects"));

	}

	@org.junit.Test
	public void enemiesTest() {

		long numOfEnemies = testRoom.getEnemySet().stream().count();

		assertTrue(numOfEnemies >= levelConfig.get("minEnemies"));
		assertTrue(numOfEnemies <= levelConfig.get("maxEnemies"));

	}

	@org.junit.Test
	public void roomsBFSTest() {

		assertTrue(roomBfs.areDoorsReachable(testLevel.getRooms().get(0)));

	}

	@org.junit.Test
	public void coinTest() {

		long numOfStairsRooms = testLevel.getRooms().stream().filter(r -> r.areStairsPresent()).map(r -> r.getRoomID())
				.count();

		assertTrue(numOfStairsRooms == 1);

	}

	@org.junit.Test
	public void StairsTest() {

		long numOfCoinRooms = testLevel.getRooms().stream().filter(r -> !r.getCoin().isEmpty()).count();

		assertTrue(numOfCoinRooms == 1);

	}

}

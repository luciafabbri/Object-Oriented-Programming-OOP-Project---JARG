package test.player;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.SlickException;
import static org.junit.Assert.*;

import main.dynamicBody.bullet.Bullet;
import main.dynamicBody.bullet.BulletPlayerImpl;
import main.dynamicBody.character.player.Player;
import main.dynamicBody.character.player.PlayerImpl;
import main.dynamicBody.move.Direction;
import main.levels.LevelComp;
import main.levels.LevelCompImpl;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

/**
 * JUnit test for player's and bullet's initial functionalities
 */

public class PlayerAndBulletCreationTest {

	private static Player testPlayer;
	private static Bullet testBullet;
	private static RoomModel testRoom;
	private static LevelComp testLevel;

	@org.junit.BeforeClass
	public static void initTest() throws IOException {
		/**
		 * Need to insert display.create inside try-catch because default constructor of
		 * bulletPlayerImpl contains an Image, otherwise we will have a runtime
		 * exception of type "No OpenGL context found"
		 */
		try {
			Display.create();
		} catch (LWJGLException e) {
			Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, e);
		}
		testLevel = new LevelCompImpl(1);
		testRoom = testLevel.getLevel().get(0).getRoom();
		testPlayer = new PlayerImpl(new Pair<Integer, Integer>(64, 64), Direction.SOUTH, testRoom.getRoomID());
		testPlayer.setCurrentRoom(testRoom);
		testBullet = new BulletPlayerImpl(testPlayer.getPosition(), testPlayer.getDamage(), testPlayer.getDirection(),
				testPlayer.getRoom());
	}

	/**
	 * To check initial player's conditions
	 * 
	 * @throws SlickException
	 */
	@org.junit.Test
	public void testStartPlayer() throws SlickException {
		assertTrue(testPlayer.isAlive());
		assertEquals(100, testPlayer.getMaxHealth());
		assertEquals(100, testPlayer.getCurrentHealth());
		testPlayer.takeDamage(30);
		assertEquals(70, testPlayer.getCurrentHealth());
		testPlayer.heal(40);
		assertEquals(100, testPlayer.getCurrentHealth()); /** because maxHealth is 100 (110 not valid) */
		testPlayer.upgradeMaxHealth(10);
		assertEquals(110, testPlayer.getMaxHealth());
		testPlayer.takeDamage(30);
		assertEquals(70, testPlayer.getCurrentHealth());
		testPlayer.heal(50);
		assertEquals(110, testPlayer.getCurrentHealth()); /** because maxHealth is 110 (120 not valid) */
		assertEquals(new Pair<>(64, 64), testPlayer.getPosition());
		assertEquals(Direction.SOUTH, testPlayer.getDirection());
		assertEquals(10, testPlayer.getDamage());
		testPlayer.upgradeDamage(20);
		assertEquals(30, testPlayer.getDamage());
		assertEquals(0, testPlayer.getLevel());
		assertEquals(0, testPlayer.getInventory().getCoin());
		assertEquals(0, testPlayer.getInventory().getKey());
		testPlayer.getInventory().addCoin();
		testPlayer.getInventory().addKey();
		assertEquals(1, testPlayer.getInventory().getKey());
		assertEquals(1, testPlayer.getInventory().getCoin());
		assertEquals(2, testPlayer.getPlayerSpeed());
		testPlayer.upgradePlayerSpeed(10);
		assertEquals(12, testPlayer.getPlayerSpeed());
		assertEquals(800, testPlayer.getRateOfFire());
		testPlayer.upgradeRateOfFire(500);
		assertEquals(300, testPlayer.getRateOfFire());
		assertEquals(testPlayer.getRoom(), testRoom);
	}

	/**
	 * To check initial bullet's conditions
	 */
	@org.junit.Test
	public void testBulletPlayer() {
		assertTrue(testBullet.isAlive());
		assertEquals(10, testBullet.getDamage());
		assertEquals(new Pair<>(64, 64), testBullet.getPos());
		assertEquals(Direction.SOUTH, testBullet.getDirection());
		assertEquals(testRoom, testBullet.getRoom());
		
		Display.destroy();
	}
}

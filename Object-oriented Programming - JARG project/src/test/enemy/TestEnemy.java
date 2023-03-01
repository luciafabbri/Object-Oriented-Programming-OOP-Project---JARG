package test.enemy;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.SlickException;

import main.dynamicBody.character.enemy.Enemy;
import main.dynamicBody.character.enemy.EnemyImpl;
import main.dynamicBody.character.enemy.TypeEnemy;
import main.dynamicBody.character.enemy.attack.TypeAttack;
import main.dynamicBody.character.enemy.move.TypeMove;
import main.dynamicBody.move.Direction;
import main.levels.LevelComp;
import main.levels.LevelCompImpl;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

/**
 * JUnit test for enemy's and bullet's initial functionalities
 */

public class TestEnemy {

	private static RoomModel testRoom;

	@org.junit.BeforeClass
	public static void initTest() throws IOException {
		try {
			/**
			 * Need to insert display.create inside try-catch because default constructor of
			 * bulletPlayerImpl contains an Image, otherwise we will have a runtime
			 * exception of type "No OpenGL context found"
			 */
			Display.create();
		} catch (LWJGLException e) {
			Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, e);
		}
		LevelComp level = new LevelCompImpl(1);
		level.loadRooms();
		testRoom = level.getLevel().get(0).getRoom();

	}

	/**
	 * To check initial enemy's conditions
	 * 
	 * @throws SlickException
	 */
	@org.junit.Test
	public void testStartPlayer() throws SlickException {
		Enemy testEnemy = new EnemyImpl(new Pair<Integer, Integer>(128, 128), 10, 1, 100, TypeMove.STRAIGHT,
				Direction.SOUTH, TypeAttack.ONE_SIDE, testRoom, TypeEnemy.BOWMAN);
		assertTrue(testEnemy.isAlive());
		testEnemy.takeDamage(30);
		assertTrue(testEnemy.isAlive());
		assertEquals(new Pair<>(128, 128), testEnemy.getPosition());
		assertEquals(Direction.SOUTH, testEnemy.getDirection());
		assertEquals(TypeEnemy.BOWMAN, testEnemy.getTypeEnemy());
		testEnemy.takeDamage(70);
		assertFalse(testEnemy.isAlive());
		
		Display.destroy();
	}

}

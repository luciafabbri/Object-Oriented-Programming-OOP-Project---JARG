package test.enemy;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import main.dynamicBody.bullet.Bullet;
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
 * JUnit test for Enemy attack functionalities
 *
 */
public class TestAttack {

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
		// RIMUOVO TUTTI GLI OSCACOLI PER IL TEST
		testRoom.getObstacleSet().removeAll(testRoom.getObstacleSet());

	}
	

	@org.junit.Test
	public void testOneSideAttack() {
		/*
		 * CONTROLLO CHE IL NEMICO CREI I PROIETTILI
		 */
		Enemy testOneSide = new EnemyImpl(new Pair<Integer, Integer>(128, 128), 10, 1, 100, TypeMove.STRAIGHT,
				Direction.SOUTH, TypeAttack.ONE_SIDE, testRoom, TypeEnemy.BOWMAN);

		// IL NEMICO CREA CORRETTAMENTE IL PROIETTILE
		testOneSide.attack();
		assertEquals(testOneSide.getRoomBullets().size(), 1);
		// LA DIREZIONE ED IL DANNO DEL PROIETTILE SARANNO UGUALI A QUELLI DEL GIOCATORE
		List<Bullet> bullets = testOneSide.getRoomBullets().stream().collect(Collectors.toList());
		assertEquals(testOneSide.getDirection(), bullets.get(0).getDirection());
		assertEquals(testOneSide.getDamage(), bullets.get(0).getDamage());
		// IL NEMICO PRIMA DI CREARE UN ALTRO PROIETTILE DEVE ASPETTARE 2 SECONDI
		testOneSide.attack();
		assertNotEquals(testOneSide.getRoomBullets().size(), 2);
	}

	@org.junit.Test
	public void testTwoSideAttack() {
		/*
		 * CONTROLLO CHE IL NEMICO CREI I PROIETTILI
		 */
		Enemy testTwoSide = new EnemyImpl(new Pair<Integer, Integer>(128, 128), 10, 1, 100, TypeMove.STRAIGHT,
				Direction.SOUTH, TypeAttack.TWO_SIDE, testRoom, TypeEnemy.BOWMAN);

		// IL NEMICO CREA CORRETTAMENTE 2 PROIETTILE
		testTwoSide.attack();
		assertEquals(testTwoSide.getRoomBullets().size(), 2);
	}

	@org.junit.Test
	public void testImmobilizedMove() {
		/*
		 * CONTROLLO CHE IL NEMICO CREI I PROIETTILI
		 */
		Enemy testTriple = new EnemyImpl(new Pair<Integer, Integer>(128, 128), 10, 1, 100, TypeMove.STRAIGHT,
				Direction.SOUTH, TypeAttack.TRIPLE, testRoom, TypeEnemy.BOWMAN);

		// IL NEMICO CREA CORRETTAMENTE 3 PROIETTILE
		testTriple.attack();
		assertEquals(testTriple.getRoomBullets().size(), 3);

	}

	@org.junit.Test
	public void testTeleportMove() {
		/*
		 * CONTROLLO CHE IL NEMICO CREI I PROIETTILI
		 */
		Enemy testFourSide = new EnemyImpl(new Pair<Integer, Integer>(128, 128), 10, 1, 100, TypeMove.STRAIGHT,
				Direction.SOUTH, TypeAttack.FOUR_SIDE, testRoom, TypeEnemy.BOWMAN);

		// IL NEMICO CREA CORRETTAMENTE 4 PROIETTILE
		testFourSide.attack();
		assertEquals(testFourSide.getRoomBullets().size(), 4);
		
		Display.destroy();

	}

}

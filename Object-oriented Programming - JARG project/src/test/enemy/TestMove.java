package test.enemy;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import main.dynamicBody.character.enemy.Enemy;
import main.dynamicBody.character.enemy.EnemyImpl;
import main.dynamicBody.character.enemy.TypeEnemy;
import main.dynamicBody.character.enemy.attack.TypeAttack;
import main.dynamicBody.character.enemy.move.TypeMove;
import main.dynamicBody.move.Direction;
import main.gameEntities.Obstacle;
import main.levels.LevelComp;
import main.levels.LevelCompImpl;
import main.worldModel.RoomModel;
import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.Pair;

/**
 * JUnit test for Enemy move functionalities
 *
 */
public class TestMove {

	// private static Bullet testBullet;
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
	public void testStraightMove() {
		/*
		 * CONTROLLO CHE PER OGNI DIREZIONE IL NEMICO SI MUOVA DALLA PARTE GIUSTA
		 */

		Direction.getDirectionList(true).forEach(d -> {

			Enemy testStraightD = new EnemyImpl(new Pair<Integer, Integer>(128, 128), 10, 2, 100, TypeMove.STRAIGHT, d,
					TypeAttack.ONE_SIDE, testRoom, TypeEnemy.BOWMAN);
			testStraightD.updatePos();
			assertEquals(new Pair<Integer, Integer>(128 + d.getAbscissa() * 2, 128 + d.getOrdinate() * 2),
					testStraightD.getPosition());
			testStraightD.updatePos();
			testStraightD.updatePos();
			testStraightD.updatePos();
			assertEquals(new Pair<Integer, Integer>(128 + d.getAbscissa() * 2 * 4, 128 + d.getOrdinate() * 2 * 4),
					testStraightD.getPosition());
		});

		/*
		 * CONTROLLO CHE IL NEMICO UNA VOLTA SBATTUTO NEL LIMITI CAMBI DIREZIONI
		 */
		Enemy testStraight = new EnemyImpl(new Pair<Integer, Integer>(64, GameSettings.LIMITDOWN - 66), 10, 2, 100,
				TypeMove.STRAIGHT, Direction.SOUTH, TypeAttack.ONE_SIDE, testRoom, TypeEnemy.BOWMAN);

		// IL NEMICO SI MUOVE CORRETTAMENTE VERSO IL BASSO ED ORA SI TROVA ATTACCATO AL
		// MURO
		testStraight.updatePos();
		assertEquals(new Pair<Integer, Integer>(64, GameSettings.LIMITDOWN - 64), testStraight.getPosition());
		assertEquals(Direction.SOUTH, testStraight.getDirection());

		// IL NEMICO CERCA DI MUOVERSI VERSO IL BASSO MA ESSENDO ATTACCATO AL MURO
		// SBATTE E CAMBIA DIREZIONE
		testStraight.updatePos();
		assertEquals(new Pair<Integer, Integer>(64, GameSettings.LIMITDOWN - 64), testStraight.getPosition());
		assertEquals(Direction.NORTH, testStraight.getDirection());

		// IL NEMICO HA CAMBIATO DIREZIONE E SI MUOVE DALLA PARTE OPPOSTA
		testStraight.updatePos();
		assertEquals(new Pair<Integer, Integer>(64, GameSettings.LIMITDOWN - 66), testStraight.getPosition());
		assertEquals(Direction.NORTH, testStraight.getDirection());

		/*
		 * CONTROLLO CHE IL NEMICO SBATTA CONTRO GLI OSTACOLI
		 */
		testStraight = new EnemyImpl(new Pair<Integer, Integer>(64, 128), 10, 2, 100, TypeMove.STRAIGHT,
				Direction.SOUTH, TypeAttack.ONE_SIDE, testRoom, TypeEnemy.BOWMAN);
		testRoom.addObstacle(new Obstacle(new Pair<Integer, Integer>(64, 128 + 65)));
		// IL NEMICO SI MUOVE CORRETTAMENTE VERSO IL BASSO ED ORA SI TROVA ATTACCATO
		// ALL'OSTACOLO
		testStraight.updatePos();
		assertEquals(new Pair<Integer, Integer>(64, 130), testStraight.getPosition());
		assertEquals(Direction.SOUTH, testStraight.getDirection());

		// IL NEMICO CERCA DI MUOVERSI VERSO IL BASSO MA ESSENDO ATTACCATO ALL'OSTACOLO
		// SBATTE E CAMBIA DIREZIONE
		testStraight.updatePos();
		assertEquals(new Pair<Integer, Integer>(64, 130), testStraight.getPosition());
		assertEquals(Direction.NORTH, testStraight.getDirection());

		// IL NEMICO HA CAMBIATO DIREZIONE E SI MUOVE DALLA PARTE OPPOSTA
		testStraight.updatePos();
		assertEquals(new Pair<Integer, Integer>(64, 128), testStraight.getPosition());
		assertEquals(Direction.NORTH, testStraight.getDirection());
	}

	@org.junit.Test
	public void testRandomMove() {
		Enemy testRandom = new EnemyImpl(new Pair<Integer, Integer>(250, 250), 10, 2, 100, TypeMove.RANDOM,
				Direction.SOUTH, TypeAttack.ONE_SIDE, testRoom, TypeEnemy.BOWMAN);
		// IL MOVIMENTO RANDOM UNA VOLTA INIZIALIZZATO CAMBIA LA DIREZIONE OGNI TOT
		// PASSI
		testRandom.updatePos();
		testRandom.updatePos();
		assertNotEquals(new Pair<Integer, Integer>(250, 250), testRandom.getPosition());
		assertNotEquals(Direction.SOUTH, testRandom.getDirection());

	}

	@org.junit.Test
	public void testImmobilizedMove() {
		Enemy testImmobilized = new EnemyImpl(new Pair<Integer, Integer>(64, 64), 10, 2, 100, TypeMove.IMMOBILIZED,
				Direction.SOUTH, TypeAttack.ONE_SIDE, testRoom, TypeEnemy.BOWMAN);
		// AGGIORNARE LA POSIZIONE RITORNERA SEMPRE QUELLA PRECEDENTE
		testImmobilized.updatePos();
		testImmobilized.updatePos();
		testImmobilized.updatePos();
		assertEquals(new Pair<Integer, Integer>(64, 64), testImmobilized.getPosition());
		assertEquals(Direction.SOUTH, testImmobilized.getDirection());

	}

	@org.junit.Test
	public void testTeleportMove() {
		Enemy testTeleport = new EnemyImpl(new Pair<Integer, Integer>(250, 250), 10, 2, 100, TypeMove.TELEPORT,
				Direction.SOUTH, TypeAttack.ONE_SIDE, testRoom, TypeEnemy.BOWMAN);
		// IL MOVIMENTO TELEPORT SI TELETRASPORTA IN UNA POSIZIONE E RIMANI LI PER UN
		// PAIO DI SECONDI
		testTeleport.updatePos();
		assertNotEquals(new Pair<Integer, Integer>(250, 250), testTeleport.getPosition());
		Pair<Integer, Integer> newPos = testTeleport.getPosition();
		Direction newDirection = testTeleport.getDirection();
		testTeleport.updatePos();
		assertEquals(newPos, testTeleport.getPosition());
		assertEquals(newDirection, testTeleport.getDirection());
		
		Display.destroy();

	}

}

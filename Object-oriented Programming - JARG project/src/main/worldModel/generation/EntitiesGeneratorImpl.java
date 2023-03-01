package main.worldModel.generation;

import java.util.Map;
import java.util.Random;
import org.newdawn.slick.SlickException;

import main.dynamicBody.character.enemy.creator.EnemyCreatorImpl;
import main.gameEntities.*;
import main.gameEntities.items.*;
import main.gameEntities.modifiers.*;
import main.worldModel.RoomModelImpl;
import main.worldModel.utilities.*;
import main.worldModel.utilities.enums.Modifiers;
import main.worldModel.utilities.graphs.RoomBFS;

/**
 * Implementation of interface EntitiesGenerator
 *
 */
public class EntitiesGeneratorImpl implements EntitiesGenerator {

	private final RoomModelImpl room;
	private final Map<String, Integer> currentConfig;
	private final RandomPosition randomPosition = new CoherentRandomPosition();
	private final Random random = new Random();
	private Pair<Integer, Integer> pos;
	private final EnemyCreatorImpl enemyGen = new EnemyCreatorImpl();

	/**
	 * @param room           that the generator applies to
	 * @param currentConfig, configuration map to be used
	 */
	public EntitiesGeneratorImpl(RoomModelImpl room, Map<String, Integer> currentConfig) {
		this.room = room;
		this.currentConfig = currentConfig;
	}

	/**
	 * @return a random tile not yet occupied inside the room
	 */
	private Pair<Integer, Integer> generateCoherentPos() {
		pos = randomPosition.generateRandomPosition();
		while (room.getOccupiedTiles().contains(pos) || RoomBFS.getDoorpositions().contains(pos)) {
			pos = randomPosition.generateRandomPosition();
		}
		room.addOccupiedTile(pos);
		return pos;

	}

	@Override
	public void generateModifiers(int numOfModifiers) throws SlickException {
		for (int i = 0; i < numOfModifiers; i++) {
			pos = generateCoherentPos();
			switch (Modifiers.valueOf(random.nextInt(Modifiers.values().length))) {
			case ATTACKUPGRADE1:
				room.addPickupable(new AttackUpgrade1(pos));
				break;
			case HEALTHUPGRADE1:
				room.addPickupable(new HealthUpgrade1(pos));
				break;
			case ATTACKSPEED1:
				room.addPickupable(new AttackSpeed1(pos));
				break;
			case MOVEMENTSPEED1:
				room.addPickupable(new MovementSpeed1(pos));
				break;
			case RECOVERHEALRTH:
				room.addPickupable(new RecoverHealth(pos));
				break;
			}
		}

	}

	@Override
	public void generateStairs() throws SlickException {
		room.setStairsPresence(true);
		room.setStairs(new Stairs(this.generateCoherentPos()));

	}

	@Override
	public void generateBoss() throws SlickException {
		Pair<Integer, Integer> bossTile = new Pair<Integer, Integer>(512, 256);
		room.addEnemy(enemyGen.getBoss(bossTile, room));

	}

	@Override
	public void generateEnemies(int numOfEnemies) throws SlickException {
		for (int j = 0; j < numOfEnemies; j++) {
			room.addEnemy(enemyGen.getMonster(this.currentConfig.get("level"), this.generateCoherentPos(),
					currentConfig.get("enemyHealth"), currentConfig.get("enemyDamage"), room));
		}

	}

	@Override
	public void generateObstacles(int numOfObstacles) throws SlickException {
		for (int k = 0; k < numOfObstacles; k++) {
			room.addObstacle(new Obstacle(this.generateCoherentPos()));
		}
	}

	@Override
	public void generateKey() throws SlickException {
		room.setRoomKey(new Key(generateCoherentPos()));

	}

	@Override
	public void generateCoin() throws SlickException {
		room.addCoin(new Coin(generateCoherentPos()));
	}

}

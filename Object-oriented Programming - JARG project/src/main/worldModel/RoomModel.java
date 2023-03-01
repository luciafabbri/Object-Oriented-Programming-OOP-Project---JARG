package main.worldModel;

import java.util.Optional;
import java.util.Set;
import main.dynamicBody.character.enemy.Enemy;
import main.gameEntities.Obstacle;
import main.gameEntities.Pickupable;
import main.gameEntities.Stairs;
import main.gameEntities.items.Coin;
import main.gameEntities.items.Key;
import main.worldModel.utilities.Pair;
import main.worldModel.utilities.graphs.BidirectionalGraph;

/**
 * An interface that defines the model aspects of the game's rooms
 *
 */
public interface RoomModel {

	/**
	 * @return the room's integer ID
	 */
	int getRoomID();

	/**
	 * @param enemy to be added to the room
	 */
	void addEnemy(Enemy enemy);

	/**
	 * @param obstacle to be added to the room
	 */
	void addObstacle(Obstacle obstacle);

	/**
	 * @param pickupable to be added to the room
	 */
	void addPickupable(Pickupable pickupable);

	/**
	 * @return Set containing the enemies currently in the room
	 */
	Set<Enemy> getEnemySet();

	/**
	 * @return Set containing the obstacles in the room
	 */
	Set<Obstacle> getObstacleSet();

	/**
	 * @return Set of pairs containing the room's obstacles' positions
	 */
	Set<Pair<Integer, Integer>> getObstaclePositions();

	/**
	 * @return Set containing the pickupables currently in the room
	 */
	Set<Pickupable> getPickupablesSet();

	/**
	 * @return a Set containing all the currently occupied tiles in this room
	 */
	Set<Pair<Integer, Integer>> getOccupiedTiles();

	/**
	 * @return a bidirectional graph defining the room's tiles layout
	 */
	BidirectionalGraph<Pair<Integer, Integer>> getTilesGraph();

	/**
	 * @return true if a Stairs object is present inside the room
	 */
	boolean areStairsPresent();

	/**
	 * @return Stairs object if present in the room, else throws
	 *         IllegalStateException
	 */
	Stairs getStairs();

	/**
	 * @return the room's key
	 */
	Key getKey();

	/**
	 * @return Optional of Coin if the room contains Coin, empty optional otherwise
	 */
	Optional<Coin> getCoin();

}

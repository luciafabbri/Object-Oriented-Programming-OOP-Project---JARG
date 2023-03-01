package main.worldModel.generation;

import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

import main.worldModel.RoomModel;
import main.worldModel.utilities.enums.Door;
import main.worldModel.utilities.graphs.BidirectionalGraph;

/**
 * Interface defining the methods to map the rooms' layout within a level
 *
 */
public interface RoomsGraphGenerator {

	/**
	 * @param List of rooms of a level
	 * @return randomly generated bidirectional graph, where the level's rooms are
	 *         the nodes and the connection between rooms are the edges
	 */
	BidirectionalGraph<RoomModel> generateRoomsGraph(LinkedList<RoomModel> rooms);

	/**
	 * 
	 * @param graph of a level's room layout
	 * @return a Map that associates each room of the level to another map linking
	 *         each room's door(NORTH, SOUTH, EAST, WEST) to the connected room in a
	 *         way that is consistent with a three dimensional world. Consistent
	 *         means that bidirectionality is guaranteed for any connection: if A
	 *         room has NORTH door connected to room B, room B has SOUTH door
	 *         connected to room A.
	 */
	Map<RoomModel, Map<Door, Optional<RoomModel>>> generateDoorsLayout(BidirectionalGraph<RoomModel> graph);
}

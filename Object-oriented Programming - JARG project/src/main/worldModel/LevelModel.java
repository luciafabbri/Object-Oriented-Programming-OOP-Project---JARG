package main.worldModel;

import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

import main.worldModel.utilities.enums.Door;
import main.worldModel.utilities.graphs.BidirectionalGraph;

/**
 * An interface that defines the model aspects of the games levels
 *
 */
public interface LevelModel {

	/**
	 * @return a linked list containing all the room models of this level
	 */
	LinkedList<RoomModel> getRooms();

	/**
	 * @return a bidirectional graph that defines all the connections between rooms
	 *         of this level
	 */
	BidirectionalGraph<RoomModel> getRoomsGraph();

	/**
	 * @return a map that, for each room of the level, associates to each door the
	 *         connected adjacent room
	 */
	Map<RoomModel, Map<Door, Optional<RoomModel>>> getDoorsLayout();

	/**
	 * @param the room worldModel to be added to this level
	 */
	void addRoom(RoomModel room);

	/**
	 * @param rooms graph of the level
	 */
	void addGraph(BidirectionalGraph<RoomModel> roomsGraph);

	/**
	 * @param doors layout of the level
	 */
	void addDoorsLayout(Map<RoomModel, Map<Door, Optional<RoomModel>>> doorsLayout);
}

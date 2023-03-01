package main.worldModel;

import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

import main.worldModel.utilities.enums.Door;
import main.worldModel.utilities.graphs.BidirectionalGraph;

/**
 * Implementation of LevelModel interface
 *
 */
public class LevelModelImpl implements LevelModel {

	private boolean isGraphSet = false;
	private final LinkedList<RoomModel> rooms = new LinkedList<>();
	private BidirectionalGraph<RoomModel> roomsGraph;
	private Map<RoomModel, Map<Door, Optional<RoomModel>>> doorsLayoutMap;

	@Override
	public LinkedList<RoomModel> getRooms() {
		return rooms;
	}

	@Override
	public BidirectionalGraph<RoomModel> getRoomsGraph() {
		return roomsGraph;
	}

	@Override
	public void addRoom(RoomModel room) {
		if (isGraphSet) { // rooms may be added so long as the graph is not set yet
			throw new IllegalStateException();
		}
		rooms.add(room);
	}

	@Override
	public void addGraph(BidirectionalGraph<RoomModel> roomsGraph) {
		if (isGraphSet) { // the graph for a given room may only be set once
			throw new IllegalStateException();
		}
		this.roomsGraph = roomsGraph;
		isGraphSet = true;
	}

	@Override
	public void addDoorsLayout(Map<RoomModel, Map<Door, Optional<RoomModel>>> doorsLayout) {
		if (!isGraphSet) { // door layout may be established only after graph generation
			throw new IllegalStateException();
		}
		this.doorsLayoutMap = doorsLayout;

	}

	@Override
	public Map<RoomModel, Map<Door, Optional<RoomModel>>> getDoorsLayout() {
		// TODO Auto-generated method stub
		return doorsLayoutMap;
	}

}

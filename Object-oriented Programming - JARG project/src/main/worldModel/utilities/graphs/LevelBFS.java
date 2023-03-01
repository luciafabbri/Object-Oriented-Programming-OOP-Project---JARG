package main.worldModel.utilities.graphs;

import main.worldModel.RoomModel;

/**
 * Extension of class BreadthFirstSearch used specifically to implement BFS
 * methods for the game's levels
 *
 */
public class LevelBFS extends BreadthFirstSearch<RoomModel> {

	/**
	 * @param level graph to be checked
	 * @return true if from any room any other room is reachable inside the graph
	 */
	public boolean areRoomsReachable(BidirectionalGraph<RoomModel> graph) {

		for (RoomModel r : graph.getNodes()) {
			for (RoomModel or : graph.getNodes()) {
				if (!this.isReachable(graph, r, or)) {
					return false;
				}
			}
		}
		return true;
	}

}

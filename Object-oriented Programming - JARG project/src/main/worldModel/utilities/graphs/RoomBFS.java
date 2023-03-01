package main.worldModel.utilities.graphs;

import java.util.HashSet;
import java.util.Set;

import main.worldModel.RoomModel;
import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.Pair;

/**
 * Extension of class BreadthFirstSearch used specifically to implement BFS
 * methods for the game's rooms
 *
 */
public class RoomBFS extends BreadthFirstSearch<Pair<Integer, Integer>> {

	private final static Set<Pair<Integer, Integer>> doorPositions;

	/**
	 * @return the set containing all four door positions
	 */
	public static Set<Pair<Integer, Integer>> getDoorpositions() {
		return doorPositions;
	}

	// static map generation for door tiles
	static {
		doorPositions = new HashSet<>();
		doorPositions
				.add(new Pair<Integer, Integer>(GameSettings.WIDTH / 2 - GameSettings.TILESIZE, GameSettings.TILESIZE));
		doorPositions.add(new Pair<Integer, Integer>(GameSettings.LIMITRIGHT - GameSettings.TILESIZE,
				GameSettings.HEIGHT / 2 - GameSettings.TILESIZE));
		doorPositions.add(new Pair<Integer, Integer>(GameSettings.WIDTH / 2 - GameSettings.TILESIZE,
				GameSettings.HEIGHT - GameSettings.TILESIZE * 2));
		doorPositions.add(
				new Pair<Integer, Integer>(GameSettings.TILESIZE, GameSettings.HEIGHT / 2 - GameSettings.TILESIZE));
	}

	/**
	 * @param roomDesign to be checked
	 * @return true if from any door any other door is reachable without
	 *         insurmountable obstacle blocking all paths
	 */
	public boolean areDoorsReachable(RoomModel roomModel) {
		for (Pair<Integer, Integer> d : doorPositions) {
			for (Pair<Integer, Integer> p : doorPositions) {
				if (!this.isReachable(roomModel.getTilesGraph(), d, p)) {
					return false;
				}
			}
		}
		return true;
	}

}

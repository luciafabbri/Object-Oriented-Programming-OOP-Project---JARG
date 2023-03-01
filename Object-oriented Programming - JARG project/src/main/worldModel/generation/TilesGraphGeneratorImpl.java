package main.worldModel.generation;

import java.util.LinkedList;

import main.worldModel.RoomModel;
import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.Pair;
import main.worldModel.utilities.graphs.BidirectionalGraph;

/**
 * Implementation of TilesGraphGenerator interface
 *
 */
public class TilesGraphGeneratorImpl implements TilesGraphGenerator {

	@Override
	public BidirectionalGraph<Pair<Integer, Integer>> generateTilesGraph(RoomModel room) {
		BidirectionalGraph<Pair<Integer, Integer>> graph = new BidirectionalGraph<>();
		for (int x = GameSettings.TILESIZE; x <= GameSettings.WIDTH; x += 64) {
			for (int y = GameSettings.TILESIZE; y <= GameSettings.HEIGHT; y += 64) {
				Pair<Integer, Integer> tilePos = new Pair<Integer, Integer>(x, y);
				if (!room.getObstaclePositions().contains(tilePos)) {
					if (!graph.getNodes().contains(tilePos)) {
						graph.addNode(tilePos);
					}
					computeAdjacentTile(tilePos).forEach(t -> {
						if (!room.getObstaclePositions().contains(t) && !graph.getEdges(tilePos).contains(t)) {
							if (!graph.getNodes().contains(t)) {
								graph.addNode(t);
							}
							graph.addEdge(tilePos, t);
						}
					});
				}
			}
		}
		return graph;
	}

	/**
	 * @param coordinates of a tile
	 * @return a List of adjacent tiles(0 to 4)
	 */
	private LinkedList<Pair<Integer, Integer>> computeAdjacentTile(Pair<Integer, Integer> tilePosition) {
		LinkedList<Pair<Integer, Integer>> list = new LinkedList<>();
		Pair<Integer, Integer> upperTile = new Pair<Integer, Integer>(tilePosition.getX() - 64, tilePosition.getY());
		Pair<Integer, Integer> lowerTile = new Pair<Integer, Integer>(tilePosition.getX() + 64, tilePosition.getY());
		Pair<Integer, Integer> leftTile = new Pair<Integer, Integer>(tilePosition.getX(), tilePosition.getY() - 64);
		Pair<Integer, Integer> rightTile = new Pair<Integer, Integer>(tilePosition.getX(), tilePosition.getY() + 64);
		if (upperTile.getX() >= GameSettings.TILESIZE) {
			list.add(upperTile);
		}
		if (lowerTile.getX() <= GameSettings.WIDTH) {
			list.add(lowerTile);
		}
		if (leftTile.getY() >= GameSettings.TILESIZE) {
			list.add(leftTile);
		}
		if (rightTile.getY() <= GameSettings.HEIGHT) {
			list.add(rightTile);
		}
		return list;

	}

}

package main.dynamicBody.character.enemy.move;

import main.coordination.init.StateCoord;
import main.dijkstra.Node;
import main.dynamicBody.character.player.Player;
import main.dynamicBody.move.Direction;
import main.worldModel.RoomModel;
import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.Pair;
import main.worldModel.utilities.graphs.BidirectionalGraph;
import main.worldModel.utilities.graphs.Graph;

/**
 * Abstract Class with utilities' function for the movement ToPlayer and for the
 * Dijkstra Algorithms
 */
public final class ToPlayerUtil {

	private static Direction lastDir;

	private static Player player = StateCoord.getPlayer();

	private static int disPX = (player.getDimension().getRight() + player.getDimension().getLeft()) / 2;
	private static int disPY = (GameSettings.TILESIZE + player.getDimension().getDown()) / 2;

	private static Pair<Integer, Integer> North = new Pair<>(GameSettings.WIDTH / 2 - GameSettings.TILESIZE, 0);
	private static Pair<Integer, Integer> South = new Pair<>(GameSettings.WIDTH / 2 - GameSettings.TILESIZE,
			GameSettings.HEIGHT - GameSettings.TILESIZE);
	private static Pair<Integer, Integer> West = new Pair<>(0, GameSettings.HEIGHT / 2 - GameSettings.TILESIZE);
	private static Pair<Integer, Integer> East = new Pair<>(GameSettings.LIMITRIGHT,
			GameSettings.HEIGHT / 2 - GameSettings.TILESIZE);

	/**
	 * Private constructors to prevent instantiation
	 */
	private ToPlayerUtil() {

	}

	/**
	 * Method that transform the TileGraph of Pair in RoomModel to a Graph of Node
	 * of Pair
	 * 
	 * @param room, the room where you can find the TileGraph
	 * @return The graph of Node
	 */
	public static Graph<Node<Pair<Integer, Integer>>> createGraph(RoomModel room) {
		Graph<Node<Pair<Integer, Integer>>> graph = new BidirectionalGraph<>();
		// INSERISCO NODI DENTRO AL GRAFO
		room.getTilesGraph().getNodes().forEach(x -> {
			if (!room.getObstaclePositions().contains(x)) {
				graph.addNode(new Node<Pair<Integer, Integer>>(x));
			}
		});
		// System.out.println(graph.getNodes().size());
		// SETTO I NODI ADIANCENTI IN TUTTI I NODI
		graph.getNodes().forEach(x -> {
			room.getTilesGraph().getEdges(x.getName()).forEach(y -> {
				x.addDestination(ToPlayerUtil.findNode(y, graph), 1);
			});
		});

		// AGGIUNGO LE PORTE AL GRAFICO
		graph.addNode(new Node<Pair<Integer, Integer>>(North));
		graph.addNode(new Node<Pair<Integer, Integer>>(South));
		graph.addNode(new Node<Pair<Integer, Integer>>(West));
		graph.addNode(new Node<Pair<Integer, Integer>>(East));

		// COLLEGO CON I TILE VICINI
		ToPlayerUtil.findNode(North, graph).addDestination(
				findNode(new Pair<Integer, Integer>(North.getX(), North.getY() + GameSettings.TILESIZE), graph), 1);
		ToPlayerUtil.findNode(South, graph).addDestination(
				findNode(new Pair<Integer, Integer>(South.getX(), South.getY() - GameSettings.TILESIZE), graph), 1);
		ToPlayerUtil.findNode(West, graph).addDestination(
				findNode(new Pair<Integer, Integer>(West.getX() + GameSettings.TILESIZE, West.getY()), graph), 1);
		ToPlayerUtil.findNode(East, graph).addDestination(
				findNode(new Pair<Integer, Integer>(East.getX() - GameSettings.TILESIZE, East.getY()), graph), 1);

		return graph;
	}

	/**
	 * Method use to find the tile of a position
	 * 
	 * @param pos, Position that you need to known the Tile
	 * @return a Pair that identify a tile
	 */
	public static Pair<Integer, Integer> findTile(Pair<Integer, Integer> pos) {
		int x = pos.getX() / GameSettings.TILESIZE;
		int y = pos.getY() / GameSettings.TILESIZE;
		return new Pair<Integer, Integer>(x * GameSettings.TILESIZE, y * GameSettings.TILESIZE);
	}

	/**
	 * Method use to find the node in a Graph
	 * 
	 * @param pos,   name of Node
	 * @param graph, graph where is located the node
	 * @return the Node you need to find
	 */
	public static Node<Pair<Integer, Integer>> findNode(Pair<Integer, Integer> pos,
			Graph<Node<Pair<Integer, Integer>>> graph) {
		for (Node<Pair<Integer, Integer>> node : graph.getNodes()) {
			if (node.getName().equals(pos)) {
				return node;
			}
		}
		throw new IllegalStateException("Node not found");
	}

	/**
	 * Method use to find the direction of an Enemy that want to know where is
	 * Player
	 * 
	 * @param posUpLeft,    Position of bite UpLeft of enemy
	 * @param posDownRight, Position of bite DownLeft of enemy
	 * @param graph,        The TileGraph of the room
	 * @return return the Direction of the enemy
	 */
	public static Direction findDir(Pair<Integer, Integer> posUpLeft, Pair<Integer, Integer> posDownRight,
			Graph<Node<Pair<Integer, Integer>>> graph) {
		Direction dir;

		int size1 = findNode(findTile(posUpLeft), graph).getShortestPath().size() - 1;
		Pair<Integer, Integer> checkPos1 = findNode(findTile(posUpLeft), graph).getShortestPath().get(size1).getName();

		int size2 = findNode(findTile(posDownRight), graph).getShortestPath().size() - 1;
		Pair<Integer, Integer> checkPos2 = findNode(findTile(posDownRight), graph).getShortestPath().get(size2)
				.getName();

		if (checkPos1.equals(checkPos2)) {
			if (posUpLeft.getX() < checkPos1.getX()) {
				dir = Direction.EAST;
			} else if (posUpLeft.getX() > checkPos1.getX()) {
				dir = Direction.WEST;
			} else if (posUpLeft.getY() < checkPos1.getY()) {
				dir = Direction.SOUTH;
			} else if (posUpLeft.getY() > checkPos1.getY()) {
				dir = Direction.NORTH;
			} else {
				throw new IllegalArgumentException("Error in ShortPath, Enemy can't move in Diagonal");
			}
		} else {
			dir = lastDir;
		}
		lastDir = dir;
		return dir;

	}

	/**
	 * Method use to known the pixel in the middle of Player
	 * 
	 * @return Coordinate of the middle of player
	 */
	public static Pair<Integer, Integer> getPlayerPos() {
		return new Pair<>(player.getPosition().getX() + disPX, player.getPosition().getY() + disPY);
	}

}

package main.worldModel.generation;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import main.worldModel.LevelModel;
import main.worldModel.RoomModel;
import main.worldModel.utilities.enums.Door;
import main.worldModel.utilities.graphs.BidirectionalGraph;
import main.worldModel.utilities.graphs.RoomBFS;

import java.util.Optional;

/**
 * A class that includes prints for all the features implemented in the
 * worldModel package
 *
 */
public class GenerationPrints {

	public static void main(String[] args) throws IOException, InterruptedException {

		final LevelModelGeneratorImpl generator = new LevelModelGeneratorImpl();
		final LevelModel testLevel = generator.generateLevel(3);

		// game entities listing for each room

		testLevel.getRooms().forEach(r -> {
			System.out.println(r.toString() + "has ID : " + r.getRoomID());
			System.out.println("Room key is in position: " + r.getKey().getPosition());

			System.out.print("Occupied tiles are the following: ");

			r.getOccupiedTiles().forEach(t -> {
				System.out.print(t.toString() + " ");
			});
			System.out.println("");

			System.out.print("Enemies are the following: ");

			r.getEnemySet().forEach(e -> {
				System.out.print(e.toString() + " ");
			});
			System.out.println("");
			System.out.print("Pickupables are the following: ");

			r.getPickupablesSet().forEach(i -> {
				System.out.print(i.toString() + " ");
			});
			System.out.println("");

			System.out.print("Obstacles are the following: ");

			r.getObstacleSet().forEach(t -> {
				System.out.print(t.toString() + " ");
			});
			System.out.println("");
			System.out.println("");
		});

		// coin room
		int coinRoomID = testLevel.getRooms().stream().filter(r -> !r.getCoin().isEmpty()).findFirst().get()
				.getRoomID();
		System.out.println("Room with coin in this level has ID " + coinRoomID + "\n");

		// rooms graph printing
		final BidirectionalGraph<RoomModel> graph = testLevel.getRoomsGraph();

		System.out.println("Room graph: ");
		for (RoomModel r : graph.getNodes()) {
			System.out.print("Room " + r.getRoomID() + " has connections to rooms: ");
			for (RoomModel a : graph.getEdges(r)) {
				System.out.print(a.getRoomID() + " ");
			}
			System.out.println("");
		}
		// doors layout graph printing
		final Map<RoomModel, Map<Door, Optional<RoomModel>>> layout = testLevel.getDoorsLayout();

		for (RoomModel d : layout.keySet()) {
			System.out.println("Key: " + d.getRoomID());
			System.out.print(" has doors: ");
			for (Entry<Door, Optional<RoomModel>> e : layout.get(d).entrySet()) {
				if (e.getValue().isPresent()) {
					System.out.print(e.getKey().toString() + " " + e.getValue().get().getRoomID() + ", ");
				} else {
					System.out.print(e.getKey().toString() + " " + e.getValue() + ", ");
				}

			}
			System.out.println("");
		}

		// tilegraph for a given room printer
		testLevel.getRooms().get(0).getObstacleSet().forEach(o -> {
			System.out.println("Obstacle in pos: " + o.getPosition());
		});

		// tileGraph and path finding
//		final BidirectionalGraph<Pair<Integer, Integer>> tileGraph = testLevel.getRooms().get(0).getTilesGraph();
		/*
		 * System.out.println("Room graph: "); for (Pair<Integer, Integer> t :
		 * tileGraph.getNodes()) { System.out.print("Tile " + t +
		 * " has connections to tiles: "); for (Pair<Integer, Integer> next :
		 * tileGraph.getEdges(t)) { System.out.print(next + " "); }
		 * System.out.println(""); }
		 */

		// BFS test
		final RoomBFS roomBfs = new RoomBFS();
		System.out.println(
				"Inside room 0 all doors are reachable: " + roomBfs.areDoorsReachable(testLevel.getRooms().get(0)));

		// stairs generation test
		Long numOfStairsRooms = testLevel.getRooms().stream().filter(r -> r.areStairsPresent()).map(r -> r.getRoomID())
				.count();
		System.out.println("Rooms with stairs are: " + numOfStairsRooms);
		int stairsRoomID = testLevel.getRooms().stream().filter(r -> r.areStairsPresent()).findFirst().get()
				.getRoomID();
		System.out.println("Room with stairs has ID: " + stairsRoomID);

	}

}

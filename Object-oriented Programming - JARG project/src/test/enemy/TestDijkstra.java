package test.enemy;

import static org.junit.Assert.*;

import main.dijkstra.DijkstraAlg;
import main.dijkstra.Node;
import main.worldModel.utilities.graphs.BidirectionalGraph;
import main.worldModel.utilities.graphs.Graph;

public class TestDijkstra {

	@org.junit.Test
	public void testStraightMove() {

		Node<Integer> nodeA = new Node<>(1);
		Node<Integer> nodeB = new Node<>(2);
		Node<Integer> nodeC = new Node<>(3);
		Node<Integer> nodeD = new Node<>(4);
		Node<Integer> nodeE = new Node<>(5);
		Node<Integer> nodeF = new Node<>(6);

		nodeA.addDestination(nodeB, 10);
		nodeA.addDestination(nodeC, 15);

		nodeB.addDestination(nodeD, 12);
		nodeB.addDestination(nodeF, 15);

		nodeC.addDestination(nodeE, 10);

		nodeD.addDestination(nodeE, 2);
		nodeD.addDestination(nodeF, 1);

		nodeF.addDestination(nodeE, 5);

		Graph<Node<Integer>> graph = new BidirectionalGraph<Node<Integer>>();

		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);
		graph.addNode(nodeF);

		graph = DijkstraAlg.calculateShortestPathFromSource(graph, nodeA);

		// IL PERCORSO MINIMO DEL NODO A E' 0 ESSENDO IL PRIMO
		assertTrue(nodeA.getShortestPath().isEmpty());

		// IL PERCORSO MINIMO DEL NODO F E' 3 OVVERO: A->B->D
		assertEquals(3, nodeF.getShortestPath().size());

	}

}

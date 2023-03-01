package test.worldmodel;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import main.worldModel.utilities.Pair;
import main.worldModel.utilities.graphs.BidirectionalGraph;
import main.worldModel.utilities.graphs.BreadthFirstSearch;
import main.worldModel.utilities.graphs.Graph;

/**
 * JUnit test for graph functionalities
 *
 */
public class GraphTest {

	private Graph<Pair<Integer, Integer>> testGraph = null;
	private BreadthFirstSearch<Pair<Integer, Integer>> testBFS = null;
	private Set<Pair<Integer, Integer>> nodesSet;

	@org.junit.Before
	public void initTest() {

		testGraph = new BidirectionalGraph<>();
		testBFS = new BreadthFirstSearch<>();
		nodesSet = new HashSet<>();

		nodesSet.add(new Pair<Integer, Integer>(0, 0));
		nodesSet.add(new Pair<Integer, Integer>(1, 0));
		nodesSet.add(new Pair<Integer, Integer>(0, 1));

		nodesSet.forEach(n -> {
			testGraph.addNode(n);
		});

	}

	@org.junit.Test
	public void testNodesAddition() {

		assertTrue(testGraph.hasNode(new Pair<Integer, Integer>(0, 0)));
		assertEquals(nodesSet, testGraph.getNodes());

	}

	@org.junit.Test
	public void testEdgesAddition() {

		testGraph.addEdge(new Pair<Integer, Integer>(0, 0), new Pair<Integer, Integer>(1, 0));

		assertEquals(testGraph.getEdges(new Pair<Integer, Integer>(0, 0)).size(), 1);
		assertTrue(testGraph.hasEdge(new Pair<Integer, Integer>(0, 0), new Pair<Integer, Integer>(1, 0)));
		assertFalse(testGraph.hasEdge(new Pair<Integer, Integer>(0, 0), new Pair<Integer, Integer>(0, 1)));

	}

	@org.junit.Test
	public void testBFS() {

		testGraph.addEdge(new Pair<Integer, Integer>(0, 0), new Pair<Integer, Integer>(1, 0));
		testGraph.addEdge(new Pair<Integer, Integer>(1, 0), new Pair<Integer, Integer>(0, 1));

		assertTrue(testBFS.isReachable(testGraph, new Pair<Integer, Integer>(0, 0), new Pair<Integer, Integer>(0, 1)));

	}

}

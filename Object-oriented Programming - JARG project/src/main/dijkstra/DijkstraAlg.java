package main.dijkstra;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;

import main.worldModel.utilities.graphs.Graph;

import java.util.Set;

/**
 * Dijkstra algorithms take from https://www.baeldung.com/java-dijkstra and
 * modify
 *
 */
public class DijkstraAlg {

	/**
	 * Method use to calculate the shortest path
	 * 
	 * @param <T>     Type of node
	 * @param graph,  graph where are nodes
	 * @param source, first node
	 * @return the Dijkstra Graph
	 */
	public static <T> Graph<Node<T>> calculateShortestPathFromSource(Graph<Node<T>> graph, Node<T> source) {
		source.setDistance(0);

		Set<Node<T>> settledNodes = new HashSet<>();
		Set<Node<T>> unsettledNodes = new HashSet<>();

		unsettledNodes.add(source);

		while (unsettledNodes.size() != 0) {
			Node<T> currentNode = getLowestDistanceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			for (Entry<Node<T>, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
				Node<T> adjacentNode = adjacencyPair.getKey();
				Integer edgeWeight = adjacencyPair.getValue();
				if (!settledNodes.contains(adjacentNode)) {
					calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
					unsettledNodes.add(adjacentNode);
				}
			}
			settledNodes.add(currentNode);
		}
		return graph;
	}

	/**
	 * method that returns the node with the lowest distance from the unsettled
	 * nodes set
	 * 
	 * @param <T>             Type of Node
	 * @param unsettledNodes, Set of unsettled nodes
	 * @return Return the node with lowest distance to discover
	 */
	private static <T> Node<T> getLowestDistanceNode(Set<Node<T>> unsettledNodes) {
		Node<T> lowestDistanceNode = null;
		int lowestDistance = Integer.MAX_VALUE;
		for (Node<T> node : unsettledNodes) {
			int nodeDistance = node.getDistance();
			if (nodeDistance < lowestDistance) {
				lowestDistance = nodeDistance;
				lowestDistanceNode = node;
			}
		}
		return lowestDistanceNode;
	}

	/**
	 * Method compares the actual distance with the newly calculated one while
	 * following the newly explored path
	 * 
	 * @param <T>             Type of Node
	 * @param evaluationNode, Evaluation Node
	 * @param edgeWeigh,      the edge weigh
	 * @param sourceNode,     source Node
	 */
	private static <T> void calculateMinimumDistance(Node<T> evaluationNode, Integer edgeWeigh, Node<T> sourceNode) {
		Integer sourceDistance = sourceNode.getDistance();
		if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
			evaluationNode.setDistance(sourceDistance + edgeWeigh);
			LinkedList<Node<T>> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			evaluationNode.setShortestPath(shortestPath);
		}
	}

}

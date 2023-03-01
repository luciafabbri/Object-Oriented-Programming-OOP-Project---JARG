package main.dijkstra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Node's class for dijkstra algorithm
 */
public class Node<T> {

	private T name;

	private List<Node<T>> shortestPath = new LinkedList<>();

	private Integer distance = Integer.MAX_VALUE;

	Map<Node<T>, Integer> adjacentNodes = new HashMap<>();

	/**
	 * Add a Node destination with a distance
	 * 
	 * @param destination, Node destination
	 * @param distance,    Node distance
	 */
	public void addDestination(Node<T> destination, int distance) {
		adjacentNodes.put(destination, distance);
	}

	/**
	 * Base Constructor
	 * 
	 * @param name
	 */
	public Node(T name) {
		this.name = name;
	}

	/**
	 * Get name of Node
	 * 
	 * @return the name of node
	 */
	public T getName() {
		return name;
	}

	/**
	 * Methods use to get the list of the shortest path of a node from the source
	 * node
	 * 
	 * @return a list of Node of the shortest path
	 */
	public List<Node<T>> getShortestPath() {
		return shortestPath;
	}

	/**
	 * The distance of the node
	 * 
	 * @return an int of the distance
	 */
	public Integer getDistance() {
		return distance;
	}

	/**
	 * Set the shortestPath of node
	 * 
	 * @param shortestPath, list of nodes that represents the shortest path
	 */
	public void setShortestPath(List<Node<T>> shortestPath) {
		this.shortestPath = shortestPath;
	}

	/**
	 * Set the distance of the Node
	 * 
	 * @param distance, value of distance
	 */
	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	/**
	 * Get the Adjacent Node
	 * 
	 * @return a Map of the adjacent Nodes with distance
	 */
	public Map<Node<T>, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}
}

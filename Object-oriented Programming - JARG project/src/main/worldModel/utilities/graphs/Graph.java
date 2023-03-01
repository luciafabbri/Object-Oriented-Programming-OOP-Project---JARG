package main.worldModel.utilities.graphs;

import java.util.*;

/**
 * An interface that defines a generic graph and its basic operations
 * 
 * @param <T> is the nodes' type
 */
public interface Graph<T> {

	/**
	 * @param node to be added
	 */
	void addNode(T n);

	/**
	 * @param source      of the edge
	 * @param destination of the edge
	 */
	void addEdge(T source, T destination);

	/**
	 * @param node to be searched
	 * @return true if node n is present in the graph
	 */
	boolean hasNode(T n);

	/**
	 * @param source
	 * @param destination
	 * @return true if an edge connecting source and destination is present
	 */
	boolean hasEdge(T source, T destination);

	/**
	 * @param node
	 * @return a list of all edges of node n
	 */
	List<T> getEdges(T n);

	/**
	 * @return a set containing all the nodes in the graph
	 */
	Set<T> getNodes();
}

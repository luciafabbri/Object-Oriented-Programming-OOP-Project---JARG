package main.worldModel.utilities.graphs;

import java.util.*;

/**
 * Bidirectional implementation of Graph<T> class
 *
 * @param <T> is the nodes' type
 */
public class BidirectionalGraph<T> implements Graph<T> {

	private final Map<T, List<T>> graphMap = new HashMap<>();

	@Override
	public void addNode(T n) {

		graphMap.put(n, new LinkedList<T>());

	}

	@Override
	public void addEdge(T source, T destination) {

		if (!graphMap.keySet().contains(source) || !graphMap.keySet().contains(source)) {
			throw new IllegalStateException();
		}

		graphMap.get(source).add(destination);
		graphMap.get(destination).add(source);

	}

	@Override
	public boolean hasNode(T n) {

		return graphMap.containsKey(n);
	}

	@Override
	public boolean hasEdge(T source, T destination) {

		return graphMap.get(source).contains(destination);
	}

	@Override
	public List<T> getEdges(T n) {

		return graphMap.get(n);
	}

	@Override
	public Set<T> getNodes() {

		return graphMap.keySet();
	}

	@Override
	public String toString() {
		return "BidirectionalGraph [graphMap=" + graphMap + "]";
	}

}

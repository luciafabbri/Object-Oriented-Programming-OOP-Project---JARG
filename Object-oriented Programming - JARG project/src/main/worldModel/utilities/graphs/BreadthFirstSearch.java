package main.worldModel.utilities.graphs;

import java.util.*;

/**
 * A class containing methods to explore a graph of type
 * worldModel.utilities.graphs.Graph<T> via Breadth First Search
 *
 * @param <T> is the nodes' type
 */
public class BreadthFirstSearch<T> {

	/**
	 * @param graph       to be searched
	 * @param source      of the path
	 * @param destination of the path
	 * @return true if a path from source to destination is present in the graph
	 */
	public boolean isReachable(Graph<T> graph, T source, T destination) {

		final Map<T, Boolean> map = new HashMap<>();
		graph.getNodes().forEach(n -> {
			map.put(n, false);
		});

		final LinkedList<T> queue = new LinkedList<T>();

		map.put(source, true);
		queue.add(source);

		Iterator<T> iterator;

		while (queue.size() != 0) {

			source = queue.poll();

			T n;
			iterator = graph.getEdges(source).listIterator();

			while (iterator.hasNext()) {
				n = iterator.next();
				if (source.equals(destination))
					return true;
				if (!map.get(n)) {
					map.put(n, true);
					queue.add(n);
				}
			}
		}

		return false;
	}

}

import java.util.*;

/**
 * Graph class
 */
public class Graph implements GraphInterface<Town, Road> {

	private Set<Town> towns;
	private Set<Road> roads;

	private Map<Town, Town> prev = new HashMap<>();
	private Map<Town, Integer> pWeights = new HashMap<>();

	/**
	 * no arg constructor
	 */
	public Graph() {
		towns = new HashSet<>();
		roads = new HashSet<>();
	}

	/**
	 * returns an edge if between two towns if exists
	 */
	@Override
	public Road getEdge(Town source, Town destination) {
		for (Road road : roads) {
			if (road.contains(source) && road.contains(destination)) {
				return road;
			}
		}
		return null;
	}

	/**
	 * adds edge
	 */
	@Override
	public Road addEdge(Town source, Town destination, int weight, String description) {
		Road road = new Road(source, destination, weight, description);

		roads.add(road);
		source.addAdjTown(destination);
		destination.addAdjTown(source);
		return road;
	}

	/**
	 * adds vertex
	 */
	@Override
	public boolean addVertex(Town v) {

		return towns.add(v);
	}

	/**
	 * returns true or falseto see if roads contains a road between two destinations
	 */
	@Override
	public boolean containsEdge(Town source, Town destination) {
		for (Road road : roads) {
			if (road.contains(source) && road.contains(destination)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * returns true or false for containing a certain vertex
	 */
	@Override
	public boolean containsVertex(Town v) {

		return towns.contains(v);
	}

	/**
	 * returns roads set
	 */
	@Override
	public Set<Road> edgeSet() {

		return roads;
	}

	/**
	 * returns all edges of a vertex
	 */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> edges = new HashSet<>();

		for (Road road : roads) {
			if (road.contains(vertex)) {
				edges.add(road);
			}
		}

		return edges;
	}

	/**
	 * removes an edge
	 */
	@Override
	public Road removeEdge(Town source, Town destination, int weight, String description) {
		Road temp = null;

		for (Road road : roads) {
			if (road.equals(new Road(source, destination, weight, description))) {
				temp = road;
				break;
			}
		}

		if (temp != null) {
			roads.remove(temp);

			source.getAdjTowns().remove(destination);
			destination.getAdjTowns().remove(source);
		}

		return temp;
	}

	/**
	 * removes a vertex
	 */
	@Override
	public boolean removeVertex(Town v) {
		if (!towns.contains(v)) {
			return false;
		}

		Set<Road> temp = new HashSet<Road>();

		for (Road road : roads) {
			if (road.contains(v)) {
				temp.add(road);
			}
		}
		for (Road road : temp) {
			roads.remove(road);
		}

		for (Town town : towns) {
			town.getAdjTowns().remove(v);
		}

		towns.remove(v);

		return true;
	}

	/**
	 * returns towns
	 */
	@Override
	public Set<Town> vertexSet() {

		return towns;
	}

	/**
	 * priority queue
	 */
	private class EntryPQ implements Comparable<EntryPQ> {
		Town town;
		int cost;
		Town predecessor;

		EntryPQ(Town town, int cost, Town predecessor) {
			this.town = town;
			this.cost = cost;
			this.predecessor = predecessor;
		}

		@Override
		public int compareTo(EntryPQ other) {
			return Integer.compare(this.cost, other.cost);
		}
	}

	/**
	 * disjkstra algorithm based on textbook
	 */
	@Override
	public void dijkstraShortestPath(Town originTown) {
		prev.clear();
		pWeights.clear();

		boolean done = false;
		PriorityQueue<EntryPQ> priorityQueue = new PriorityQueue<>();
		Set<Town> visitedTowns = new HashSet<>();
		priorityQueue.add(new EntryPQ(originTown, 0, null));

		while (!done && !priorityQueue.isEmpty()) {
			EntryPQ frontEntry = priorityQueue.remove();
			Town frontTown = frontEntry.town;

			if (!visitedTowns.contains(frontTown)) {
				visitedTowns.add(frontTown);

				pWeights.put(frontTown, frontEntry.cost);

				if (frontEntry.predecessor != null) {
					prev.put(frontTown, frontEntry.predecessor);
				}

				if (frontTown.equals(originTown) && pWeights.size() == towns.size()) {
					done = true;
				} else {
					Iterator<Town> neighborIterator = frontTown.getAdjTowns().iterator();
					while (neighborIterator.hasNext()) {
						Town nextNeighbor = neighborIterator.next();

						Road connectingRoad = getEdge(frontTown, nextNeighbor);
						int weightOfEdgeToNeighbor = (connectingRoad != null) ? connectingRoad.getWeight() : 1;

						if (!visitedTowns.contains(nextNeighbor)) {
							int nextCost = weightOfEdgeToNeighbor + pWeights.get(frontTown);

							priorityQueue.add(new EntryPQ(nextNeighbor, nextCost, frontTown));
						}
					}
				}
			}
		}
	}

	/**
	 * calls dijkstra algorithm
	 */
	@Override
	public ArrayList<String> shortestPath(Town source, Town destination) {
		dijkstraShortestPath(source);

		if (!prev.containsKey(destination) && !source.equals(destination)) {
			return null;
		}

		LinkedList<Town> pathStack = new LinkedList<>();
		pathStack.push(destination);

		Town currentTown = destination;
		while (prev.containsKey(currentTown)) {
			currentTown = prev.get(currentTown);
			pathStack.push(currentTown);
		}

		ArrayList<String> result = new ArrayList<>();
		while (pathStack.size() > 1) {
			Town from = pathStack.pop();
			Town to = pathStack.peek();

			Road road = getEdge(from, to);
			String roadName = (road != null) ? road.getName() : "unknown";
			int roadWeight = (road != null) ? road.getWeight() : 0;

			result.add(from.getName() + " via " + roadName + " to " + to.getName() + " " + roadWeight + " mi");
		}

		return result;
	}
}

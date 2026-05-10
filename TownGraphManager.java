import java.util.*;
import java.io.*;

/**
 * town graph manager
 */
public class TownGraphManager implements TownGraphManagerInterface {
	private Graph graph = new Graph();

	/**
	 * add road
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town t1 = getTownObj(town1);
		Town t2 = getTownObj(town2);

		if (t1 == null || t2 == null) {
			return false;
		}

		graph.addEdge(t1, t2, weight, roadName);
		return true;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	private Town getTownObj(String name) {
		for (Town town : graph.vertexSet()) {
			if (town.getName().equals(name)) {
				return town;
			}
		}
		return null;
	}

	/**
	 * returns road name
	 */
	@Override
	public String getRoad(String town1, String town2) {
		Town t1 = getTownObj(town1);
		Town t2 = getTownObj(town2);

		if (t1 == null || t2 == null) {
			return null;
		}

		Road road = graph.getEdge(t1, t2);

		if (road == null) {
			return null;
		}

		return road.getName();
	}

	/**
	 * adds vertex
	 */
	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}

	/**
	 * checks in contains a vertex
	 */
	@Override
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}

	/**
	 * check if contains a road
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town t1 = getTownObj(town1);
		Town t2 = getTownObj(town2);

		if (t1 == null || t2 == null) {
			return false;
		}

		return graph.containsEdge(t1, t2);
	}

	/**
	 * returns all roads
	 */
	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> roads = new ArrayList<>();

		for (Road road : graph.edgeSet()) {
			roads.add(road.getName());
		}

		Collections.sort(roads);
		return roads;
	}

	/**
	 * deletes road
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town t1 = getTownObj(town1);
		Town t2 = getTownObj(town2);

		if (t1 == null || t2 == null) {
			return false;
		}

		Road r = graph.getEdge(t1, t2);

		if (r == null) {
			return false;
		}

		graph.removeEdge(t1, t2, r.getWeight(), r.getName());
		return true;
	}

	/**
	 * deletes vertex
	 */
	@Override
	public boolean deleteTown(String v) {
		Town t = getTownObj(v);

		if (t == null) {
			return false;
		}

		return graph.removeVertex(t);
	}

	/**
	 * returns all towns
	 */
	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> towns = new ArrayList<>();

		for (Town town : graph.vertexSet()) {
			towns.add(town.getName());
		}

		Collections.sort(towns);
		return towns;
	}

	/**
	 * returns path arraylist
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Town t1 = getTownObj(town1);
		Town t2 = getTownObj(town2);

		if (t1 == null || t2 == null) {
			return new ArrayList<>();
		}

		ArrayList<String> path = graph.shortestPath(t1, t2);

		if (path == null) {
			return new ArrayList<>();
		}

		return path;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Town getTown(String name) {
		return getTownObj(name);
	}

	/**
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void populateTownGraph(File file) throws FileNotFoundException, IOException {
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine().trim();
			if (line.isEmpty())
				continue;

			String[] parts = line.split(";");
			String[] roadInfo = parts[0].split(",");
			String[] townInfo = parts[1].split(",");

			String roadName = roadInfo[0];
			int weight = Integer.parseInt(roadInfo[1]);
			String town1 = townInfo[0];
			String town2 = townInfo[1];

			addTown(town1);
			addTown(town2);
			addRoad(town1, town2, weight, roadName);
		}
		scanner.close();
	}
}

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class GraphStudentTest {
	private Graph graph;
	private Town t1, t2, t3;

	@Before
	public void setUp() {
		graph = new Graph();
		t1 = new Town("Town1");
		t2 = new Town("Town2");
		t3 = new Town("Town3");
		graph.addVertex(t1);
		graph.addVertex(t2);
		graph.addVertex(t3);
		graph.addEdge(t1, t2, 3, "Road1");
		graph.addEdge(t2, t3, 4, "Road2");
	}

	@Test
	public void testAddVertex() {
		assertTrue(graph.containsVertex(t1));
	}

	@Test
	public void testAddEdge() {
		assertTrue(graph.containsEdge(t1, t2));
	}

	@Test
	public void testGetEdge() {
		assertEquals(new Road(t1, t2, 3, "Road1"), graph.getEdge(t1, t2));
	}

	@Test
	public void testRemoveVertex() {
		graph.removeVertex(t1);
		assertFalse(graph.containsVertex(t1));
	}

	@Test
	public void testRemoveEdge() {
		graph.removeEdge(t1, t2, 3, "Road1");
		assertFalse(graph.containsEdge(t1, t2));
	}

	@Test
	public void testShortestPath() {
		ArrayList<String> path = graph.shortestPath(t1, t3);
		assertFalse(path.isEmpty());
	}

	@Test
	public void testDisjointGraph() {
		Town isolated = new Town("Town4");
		graph.addVertex(isolated);
		ArrayList<String> path = graph.shortestPath(t1, isolated);
		assertTrue(path == null || path.isEmpty());
	}
}
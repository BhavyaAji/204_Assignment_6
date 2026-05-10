import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class TownGraphManagerStudentTest {
	private TownGraphManager manager;

	@Before
	public void setUp() {
		manager = new TownGraphManager();
		manager.addTown("Town1");
		manager.addTown("Town2");
		manager.addTown("Town3");
		manager.addRoad("Town1", "Town2", 3, "Road1");
		manager.addRoad("Town2", "Town3", 4, "Road2");
	}

	@Test
	public void testAddTown() {
		assertTrue(manager.containsTown("Town1"));
	}

	@Test
	public void testAddRoad() {
		assertTrue(manager.containsRoadConnection("Town1", "Town2"));
	}

	@Test
	public void testGetRoad() {
		assertEquals("Road1", manager.getRoad("Town1", "Town2"));
	}

	@Test
	public void testAllTowns() {
		ArrayList<String> towns = manager.allTowns();
		assertTrue(towns.contains("Town1"));
		assertTrue(towns.contains("Town2"));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = manager.allRoads();
		assertTrue(roads.contains("Road1"));
		assertTrue(roads.contains("Road2"));
	}

	@Test
	public void testDeleteTown() {
		manager.deleteTown("Town1");
		assertFalse(manager.containsTown("Town1"));
	}

	@Test
	public void testDeleteRoad() {
		manager.deleteRoadConnection("Town1", "Town2", "Road1");
		assertFalse(manager.containsRoadConnection("Town1", "Town2"));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = manager.getPath("Town1", "Town3");
		assertFalse(path.isEmpty());
	}

	@Test
	public void testDisjointGraph() {
		manager.addTown("Town99");
		ArrayList<String> path = manager.getPath("Town1", "Town99");
		assertFalse(path.size() > 0);
	}
}
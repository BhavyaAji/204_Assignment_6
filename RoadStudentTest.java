import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RoadStudentTest {
	private Town t1, t2;
	private Road road;

	@Before
	public void setUp() {
		t1 = new Town("Town1");
		t2 = new Town("Town2");
		road = new Road(t1, t2, 5, "Road1");
	}

	@Test
	public void testGetName() {
		assertEquals("Road1", road.getName());
	}

	@Test
	public void testGetWeight() {
		assertEquals(5, road.getWeight());
	}

	@Test
	public void testGetSource() {
		assertEquals(t1, road.getSource());
	}

	@Test
	public void testGetDestination() {
		assertEquals(t2, road.getDestination());
	}

	@Test
	public void testContains() {
		assertTrue(road.contains(t1));
		assertTrue(road.contains(t2));
	}

	@Test
	public void testEquals() {
		Road same = new Road(t1, t2, 5, "Road1");
		assertTrue(road.equals(same));
	}

	@Test
	public void testEqualsReversed() {
		Road reversed = new Road(t2, t1, 5, "Road1");
		assertTrue(road.equals(reversed));
	}
}
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TownStudentTest {
	private Town t1, t2;

	@Before
	public void setUp() {
		t1 = new Town("Town1");
		t2 = new Town("Town2");
	}

	@Test
	public void testGetName() {
		assertEquals("Town1", t1.getName());
	}

	@Test
	public void testAddAdjTown() {
		t1.addAdjTown(t2);
		assertTrue(t1.getAdjTowns().contains(t2));
	}

	@Test
	public void testEquals() {
		Town same = new Town("Town1");
		assertTrue(t1.equals(same));
	}

	@Test
	public void testNotEquals() {
		assertFalse(t1.equals(t2));
	}

	@Test
	public void testCompareTo() {
		assertTrue(t1.compareTo(t2) < 0);
	}
}
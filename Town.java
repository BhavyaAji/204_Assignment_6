import java.util.*;
/**
 * town
 */
public class Town implements Comparable<Town> {
	private String name;
	private Set<Town> adjacentTowns;

	/**
	 * 
	 * @param name
	 */
	public Town(String name) {
		this.name = name;
		adjacentTowns = new HashSet<>();
	}

	/**
	 * 
	 * @param template
	 */
	public Town(Town template) {
		this.name = template.getName();
		this.adjacentTowns = new HashSet<Town>(template.adjacentTowns);
	}

	/**
	 * compare to
	 */
	@Override
	public int compareTo(Town o) {
		return name.compareTo(o.getName());
	}

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param adjTown
	 */
	public void addAdjTown(Town adjTown) {
		adjacentTowns.add(adjTown);
	}

	/**
	 * 
	 * @return set of adjacent towns
	 */
	public Set<Town> getAdjTowns() {
		return adjacentTowns;
	}

	/**
	 * tostring
	 */
	@Override
	public String toString() {
		return "Town [name=" + name + ", adjacentTowns=" + adjacentTowns + "]";
	}

	/**
	 * hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	/**
	 * equals method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Town other = (Town) obj;
		return Objects.equals(name, other.name);
	}

}

import java.util.Objects;

/**
 * graph
 */
public class Road implements Comparable<Road> {

	private Town source, destination;
	private String name;
	private int weight;

	/**
	 * 
	 * @param source
	 * @param destination
	 * @param weight
	 * @param name
	 */
	public Road(Town source, Town destination, int weight, String name) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.name = name;
	}

	/**
	 * 
	 * @param source
	 * @param destination
	 * @param name
	 */
	public Road(Town source, Town destination, String name) {
		this(source, destination, 1, name);
	}

	/**
	 * compare to method
	 */
	@Override
	public int compareTo(Road o) {
		return name.compareTo(o.name);
	}

	/**
	 * 
	 * @param town
	 * @return bool
	 */
	public boolean contains(Town town) {
		if (source.equals(town) || destination.equals(town)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return source
	 */
	public Town getSource() {
		return source;
	}

	/**
	 * 
	 * @return destination
	 */
	public Town getDestination() {
		return destination;
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
	 * @return weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * set source
	 * 
	 * @param source
	 */
	public void setSource(Town source) {
		this.source = source;
	}

	/**
	 * set destination
	 * 
	 * @param destination
	 */
	public void setDestination(Town destination) {
		this.destination = destination;
	}

	/**
	 * set name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * set weight
	 * 
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "Road [source=" + source + ", destination=" + destination + ", name=" + name + ", weight=" + weight
				+ "]";
	}

	/**
	 * hash code
	 */
	@Override
	public int hashCode() {
		return Objects.hash(destination, source);
	}

	/**
	 * equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Road other = (Road) obj;
		return (Objects.equals(destination, other.destination) && Objects.equals(source, other.source))
				|| (Objects.equals(destination, other.source) && Objects.equals(source, other.destination));
	}

}

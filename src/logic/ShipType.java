package logic;

/**
 * Enumeration of types of ships and their corresponding lengths
 * 
 */
public enum ShipType {
	
	AIRCRAFT(5),
	BATTLESHIP(4),
	DESTROYER(3),
	SUBMARINE(3),
	PATROL(2);

	private int length;

	/**
	 * Constructor of ship type
	 * 
	 * @param length
	 * 			length of type of ship
	 */
	private ShipType(int length) {
		this.length = length;
	}

	/**
	 * Returns ship type's length
	 * 
	 * @return length
	 */
	public int length() {
		return length;
	}

}

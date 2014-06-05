package logic;

public enum ShipType {
	
	AIRCRAFT_CARRIER(5),
	BATTLESHIP(4),
	DESTROYER(3),
	SUBMARINE(3),
	PATROL_BOAT(2);

	private int length;

	private ShipType(int length) {
		this.length = length;
	}

	public int length() {
		return length;
	}

}

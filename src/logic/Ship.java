package logic;

public class Ship {

	private int numHits = 0;

	
	private ShipType type;

	
	public Ship(ShipType type) {
		this.type = type;
	}


	public ShipType type() {
		return type;
	}

	
	public HitStatus hit() {
		numHits++;
		if (type.length() <= numHits) {
			return HitStatus.SUNK;
		}
		return HitStatus.HIT;
	}
}

package logic;

public class Ship {

	private int numHits = 0;
	private boolean hit = false;

	
	private ShipType type;

	/**
	 * Constructor of ship
	 * 
	 * @param type  
	 * 			type of ship to be constructed
	 */
	public Ship(ShipType type) {
		this.type = type;
	}

	/**
	 * Returns ship's type
	 * 
	 * @return type of ship
	 */
	public ShipType type() {
		return type;
	}

	/**
	 * Returns the status of the ship after being hit
	 * 
	 * @return ship's status after being hit (can either be hit or sunk)
	 */
	public HitStatus hit() {
		numHits++;
		if (type.length() <= numHits) {
			return HitStatus.SUNK;
		}
		return HitStatus.HIT;
	}


	/**
	 * Sets hit variable of ship
	 * 
	 * @param b 
	 * 			set as true if ship has been hit
	 */
	public void setHit(boolean b) {
		this.hit = b;
		
	}

	/**
	 * Returns true if ship has been hit
	 * 
	 * @return true/false
	 */
	public boolean getHit() {
		return hit;
	}
}

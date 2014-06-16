package logic;

public class GridSpace {
	
	private Ship ship;

	private boolean visible;

	/**
	 * Constructor for GridSpace
	 */
	public GridSpace() {
		this.ship = null;
		this.visible = false;
	}

	/**
	 * Returns true if space of grid has already been played
	 * 
	 * @return true/false
	 */
	public boolean isVisible() {
		return this.visible;
	}

	/**
	 * Sets the grid space as already played
	 */
	public void makeVisible() {
		this.visible = true;
	}

	
	/**
	 * Returns true if grid space is occupied by a ship
	 * 
	 * @return true/false
	 */
	public boolean hasShip() {
		return null != this.ship;
	}


	/**
	 * Returns ship that occupies this grid space
	 * 
	 * @return ship
	 */
	public Ship getShip() {
		return this.ship;
	}


	/**
	 * Sets a ship as occupant of grid space
	 * 
	 * @param s
	 *			ship that occupies this position
	 */
	public void setShip(Ship s) {
		this.ship = s;
	}
}

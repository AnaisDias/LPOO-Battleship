package logic;

public class GridSpace {
	
	private Ship ship;

	private boolean visible;

	public GridSpace() {
		this.ship = null;
		this.visible = false;
	}

	
	public boolean isVisible() {
		return this.visible;
	}

	
	public void makeVisible() {
		this.visible = true;
	}

	
	public boolean hasShip() {
		return null != this.ship;
	}


	public Ship getShip() {
		return this.ship;
	}


	public void setShip(Ship s) {
		this.ship = s;
	}
}

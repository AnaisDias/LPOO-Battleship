package logic;

import logic.HitStatus;

public final class Board {


	private static final int BOARD_L = 10;

	private int numShips = 0;

	public final GridSpace[][] gridSpaces;
	
	private boolean isActive = false;
	
	private final Player owningPlayer;


	/**
	 * Constructor of board for current player
	 * 
	 * @param owner
	 *			player that owns the board being constructed
	 */
	public Board(Player owner) {
		gridSpaces = new GridSpace[BOARD_L][BOARD_L];
		this.owningPlayer = owner;

		for (int i = 0; i < BOARD_L; i++) {
			for (int j = 0; j < BOARD_L; j++) {
				gridSpaces[i][j] = new GridSpace();
			}
		}

	}

	
	/**
	 * Sets the position of a ship and sets positions as occupied by a ship
	 * 
	 * @param ship 
	 * 			ship to place
	 * @param x
	 *			coordinate X of the position
	 * @param y
	 * 			coordinate Y of the position
	 * @param orient
	 * 			orientation of ship to be placed
	 */
	public void setShipPos(Ship ship, int x, int y, boolean orient) {

		int lengthOfShip = ship.type().length();

		
		if (orient) {
			for (int i = 0; i < lengthOfShip; i++) {
				gridSpaces[x + i][y].setShip(ship);
			}
		} else {
			for (int i = 0; i < lengthOfShip; i++) {
				gridSpaces[x][y + i].setShip(ship);
			}
		}
		numShips++;

	
	}

	/**
	 * Shoots at a certain position
	 * 
	 * @param x
	 *			coordinate X of the position
	 * @param y
	 * 			coordinate Y of the position
	 * @return the result of the shot (hit, miss or sunk)
	 */
	public HitStatus shoot(int x, int y) {
		HitStatus shotStatus = HitStatus.MISS;

		if (gridSpaces[x][y].hasShip() && !gridSpaces[x][y].isVisible()) {
			Ship s = gridSpaces[x][y].getShip();
			shotStatus = s.hit();

			if (HitStatus.SUNK == shotStatus) {
				numShips--;
			}
		}

		gridSpaces[x][y].makeVisible();

		return shotStatus;
	}

	/**
	 * Returns true if the position has already been played and is viewable
	 * 
	 * @param x
	 *			coordinate X of the position
	 * @param y
	 * 			coordinate Y of the position
	 * @return true/false
	 */
	public boolean isViewable(int x, int y) {
		return gridSpaces[x][y].isVisible();
	}

	/**
	 * Returns true if the position is occupied by a ship
	 * 
	 * @param x
	 *			coordinate X of the position
	 * @param y
	 * 			coordinate Y of the position
	 * @return true/false
	 */
	public boolean isShip(int x, int y) {
		return gridSpaces[x][y].hasShip();
	}

	/**
	 * Returns the type of the ship that occupies the specified position
	 * 
	 * @param x
	 *			coordinate X of the position
	 * @param y
	 * 			coordinate Y of the position
	 * @return type of ship at position
	 */
	public ShipType shipTypeAt(int x, int y) {
		return gridSpaces[x][y].getShip().type();
	}

	
	/**
	 * Returns true if there are still ships that have not been destroyed
	 * 
	 * @return true/false
	 */
	public boolean shipsRemaining() {
		return numShips > 0;
	}


	/**
	 * Returns true if position is within the playing grid's bounds
	 * 
	 * @param x
	 *			coordinate X of the position
	 * @param y
	 * 			coordinate Y of the position
	 * @return true/false
	 */
	public boolean boundsCheck(int x, int y) {
		return x < BOARD_L && y < BOARD_L && x >= 0 && y >= 0;
	}


	/**
	 * Returns size of grid
	 * 
	 * @return size of grid
	 */
	public int gridSize() {
		return BOARD_L;
	}

	
	/**
	 * Returns player that owns this board
	 *
	 * @return owningPlayer
	 */
	public Player getPlayer() {
		return this.owningPlayer;
	}

	/**
	 * Sets state of player as active or inactive
	 * 
	 * @param state
	 *			player's state (true means the player is active)
	 */
	public void setPlayerState(boolean state) {
		isActive = state;
	}

	
	/**
	 * Returns player's state (returns true if player is active)
	 * 
	 * @return true/false
	 */
	public boolean getPlayerState() {
		return isActive;
	}

	
	/**
	 * Returns number of ships in the board
	 * 
	 * @return number of ships
	 */
	public Object getNumShips() {

		return numShips;
	}

	
	
}

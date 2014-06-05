package logic;

import java.util.logging.Logger;

import logic.HitStatus;

public final class Board {

	
	//private static final Logger LOGGER = Logger.getLogger(Board.class.getName());


	private static final int BOARD_L = 10;

	private int numShips = 0;

	
	private final GridSpace[][] gridSpaces;

	
	private boolean isActive = false;

	
	private final Player owningPlayer;

	
	public Board(Player owner) {
		gridSpaces = new GridSpace[BOARD_L][BOARD_L];
		this.owningPlayer = owner;

		for (int i = 0; i < BOARD_L; i++) {
			for (int j = 0; j < BOARD_L; j++) {
				gridSpaces[i][j] = new GridSpace();
			}
		}
		//LOGGER.finest("Created BattleGrid.");
	}

	public void setShipPos(Ship ship, int x, int y, boolean orient) {

		int lengthOfShip = ship.type().length();

		// Updates each space of the board, and tells it that a ship now
		// occupies it.
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

		//LOGGER.fine("Set ship at (" + x + "," + y + ")");
	}

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

		//LOGGER.fine("Shot at (" + x + "," + y + "). Hit? "+ shotStatus.toString());
		return shotStatus;
	}

	public boolean isViewable(int x, int y) {
		return gridSpaces[x][y].isVisible();
	}

	public boolean isShip(int x, int y) {
		return gridSpaces[x][y].hasShip();
	}

	public ShipType shipTypeAt(int x, int y) {
		return gridSpaces[x][y].getShip().type();
	}

	public boolean shipsRemaining() {
		return numShips > 0;
	}


	public boolean boundsCheck(int x, int y) {
		return x < BOARD_L && y < BOARD_L && x >= 0 && y >= 0;
	}


	public int gridSize() {
		return BOARD_L;
	}

	
	public Player getPlayer() {
		return this.owningPlayer;
	}

	
	public void setPlayerState(boolean state) {
		isActive = state;
	}

	
	public boolean getPlayerState() {
		return isActive;
	}

	
	
}

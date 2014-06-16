package graphics.multiplayer;


import graphics.Animations;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import controllers.FireControl;
import controllers.PlacementControl;

import logic.Board;
import logic.HitStatus;

public final class BattleGridSquare extends JPanel {

	private static final long serialVersionUID = 1L;

	private int x;
	private int y;

	private static final Color FOG = new Color(171, 185, 192);
	private static final Color HIT = new Color(248, 105, 35);
	private static final Color WATER = new Color(78, 170, 215);
	private static final Color SHIP = new Color(97, 108, 118);
	private static final Color MISS = new Color(164, 207, 228);
	public static final Color LABEL_COLOR = Color.WHITE;

	public BattleGridSquare(int x, int y) {
		this.x = x;
		this.y = y;

	}

	//click listener do place ship
	public void addPlacementClickListener(final BattleGrid gView, final PlacementControl con, final Board grid) {

		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				boolean placementSuccess = con.setShipPos(x, y);

				if (placementSuccess) {
					con.disableSelectedShipType();
					gView.redrawSquareBackgrounds(grid);
				}
			}
		});
	}

	//Fire click listener
	public void addFiringClickListener(final BattleGrid gView,
			final FireControl con, final Board grid) {

		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (con.isShootable(x, y)) {
					HitStatus shotSuccess = con.fireShot(x, y);

					BattleGridSquare.this.setSquareBackground(grid);

					con.endTurn();
				} 
			}
		});
	}

	public void setSquareBackground(Board grid) {
		Color currentColor;

		if (grid.getPlayerState()) { // active player owns this grid.

			if (grid.isShip(this.x, this.y) && grid.isViewable(this.x, this.y)) {
				currentColor = BattleGridSquare.HIT;
			} else if (grid.isShip(this.x, this.y)) {
				currentColor = BattleGridSquare.SHIP;
			} else if (grid.isViewable(this.x, this.y)) {
				currentColor = BattleGridSquare.MISS;
			} else {
				currentColor = BattleGridSquare.WATER;
			}

		} else if (grid.isViewable(this.x, this.y)) {

			if (grid.isShip(this.x, this.y)) {
				currentColor = BattleGridSquare.HIT;
			} else {
				currentColor = BattleGridSquare.WATER;
			}

		} else {
			currentColor = BattleGridSquare.FOG;
		}

		this.setBackground(currentColor);
}

	public void setSquareImage(Graphics2D g2d, Board grid) {
		Animations ani = new Animations();
		if (grid.getPlayerState()) { // active player owns this grid.

			if (grid.isShip(this.x, this.y) && grid.isViewable(this.x, this.y)) {
				ani.drawExplosion(g2d, grid.gridSpaces[x][y].getShip(), x, y);
			} else if (grid.isShip(this.x, this.y)) {
				//draw ship
			} else if (grid.isViewable(this.x, this.y)) {
				ani.drawWaterHit(g2d, x, y);
			} else {
				ani.drawWater(g2d, x, y);
			}

		} else if (grid.isViewable(this.x, this.y)) {

			if (grid.isShip(this.x, this.y)) {
				ani.drawExplosion(g2d, grid.gridSpaces[x][y].getShip(), x, y);
			} else {
				ani.drawWater(g2d, x, y);
			}

		} 
		
	}

		
	}


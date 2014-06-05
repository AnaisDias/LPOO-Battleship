package graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logic.ShipType;

import controllers.PlacementControl;


public final class MouseSelectShipType extends MouseAdapter {

	
	private PlacementControl pl;



	private ShipType t;

	public MouseSelectShipType(PlacementControl pc, ShipType s) {
		this.pl = pc;
		this.t = s;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.pl.setSelectedShipType(this.t);
	}
}

package graphics.multiplayer;


import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.PlacementControl;



import logic.ShipType;

public final class ShipTypeSelector extends JPanel {

	private static final long serialVersionUID = 1L;

	private Map<ShipType, JButton> buttonMap;

	private Color originalButtonColor;
	private static final Color HIT = new Color(248, 105, 35);

	/**
	 * This constructor creates the basic JPanel for the grid during the set-up
	 * phase, containing the initial ships.
	 * 
	 */
	public ShipTypeSelector(PlacementControl pc) {
		pc.registerShipTypeSelector(this);
		buttonMap = new HashMap<ShipType, JButton>();

		this.add(new JLabel("Ships: "));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		for (ShipType s : ShipType.values()) {
			JButton ship = new JButton(s.toString() + " " + s.length());
			if (originalButtonColor == null) {
				originalButtonColor = ship.getBackground();
			}
			ship.addMouseListener(new MouseSelectShipType(pc, s));
			this.add(ship);
			ship.setBackground(HIT);
			ship.setContentAreaFilled(false);
			ship.setOpaque(true);
			buttonMap.put(s, ship);
		}

		this.setActiveButton(ShipType.AIRCRAFT);
	}
	
	

	/**
	 * This method removes the capacity to select the button corresponding to a
	 * particular ship after it has been placed.
	 */
	public void disableShipButton(ShipType s) {
		buttonMap.get(s).setEnabled(false);
	}

	/**
	 * This method highlights the button corresponding to a particular ship
	 * after it has been activated.
	 */
	public void setActiveButton(ShipType s) {
		for (JButton b : buttonMap.values()) {
			b.setBackground(originalButtonColor);
		}
		buttonMap.get(s).setBackground(originalButtonColor.brighter());
	}
}

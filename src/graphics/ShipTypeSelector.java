package graphics;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.PlacementControl;



import logic.ShipType;

public final class ShipTypeSelector extends JPanel {

	
	//private static final Logger LOGGER = Logger.getLogger(ShipTypeSelector.class.getName());

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 1L;

	private Map<ShipType, JButton> buttonMap;

	private Color originalButtonColor;

	/**
	 * This constructor creates the basic JPanel for the grid during the set-up
	 * phase, containing the initial ships.
	 * 
	 */
	public ShipTypeSelector(PlacementControl pc) {
		pc.registerShipTypeSelector(this);
		buttonMap = new HashMap<ShipType, JButton>();

		this.add(new JLabel("Ships"));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		for (ShipType s : ShipType.values()) {
			JButton ship = new JButton(s.toString());
			if (originalButtonColor == null) {
				originalButtonColor = ship.getBackground();
			}
			ship.addMouseListener(new MouseSelectShipType(pc, s));
			this.add(ship);
			buttonMap.put(s, ship);
		}

		this.setActiveButton(ShipType.AIRCRAFT_CARRIER);
		//LOGGER.finest("Created ShipTypeSeletor.");
	}

	/**
	 * This method removes the capacity to select the button corresponding to a
	 * particular ship after it has been placed.
	 */
	public void disableShipButton(ShipType s) {
		buttonMap.get(s).setEnabled(false);
		//LOGGER.finer("Disabled " + s.toString() + " Button");
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
		//LOGGER.finer("Highlighting " + s.toString() + " Button");
	}
}

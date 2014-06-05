package graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.PlacementControl;

public class OrientationSelector extends JPanel {

	//private static final Logger LOGGER = Logger.getLogger(OrientationSelector.class.getName());

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 1L;

	private final PlacementControl controller;

	private boolean orientation;

	private final JLabel label;

	private final JButton button;

	private final String northSouthLabelText = "North/South Placement";

	private final String eastWestLabelText = "East/West Placement";

	private final String northSouthButtonText = "Change to North/South";

	private final String eastWestButtonText = "Change to East/West";

	
	public OrientationSelector(PlacementControl con) {
		this.controller = con;
		this.orientation = true;

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		label = new JLabel(eastWestLabelText);
		this.add(label);

		button = new JButton(northSouthButtonText);
		this.add(button);

		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				OrientationSelector.this.controller.rotateShip();

				OrientationSelector.this.orientation = 
					!OrientationSelector.this.orientation;

				if (orientation) {
					OrientationSelector.this.button
							.setText(northSouthButtonText);
					OrientationSelector.this.label.setText(eastWestLabelText);
				} else {
					OrientationSelector.this.button.setText(eastWestButtonText);
					OrientationSelector.this.label.setText(northSouthLabelText);
				}

				//LOGGER.finest("orientation set to : "+ OrientationSelector.this.orientation);
			}
		});

		//LOGGER.finer("OrientationSelector created.");

	}
}

package graphics;

import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import logic.Board;

import controllers.PlacementControl;

public final class PlacementView extends JPanel {

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 1L;
	
	
	//private static final Logger LOGGER = Logger.getLogger(PlacementView.class.getName());

	/**
	 * Constructor which generates a PlacementView for placing Ships on the
	 * active grid.
	 */
	public PlacementView(PlacementControl placing, Board activeGrid) {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(new BattleGrid(placing, activeGrid));

		JPanel rightSide = new JPanel();
		this.add(rightSide);
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		rightSide.add(new ShipTypeSelector(placing));
		rightSide.add(new OrientationSelector(placing));

		//LOGGER.finer("Placing on grid " + activeGrid.toString());
	}

}
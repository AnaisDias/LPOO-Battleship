package graphics.multiplayer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import logic.Board;

import controllers.PlacementControl;

public final class PlacementView extends JPanel {

	private static final long serialVersionUID = 1L;

	
	public PlacementView(PlacementControl placing, Board activeGrid) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(new BattleGrid(placing, activeGrid));

		JPanel rightSide = new JPanel();
		this.add(rightSide);
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.LINE_AXIS));
		rightSide.add(new ShipTypeSelector(placing));
		rightSide.add(new OrientationSelector(placing));
		
	}

}
package graphics;

import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


import controllers.FireControl;
import controllers.WaitControl;

public final class FiringView extends JPanel {
    
   // private static final Logger LOGGER = Logger.getLogger(FiringView.class.getName());

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor which generates a view for one player firing at the other
	 * player's BattleGrid.
	 */
	public FiringView(WaitControl waiting, FireControl firing) {
	    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.add(new BattleGrid(firing));
	    this.add(new BattleGrid(waiting.getActiveGrid()));

	   // LOGGER.finer("Created Firing View.");
	}
}

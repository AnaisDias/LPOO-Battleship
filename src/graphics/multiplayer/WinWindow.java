package graphics.multiplayer;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.WaitControl;


public final class WinWindow extends JPanel {
	private static final long serialVersionUID = 1L;

	private WaitControl controller;

	private static final int RIGID_DIM = 20;

	private static final int HGAP = 0;

	private static final int VGAP = 10;

	/**
	 * Constructor which generates the WinView, initialized with the given
	 * WaitControl and win message.
	 */
	public WinWindow(WaitControl con, String winMessage) {
		
		this.controller = con;

		this.setLayout(new BorderLayout(HGAP, VGAP));

		JPanel topContainer = new JPanel();
		topContainer.setLayout(new GridBagLayout());

		topContainer.add(Box.createRigidArea(new Dimension(0, RIGID_DIM)));

		JLabel victoryMessage = new JLabel(winMessage);
		topContainer.add(victoryMessage, new GridBagConstraints());

		JPanel bottomContainer = new JPanel();
		bottomContainer.setLayout(new BoxLayout(bottomContainer,
				BoxLayout.X_AXIS));

		bottomContainer
				.add(new BattleGrid(this.controller.getActiveGrid()));
		bottomContainer.add(Box.createRigidArea(new Dimension(RIGID_DIM, 0)));
		bottomContainer.add(new BattleGrid(this.controller
				.getInactiveGrid()));

		this.add(topContainer, BorderLayout.NORTH);
		this.add(bottomContainer, BorderLayout.CENTER);

		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	}

}
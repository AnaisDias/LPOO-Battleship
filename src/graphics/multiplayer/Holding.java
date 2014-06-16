package graphics.multiplayer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.WaitControl;



public final class Holding extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE_SUFFIX = " click when Ready";

	private static final int WIDTH = 200;

	private static final int HEIGHT = 100;
	
	private final WaitControl controller;

	private final JLabel label;

	public Holding(WaitControl con, String msg) {
		
		this.controller = con;

		this.setLayout(new GridBagLayout());

		label = new JLabel(this.controller.getInactivePlayer().getPlayerName() + Holding.MESSAGE_SUFFIX);
		label.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		this.add(label, new GridBagConstraints());

		if (null != msg) {
			this.add(new JLabel(msg), new GridBagConstraints());
		}

		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controller.nextScreen(null);
			}
		});

	}
}
package graphics.multiplayer;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.PlacementControl;

public class OrientationSelector extends JPanel {

	private static final long serialVersionUID = 1L;

	private final PlacementControl controller;

	private boolean orientation;

	private final JLabel label;

	private final JButton button;

	private Image rotate;

	public OrientationSelector(PlacementControl con) {
		
		this.controller = con;
		this.orientation = true;

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		loadImage();

		label = new JLabel("--- ROTATION SHIP ---");
		this.add(label);

		button = new JButton();
		this.add(button);
		button.setIcon(new ImageIcon(rotate));
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		


		button.addMouseListener(new MouseAdapter() {
			
			
			public void mouseClicked(MouseEvent e) {

				OrientationSelector.this.controller.rotateShip();

				OrientationSelector.this.orientation = !OrientationSelector.this.orientation;

				}
		});

	}
	
	
	public void loadImage()
	{
		try{
			
			rotate = ImageIO.read(new File("pics/rotate.png"));
			
			
			}catch(IOException e)
			{
				System.out.println("Image loading failed");
				e.printStackTrace();
			}
	}
	
}

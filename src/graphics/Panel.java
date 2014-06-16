package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

public class Panel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	Image img;
	
	public Panel(Image image)
	{
		this.img = image;
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), 0, 0,
				img.getWidth(null), img.getHeight(null), null);
	}

	public void changeImage(Image image) {
		this.img = image;
	}
	
	

}

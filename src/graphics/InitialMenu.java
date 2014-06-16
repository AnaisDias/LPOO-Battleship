package graphics;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class InitialMenu extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private Panel backImage;
	private Panel btnPlay;
	//private Panel btnScore;

	private Image background;
	private Image play;
	//private Image score;
	
	private StartGameMenu startGame;
	
	public InitialMenu() 
	{
		
		setLayout(null);
		loadImagesInitialMenu();
		
		startGame = new StartGameMenu(this);
		
	}
	
	public void start()
	{
		btnPlay = new Panel(play);
		btnPlay.setBounds(158, 430, 200, 55);
		this.add(btnPlay);
		btnPlay.setOpaque(false);
		btnPlay.setVisible(true);
		btnPlay.repaint();
		btnPlay.addMouseListener(new Mouse());
		
		/*btnScore= new Panel(score);
		btnScore.setBounds(158, 500, 200, 55);
		this.add(btnScore);
		btnScore.setOpaque(false);
		btnScore.setVisible(true);
		btnScore.repaint();
		btnScore.addMouseListener(new Mouse());*/
		
		backImage = new Panel(background);
		backImage.setBounds(0, 0, 501, 801);
		this.add(backImage);
		backImage.setOpaque(false);
		backImage.setVisible(true);
		backImage.repaint();
	}

	private void loadImagesInitialMenu(){
		try{
			
		background = ImageIO.read(new File("pics/MENU1.png"));
		play = ImageIO.read(new File("pics/play.png"));
		//score = ImageIO.read(new File("pics/score.png"));
		
		}catch(IOException e)
		{
			System.out.println("Image loading failed");
			e.printStackTrace();
		}
	}
	
	public class Mouse extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			
			if ((Panel) e.getSource() == btnPlay) {
				btnPlay.setBounds(155, 425, 210, 60);
			}
			
			/*if ((Panel) e.getSource() == btnScore) {
				btnScore.setBounds(155, 495, 210, 60);
			}*/
			
		}

		public void mouseExited(MouseEvent e) {
			
			if ((Panel) e.getSource() == btnPlay)
				btnPlay.setBounds(158, 430, 200, 55);
			
			/*if ((Panel) e.getSource() == btnScore)
				btnScore.setBounds(158, 500, 200, 55);*/
	
		}

		public void mousePressed(MouseEvent e) {

			
			if ((Panel) e.getSource() == btnPlay) {
				removeAll();
				startGame.start();
				add(startGame);
				revalidate();
				repaint();
			}
			
			/*if ((Panel) e.getSource() == btnScore) {
				removeAll();
				startGame.start();
				add(startGame);
				revalidate();
				repaint();
			}*/
		}
	}
	


	

	
}

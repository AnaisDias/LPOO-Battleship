package graphics;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.SocketException;


import graphics.Rede.MainView;
import graphics.multiplayer.BattleshipFrame;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import server.Servidor;


public class StartGameMenu extends JPanel{

	private static final long serialVersionUID = 1L;

	protected InitialMenu iMenu;
	private BattleshipFrame multiplayer;
	private Servidor server;
	
	private Panel backImage;
	private Panel goBack;
	private Panel startMult;
	private Panel startRede;
	
	private Image background;
	private Image back;
	private Image mult;
	private Image rede;

	
	public StartGameMenu(InitialMenu panel) {
		
		this.iMenu = panel;

		setVisible(true);
		setBounds(0, 0, 501, 801);
		setLayout(null);
		
		multiplayer = new BattleshipFrame();
		loadStartGameImages();
		
	}

	private void loadStartGameImages() {
		try{
			
			background = ImageIO.read(new File("pics/MENU1.png"));
			back = ImageIO.read(new File("pics/back.png"));
			mult = ImageIO.read(new File ("pics/multiplayer.png"));
			rede = ImageIO.read(new File ("pics/rede.png"));
			
			}catch(IOException e)
			{
				System.out.println("Image loading failed");
				e.printStackTrace();
			}
	}



	public void start() {
		goBack = new Panel(back);
		goBack.setBounds(15, 15, 95, 39);
		this.add(goBack);
		goBack.setOpaque(false);
		goBack.setVisible(true);
		goBack.repaint();
		goBack.addMouseListener(new Mouse());
		
		startMult = new Panel(mult);
		startMult.setBounds(158, 430, 200, 55);
		this.add(startMult);
		startMult.setOpaque(false);
		startMult.setVisible(true);
		startMult.repaint();
		startMult.addMouseListener(new Mouse());
		
		startRede = new Panel(rede);
		startRede.setBounds(158, 500, 200, 55);
		this.add(startRede);
		startRede.setOpaque(false);
		startRede.setVisible(true);
		startRede.repaint();
		startRede.addMouseListener(new Mouse());
		
		backImage = new Panel(background);
		backImage.setBounds(0, 0, 501, 801);
		this.add(backImage);
		backImage.setOpaque(false);
		backImage.setVisible(true);
		backImage.repaint();
		
	
		
	}

	private class Mouse extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			
			if ((Panel) e.getSource() == goBack) {
				goBack.setBounds(12, 14, 100, 41);
			}
			if ((Panel) e.getSource() == startMult) {
				startMult.setBounds(155, 425, 210, 60);
				
			}
			if ((Panel) e.getSource() == startRede) {
				startRede.setBounds(155, 495, 210, 60);
				
			}
			
		}

		public void mouseExited(MouseEvent e) {
			
			if ((Panel) e.getSource() == goBack)
				goBack.setBounds(15, 15, 95, 39);

			if ((Panel) e.getSource() == startMult)
				startMult.setBounds(158, 430, 200, 55);
			
			if ((Panel) e.getSource() == startRede)
				startRede.setBounds(158, 500, 200, 55);
				
	
		}

		public void mousePressed(MouseEvent e) {

			
			if ((Panel) e.getSource() == goBack) {
				removeAll();
				revalidate();
				iMenu.removeAll();
				iMenu.start();
				iMenu.revalidate();
				iMenu.repaint();
			}
			
			if ((Panel) e.getSource() == startMult) {
				removeAll();
				multiplayer.start();
				multiplayer.setVisible(true);
				add(multiplayer);
				revalidate();
				repaint();
			}
			
			if ((Panel) e.getSource() == startRede) {
				removeAll();
				
				
			}
			
		}
	}
	
}

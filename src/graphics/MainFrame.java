package graphics;


import java.io.IOException;
import javax.swing.JFrame;


public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	InitialMenu iMenu;

	
	public MainFrame() throws ClassNotFoundException, IOException{
		
		setSize(515,840);
		setTitle("BATTLESHIP v1.0");
		iMenu = new InitialMenu();
		iMenu.start();
		getContentPane().add(iMenu);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
}

package graphics;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.Board;
import logic.Player;

import controllers.FireControl;
import controllers.PlacementControl;
import controllers.WaitControl;


public final class BattleshipFrame extends JFrame {

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 1L;

	
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;

	
	//private static final Logger LOGGER = Logger.getLogger(BattleshipFrame.class.getName());

	private WaitControl waiting;

	private JPanel currentView;

	/**
	 * Default constructor. Initializes Players, BattleGrids, and
	 * WaitControl to kick off Battleship game.
	 */
	public BattleshipFrame() {

		Player playerA = new Player("Player 1");
		Player playerB = new Player("Player 2");
		Board gridA = new Board(playerA);
		Board gridB = new Board(playerB);

		waiting = new WaitControl(this, playerB, gridB, playerA,gridA);


		Dimension dim = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(dim);
		this.pack();
		

		//LOGGER.finer("Created Frame.");
		
		this.setTitle("BATTLESHIP v1.0");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		centeringPanel();
	}

	/**
	 * Creates a view for Ship placement.
	 */
	private void createPlacementView() {
		PlacementControl placing = waiting.switchPlacementPlayer();

		PlacementView view = new PlacementView(placing, waiting.getActiveGrid());

		this.currentView = view;
		this.setContentPane(view);

		//LOGGER.finer("Created Placement View");
	}

	/**
	 * Creates a view for transitioning between Players.
	 */
	private void createWaitingView(String msg) {
		Holding view = new Holding(waiting, msg);

		this.currentView = view;
		this.setContentPane(view);

		//LOGGER.finer("Created Waiting View");
	}

	/**
	 * Creates a view for win Player.
	 */
	private void createWinView(String msg) {
		WinWindow view = new WinWindow(waiting, msg);

		this.currentView = view;
		this.setContentPane(view);

		//LOGGER.finer("Created Win View");
	}

	/**
	 * Creates a view for firing on opponent's grid.
	 */
	private void createFiringView() {
		FireControl firing = waiting.switchFiringPlayer();

		FiringView view = new FiringView(waiting, firing);

		this.currentView = view;
		this.setContentPane(view);

		//LOGGER.finer("Created Firing View");
	}

	/**
	 * Begins a new game by creating the PlacementView.
	 */
	public void start() {
		createPlacementView();
	}

	/**
	 * Switches the current view to nextView.
	 */
	public void switchView(Class nextView, String message) {
		
		this.currentView.setVisible(false);

		if (Holding.class == nextView) {
			createWaitingView(message);

		} else if (PlacementView.class == nextView) {
			createPlacementView();

		} else if (FiringView.class == nextView) {
			createFiringView();

		} else if (WinWindow.class == nextView) {
			createWinView(message);

		}

	}
	
	public void centeringPanel()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	}
}
package graphics;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.FireControl;
import controllers.PlacementControl;

import logic.Board;

public final class BattleGrid extends JPanel {

    /**
     * Serial Version ID.
     */
    private static final long   serialVersionUID = 1L;

   // private static final Logger LOGGER           = Logger.getLogger(BattleGrid.class.getName());

    private final int gridSize;

    private static final int    GRID_GAP         = 3;

    private static final int    RIGID_DIM        = 10;

    private JPanel container;

    /**
     * Constructor that initializes the BattleGrid with the given
     * PlacementControl and Board.
     */
    public BattleGrid(PlacementControl con, Board grid) {
        this.gridSize = grid.gridSize() + 1;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(0, RIGID_DIM)));
        this.add(new JLabel(grid.getPlayer().getPlayerName()));
        this.add(Box.createRigidArea(new Dimension(0, RIGID_DIM)));

        container = new JPanel();
        container.setLayout(new GridLayout(gridSize, gridSize, GRID_GAP, GRID_GAP));
        this.add(container);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {

                if (i > 0 && j > 0) { // the grid
                    BattleGridSquare square = new BattleGridSquare(j - 1, i - 1);
                    square.addPlacementClickListener(this, con, grid);
                    container.add(square);
                    square.setSquareBackground(grid);

                } else if (i == 0 && j == 0) { // blank spot
                    JPanel blank = new JPanel();
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);
                    container.add(blank);

                } else if (j == 0) { // rows
                    JPanel blank = new JPanel();
                    blank.setLayout(new GridBagLayout());
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);

                    blank.add(new JLabel(String.valueOf((char) (((int) 'A') + i - 1))),
                            new GridBagConstraints());

                    container.add(blank);
                } else { // column numbers
                    JPanel blank = new JPanel();
                    blank.setLayout(new GridBagLayout());
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);

                    blank.add(new JLabel(String.valueOf(j)), new GridBagConstraints());

                    container.add(blank);
                }
            }
        }

        //LOGGER.finer("Created BattleGridView for Placement");
    }

    /**
     * Constructor that initializes the BattleGrid with the given
     * FireControl.
     * 
     */
    public BattleGrid(FireControl con) {
        Board targetGrid = con.getTarget();
        this.gridSize = targetGrid.gridSize() + 1;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(0, RIGID_DIM)));
        this.add(new JLabel(targetGrid.getPlayer().getPlayerName()));
        this.add(Box.createRigidArea(new Dimension(0, RIGID_DIM)));

        container = new JPanel();
        container.setLayout(new GridLayout(gridSize, gridSize, GRID_GAP, GRID_GAP));
        this.add(container);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {

                if (i > 0 && j > 0) { // the grid
                    BattleGridSquare square = new BattleGridSquare(j - 1, i - 1);
                    square.addFiringClickListener(this, con, targetGrid);
                    container.add(square);
                    square.setSquareBackground(targetGrid);

                } else if (i == 0 && j == 0) { // blank spot
                    JPanel blank = new JPanel();
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);
                    container.add(blank);

                } else if (j == 0) { // rows
                    JPanel blank = new JPanel();
                    blank.setLayout(new GridBagLayout());
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);

                    blank.add(new JLabel(String.valueOf((char) (((int) 'A') + i - 1))),
                            new GridBagConstraints());

                    container.add(blank);
                } else { // column numbers
                    JPanel blank = new JPanel();
                    blank.setLayout(new GridBagLayout());
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);

                    blank.add(new JLabel(String.valueOf(j)), new GridBagConstraints());

                    container.add(blank);
                }
            }
        }

        //LOGGER.finer("Created BattleGridView for Firing");
    }

    /**
     * Constructor which initializes the BattleGrid with the given
     * Battle.
     * 
     */
    public BattleGrid(Board grid) {
        this.gridSize = grid.gridSize() + 1;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(0, RIGID_DIM)));
        this.add(new JLabel(grid.getPlayer().getPlayerName()));
        this.add(Box.createRigidArea(new Dimension(0, RIGID_DIM)));

        container = new JPanel();
        container.setLayout(new GridLayout(gridSize, gridSize, GRID_GAP, GRID_GAP));
        this.add(container);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (i > 0 && j > 0) { // the grid

                    BattleGridSquare square = new BattleGridSquare(j - 1, i - 1);
                    container.add(square);
                    square.setSquareBackground(grid);

                } else if (i == 0 && j == 0) { // blank spot
                    JPanel blank = new JPanel();
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);
                    container.add(blank);

                } else if (j == 0) { // rows
                    JPanel blank = new JPanel();
                    blank.setLayout(new GridBagLayout());
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);

                    blank.add(new JLabel(String.valueOf((char) (((int) 'A') + i - 1))),
                            new GridBagConstraints());

                    container.add(blank);
                } else { // column numbers
                    JPanel blank = new JPanel();
                    blank.setLayout(new GridBagLayout());
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);

                    blank.add(new JLabel(String.valueOf(j)), new GridBagConstraints());

                    container.add(blank);
                }
            }
        }

        //LOGGER.finer("Created BattleGridView for Viewing");
    }

    /**
     * Updates the background colors for each square in the grid.
     */
    public void redrawSquareBackgrounds(Board grid) {
        for (Component square : this.container.getComponents()) {
            if (square instanceof BattleGridSquare) {
                BattleGridSquare sq = (BattleGridSquare) square;
                sq.setSquareBackground(grid);
            }
        }
       // LOGGER.finest("Redrawing Square Backgrounds.");
    }

}
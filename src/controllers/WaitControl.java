package controllers;

import graphics.BattleshipFrame;
import graphics.FiringView;
import graphics.PlacementView;
import graphics.Holding;
import graphics.WinWindow;

import java.util.ArrayList;


import logic.Board;
import logic.Player;

public final class WaitControl {
   
	//current player
    private Player currentPlayer;
    //inative player
    private Player inactivePlayer;

   //grid owned by current player
    private Board currentGrid;
    
    private BattleshipFrame battleship;

   //grid owned by inactivePlayer
    private Board inactiveGrid;

   //view sequence
    private final ArrayList<Class> viewSequence;

    
    private int sequenceCounter = 0;

    
    private boolean hasWon;

    
    public WaitControl(BattleshipFrame battleship, Player currentPlayer,
            Board currentGrid, Player inactivePlayer, Board inactiveGrid) {
        this.battleship = battleship;

        this.viewSequence = new ArrayList<Class>();
        this.viewSequence.add(PlacementView.class);
        this.viewSequence.add(Holding.class);
        this.viewSequence.add(PlacementView.class);
        this.viewSequence.add(Holding.class);
        this.viewSequence.add(FiringView.class);

        this.currentPlayer = currentPlayer;
        this.currentGrid = currentGrid;
        this.currentGrid.setPlayerState(true);

        this.inactivePlayer = inactivePlayer;
        this.inactiveGrid = inactiveGrid;
    }


    public PlacementControl switchPlacementPlayer() {
        swapPlayersAndGrids();

        return new PlacementControl(this, this.currentGrid);
    }

    public FireControl switchFiringPlayer() {
        swapPlayersAndGrids();

        return new FireControl(this, this.inactiveGrid);
    }

    public void nextScreen(String message) {
        if (hasWon) {
            this.battleship.switchView(WinWindow.class, message);
            
        } else {
            if (sequenceCounter == viewSequence.indexOf(FiringView.class)) {
                sequenceCounter--;
            } else {
                sequenceCounter++;
            }

            this.battleship.switchView(viewSequence.get(sequenceCounter), message);
        }
    }

  
    public Board getActiveGrid() {
        return this.currentGrid;
    }

  
    public Player getActivePlayer() {
        return this.currentPlayer;
    }


    public Board getInactiveGrid() {
        return this.inactiveGrid;
    }

 
    public Player getInactivePlayer() {
        return this.inactivePlayer;
    }

   
    public void setHasWon(boolean b) {
        this.hasWon = b;
        this.inactiveGrid.setPlayerState(true); // so we see both grids as visible.
    }

    
    private void swapPlayersAndGrids() {
        Player tempPlayer = this.currentPlayer;
        Board tempGrid = this.currentGrid;

        this.currentPlayer = this.inactivePlayer;
        this.currentGrid = this.inactiveGrid;
        this.currentGrid.setPlayerState(true);

        this.inactivePlayer = tempPlayer;
        this.inactiveGrid = tempGrid;
        this.inactiveGrid.setPlayerState(false);
    }

}
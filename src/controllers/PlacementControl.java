package controllers;

import graphics.multiplayer.ShipTypeSelector;

import java.util.LinkedList;
import java.util.List;
import logic.Board;
import logic.Ship;
import logic.ShipType;

public final class PlacementControl{

	private Board grid;
    private ShipType currentShipType;
    private boolean  curOrientation = true; //Horizontal

   //The rest of the ships that have not been placed
    private List<ShipType> shipTypesLeft  = new LinkedList<ShipType>();
    
    //Ship type
    private ShipTypeSelector selector;

    //Waiting Control for the ship placement.
    private WaitControl   waiting;

   
    public PlacementControl(WaitControl waiting, Board grid) {
        this.grid = grid;
        this.waiting = waiting;
        
        // Initialize set of ShipTypes to place
        shipTypesLeft.add(ShipType.AIRCRAFT);
        shipTypesLeft.add(ShipType.BATTLESHIP);
        shipTypesLeft.add(ShipType.DESTROYER);
        shipTypesLeft.add(ShipType.SUBMARINE);
        shipTypesLeft.add(ShipType.PATROL);

        this.currentShipType = shipTypesLeft.get(0);
    }
    
    
    public boolean setShipPos(int x, int y) {
        // check easy boundary conditions
        if (null == this.currentShipType || !this.grid.boundsCheck(x, y) || (this.curOrientation && !this.grid.boundsCheck(x + this.currentShipType.length() - 1, y))
                || (!this.curOrientation && !this.grid.boundsCheck(x, y
                        + this.currentShipType.length() - 1))) {

            return false;
        }

        boolean validPlacement = true;

        if (this.curOrientation) {
            for (int i = 0; i < this.currentShipType.length(); i++) {
                validPlacement = validPlacement && !this.grid.isShip(x + i, y);
            }
        } else {
            for (int i = 0; i < this.currentShipType.length(); i++) {
                validPlacement = validPlacement && !this.grid.isShip(x, y + i);
            }
        }

        if (validPlacement) {
            this.grid.setShipPos(new Ship(this.currentShipType), x, y,
                    this.curOrientation);
        } 

        return validPlacement;
    }


    public void setSelectedShipType(ShipType type) {
        this.currentShipType = type;
        if (null != this.selector) {
            this.selector.setActiveButton(this.currentShipType);
        }
    }

   
    public void disableSelectedShipType() {
      
        this.shipTypesLeft.remove(this.currentShipType);
        if (null != this.selector) {
            this.selector.disableShipButton(this.currentShipType);
        }
        this.currentShipType = null;

        if (0 == this.shipTypesLeft.size()) {
            this.waiting.nextScreen(null);
        } else {
            this.currentShipType = this.shipTypesLeft.get(0);
            if (null != this.selector) {
                this.selector.setActiveButton(this.currentShipType);
            }
        }
    }

 
    public void registerShipTypeSelector(ShipTypeSelector selector) {

        this.selector = selector;
    }

    public void rotateShip() {
      
        this.curOrientation = !this.curOrientation;

    }

}
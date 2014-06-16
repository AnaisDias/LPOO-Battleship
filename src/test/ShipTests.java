package test;

import static org.junit.Assert.*;
import graphics.multiplayer.BattleshipFrame;
import logic.HitStatus;
import logic.ShipType;

import org.junit.Test;

import controllers.FireControl;
import controllers.PlacementControl;

public class ShipTests {

	/**
	 * Tests if ships are created in the correct position and if a 
	 * ship is not created if the chosen position is already occupied
	 */
	@Test
	public void testCreateShips(){
		BattleshipFrame game = new BattleshipFrame();

		PlacementControl placing = new PlacementControl(game.waiting, game.waiting.getActiveGrid());
		placing.setSelectedShipType(ShipType.PATROL);
		placing.setShipPos(5, 5);
		
		assertTrue(game.waiting.getActiveGrid().isShip(5, 5));
		assertTrue(game.waiting.getActiveGrid().isShip(6, 5));
		
		placing.setSelectedShipType(ShipType.BATTLESHIP);
		placing.rotateShip();
		placing.setShipPos(7, 5);
		assertTrue(game.waiting.getActiveGrid().isShip(7, 5));
		assertTrue(game.waiting.getActiveGrid().isShip(7, 6));
		assertTrue(game.waiting.getActiveGrid().isShip(7, 7));
		assertTrue(game.waiting.getActiveGrid().isShip(7, 8));
		
		//creating ship where it can't be created
		placing.setSelectedShipType(ShipType.BATTLESHIP);
		placing.rotateShip(); //to get back to horizontal position
		placing.setShipPos(7, 5);
		
		assertFalse(game.waiting.getActiveGrid().isShip(8, 5)); //if ship had been created, it would be true
			
	}
	
	/**
	 * Tests if ship correctly registers being hit when player fires a shot at it
	 */
	@Test
	public void testShipHit(){
		BattleshipFrame game = new BattleshipFrame();

		PlacementControl placing = new PlacementControl(game.waiting, game.waiting.getActiveGrid());
		placing.setSelectedShipType(ShipType.DESTROYER);
		placing.setShipPos(5, 5);
		
		game.waiting.switchFiringPlayer();
		FireControl firing = new FireControl(game.waiting, game.waiting.getInactiveGrid());
		HitStatus status = firing.fireShot(5, 5);
		assertEquals(status, HitStatus.HIT);
		
		PlacementControl placing1 = new PlacementControl(game.waiting, game.waiting.getActiveGrid());
		placing1.setSelectedShipType(ShipType.DESTROYER);
		placing1.setShipPos(5, 5);
		
		game.waiting.switchFiringPlayer();
		FireControl firing1 = new FireControl(game.waiting, game.waiting.getInactiveGrid());
		HitStatus status1 = firing1.fireShot(5, 5);
		assertEquals(status1, HitStatus.HIT);
		status1 = firing1.fireShot(5, 5);
		assertEquals(status1, HitStatus.MISS);
		
	}
	
	/**
	 * Tests if ship is destroyed after being hit in all the positions it occupies
	 */
	@Test
	public void testShipDestroyed(){
		BattleshipFrame game = new BattleshipFrame();

		PlacementControl placing = new PlacementControl(game.waiting, game.waiting.getActiveGrid());
		placing.setSelectedShipType(ShipType.PATROL);
		placing.setShipPos(5, 5);
		
		game.waiting.switchFiringPlayer();
		FireControl firing = new FireControl(game.waiting, game.waiting.getInactiveGrid());
		HitStatus status = firing.fireShot(5, 5);
		assertEquals(status, HitStatus.HIT);
		
		status = firing.fireShot(6, 5);
		assertEquals(status, HitStatus.SUNK);
	}
	
	
	/**
	 * Tests if game correctly registers how many ships remain after one is destroyed
	 */
	@Test
	public void testRemainingShips(){
		BattleshipFrame game = new BattleshipFrame();

		PlacementControl placing = new PlacementControl(game.waiting, game.waiting.getActiveGrid());
		placing.setSelectedShipType(ShipType.PATROL);
		placing.setShipPos(5, 5);
		placing.setSelectedShipType(ShipType.DESTROYER);
		placing.setShipPos(7, 5);
		placing.setSelectedShipType(ShipType.SUBMARINE);
		placing.setShipPos(2, 2);
		
		
		game.waiting.switchFiringPlayer();
		assertEquals(3, game.waiting.getInactiveGrid().getNumShips());
		
		FireControl firing = new FireControl(game.waiting, game.waiting.getInactiveGrid());
		HitStatus status = firing.fireShot(5, 5);
		assertEquals(status, HitStatus.HIT);
		
		status = firing.fireShot(6, 5);
		assertEquals(status, HitStatus.SUNK);
		
		assertEquals(2, game.waiting.getInactiveGrid().getNumShips());
		
	}
	

}

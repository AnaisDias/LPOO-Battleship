package test;

import static org.junit.Assert.*;
import logic.HitStatus;
import graphics.multiplayer.BattleshipFrame;
import logic.ShipType;

import org.junit.Test;

import controllers.FireControl;
import controllers.PlacementControl;

public class PlayingTests {
	
	
	/**
	 * Test that checks if game is won when player destroys all opposing ships
	 */
	@Test
	public void testGameWon(){
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
		assertTrue(game.waiting.getHasWon());
		
	}
	
	/**
	 * Tests if cells are correctly labeled as played after player chooses to fire at them
	 */
	
	@Test
	public void testPlayedCells(){
		BattleshipFrame game = new BattleshipFrame();
		
		PlacementControl placing = new PlacementControl(game.waiting, game.waiting.getActiveGrid());
		placing.setSelectedShipType(ShipType.PATROL);
		placing.setShipPos(5, 5);
		
		game.waiting.switchFiringPlayer();
		FireControl firing = new FireControl(game.waiting, game.waiting.getInactiveGrid());
		firing.fireShot(7, 7);
		assertTrue(game.waiting.getInactiveGrid().isViewable(7,7)); //if viewable, space has been played already
		
		game.waiting.switchFiringPlayer();
		FireControl firing1 = new FireControl(game.waiting, game.waiting.getInactiveGrid());
		firing1.fireShot(8, 7);
		assertTrue(game.waiting.getInactiveGrid().isViewable(8,7)); //if viewable, space has been played already
		
	}
	
	/*
	@Test
	public void testRecordScore(){
		
	}
	
	@Test
	public void testRegistration(){
		
	}
	*/
	
	/**
	 * Tests if water is hit when player fires at cell that does not have a ship in it
	 */
	@Test
	public void testWaterHit(){
		BattleshipFrame game = new BattleshipFrame();
		
		PlacementControl placing = new PlacementControl(game.waiting, game.waiting.getActiveGrid());
		placing.setSelectedShipType(ShipType.PATROL);
		placing.setShipPos(5, 5);
		
		FireControl firing = new FireControl(game.waiting, game.waiting.getInactiveGrid());
		HitStatus status = firing.fireShot(7, 5);
		assertEquals(status, HitStatus.MISS);
		
		status = firing.fireShot(5, 6);
		assertEquals(status, HitStatus.MISS);
	}
	
}

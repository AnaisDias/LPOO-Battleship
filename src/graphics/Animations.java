package graphics;

import graphics.multiplayer.BattleGrid;
import graphics.multiplayer.BattleGridSquare;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javax.imageio.ImageIO;

import logic.Board;
import logic.Ship;

public class Animations extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Image shipHit;
	private Image water;
	private Image waterHit;
	private int tileWidth, tileHeight;
	
	private Ship destroyedShip;
	private Board grid;
	
	private int waterFrames, waterCurrFrames;
	private int expFrames, expCurrFrames, expCurrRow, expRows;
	private int waterHitFrames, waterHitCurrFrames;
	
	
	public int getFrames(int obj){
		int frames=0;
		switch(obj){
		case 0:
			frames=waterFrames;
			break;
		case 1:
			frames=expFrames;
			break;
		case 2:
			frames=waterHitFrames;
			break;
		}
		
		return frames;
	}
	
	public void nextFrame(int obj){
		switch(obj){
		case 0:
			waterCurrFrames++;

			if (waterCurrFrames >= waterFrames) {
				waterCurrFrames = 0;
			}
			break;

		case 1:
			expCurrFrames++;

			if (expCurrFrames >= expFrames) {
				expCurrFrames = 0;
				expCurrRow++;
			}
			if(expCurrRow >= expRows){
				destroyedShip.setHit(true);
				expCurrRow=4;
				expCurrFrames=0;
			}

			break;
		case 2:
			waterHitCurrFrames++;

			if (waterHitCurrFrames >= waterHitFrames) {
				waterHitCurrFrames = 0;
			}
			break;
		}
	}
	
	public int getCurrentFrame(int obj){
		int cframes=0;
		switch(obj){
		case 0:
			cframes=waterCurrFrames;
			break;
		case 1:
			cframes=expCurrFrames;
			break;
		case 2:
			cframes=waterHitCurrFrames;
			break;
		}
		
		return cframes;
		}
	
	
	public int getFrameRow(){
		return expCurrRow;
	}
	
	public void loadImages(){
		waterFrames = 3;
		waterCurrFrames = 0;
		expFrames = 4;
		expCurrFrames = 0;
		expCurrRow = 0;
		expRows = 4;
		waterHitFrames=10;
		waterHitCurrFrames=0;
		
		tileWidth = 800/13;
		tileHeight= 600/13;
		try {
			shipHit = ImageIO.read(new File("pics/shiphit.png"));
			water = ImageIO.read(new File("pics/water.png"));
			waterHit = ImageIO.read(new File("pics/shot.png"));
		}
			
			catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	
	
	public void drawExplosion(Graphics2D g2d, Ship ship, int x, int y){
		destroyedShip=ship;
		Image img = shipHit;
		int height= 400;
		int width=500;

		int dstX = x * tileWidth;
		int dstY = (int) (y * tileHeight - (12 * tileHeight / 131.0));
		int yCorrection = (int) (-50.0 * tileHeight / 131.0);
		dstY += y * yCorrection;

		// centering board
		dstX += (width - tileWidth * width / 2.0);
		dstY += (height - (tileHeight - 0.37 * tileHeight)
				* height / 2.0);

		int dstWidth = tileWidth;
		int dstHeight = tileHeight;

		

		g2d.drawImage(
				img,
				dstX,
				dstY,
				dstX + dstWidth,
				dstY + dstHeight,
				getCurrentFrame(1) * img.getWidth(null) / getFrames(1),
				getFrameRow() * img.getHeight(null) / 5,
				getCurrentFrame(1) * img.getWidth(null) / getFrames(1)
						+ img.getWidth(null) / getFrames(1),
				getFrameRow() * img.getHeight(null) / 5
						+ img.getHeight(null) / 5, null);

		
		if(!destroyedShip.getHit()) nextFrame(1);
	}
	
	public void drawWaterHit(Graphics2D g2d,int x, int y){
		Image img = waterHit;
		int height= 400;
		int width=500;

		int dstX = x * tileWidth;
		int dstY = (int) (y * tileHeight - (12 * tileHeight / 131.0));
		int yCorrection = (int) (-50.0 * tileHeight / 131.0);
		dstY += y * yCorrection;

		// centering board
		dstX += (width - tileWidth * width / 2.0);
		dstY += (height - (tileHeight - 0.37 * tileHeight)
				* height / 2.0);

		int dstWidth = tileWidth;
		int dstHeight = tileHeight;

		

		g2d.drawImage(
				img,
				dstX,
				dstY,
				dstX + dstWidth,
				dstY + dstHeight,
				getCurrentFrame(2) * img.getWidth(null) / getFrames(2),
				0,
				getCurrentFrame(2) * img.getWidth(null) / getFrames(2)
						+ img.getWidth(null) / getFrames(1), img.getHeight(null)
						, null);
		nextFrame(2);
	}
	
	public void drawWater(Graphics2D g2d,int x, int y){
		Image img = water;
		int height= 400;
		int width=500;

		int dstX = x * tileWidth;
		int dstY = (int) (y * tileHeight - (12 * tileHeight / 131.0));
		int yCorrection = (int) (-50.0 * tileHeight / 131.0);
		dstY += y * yCorrection;

		// centering board
		dstX += (width - tileWidth * width / 2.0);
		dstY += (height - (tileHeight - 0.37 * tileHeight)
				* height / 2.0);

		int dstWidth = tileWidth;
		int dstHeight = tileHeight;

		

		g2d.drawImage(
				img,
				dstX,
				dstY,
				dstX + dstWidth,
				dstY + dstHeight,
				getCurrentFrame(0) * img.getWidth(null) / getFrames(0),
				0,
				getCurrentFrame(0) * img.getWidth(null) / getFrames(0)
						+ img.getWidth(null) / getFrames(0), img.getHeight(null)
						, null);
		
		nextFrame(0);
	}
	
	private void drawShip(Graphics2D g2d, Ship ship, int x, int y){
		
	}
	
	public void paintComponent(Graphics g)
	{
		
	    super.paintComponent(g);
	    BattleGrid btgrid = new BattleGrid(grid);
	    Graphics2D g2= (Graphics2D) g;
	   btgrid.redrawSquareBackgrounds(g2, grid);
	}
}

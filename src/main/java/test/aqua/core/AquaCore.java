package test.aqua.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import test.aqua.core.api.Internalable;


public class AquaCore implements Internalable {
	
	public static final int FISH_TYPES = 5;
	
	public static final int OFFSET_FROM_BORDER = 15;
	
	private static Random rand = new Random();
	
	private BaseFish[] fishes;
	
	private int fishCount;
	
	private Rectangle aquaRect;
	
	public void init(Rectangle aquaRect, int fishCount) {		
		this.aquaRect = aquaRect;
		this.fishCount = fishCount;
		
		int maxX = this.aquaRect.width;
		int maxY = this.aquaRect.height;
		
		fishes = new BaseFish[this.fishCount];
		for (int i = 0; i < fishes.length; i++) {
			int initX = rand.nextInt(maxX - OFFSET_FROM_BORDER);
			int initY = rand.nextInt(maxY - OFFSET_FROM_BORDER);
			
			fishes[i] = new BaseFish(rand, initX, initY, this);
		}
	}
	
	public void draw(Graphics graphics) {
		aquaRect.setSize(
				graphics.getClipBounds().getSize());
		
		Color oldColor = graphics.getColor();
		
		graphics.setColor(Color.BLACK);
		graphics.drawRect(1, 1,
				(int)aquaRect.getWidth() - 2,
				(int)aquaRect.getHeight() - 2);
		
		for (BaseFish fish : fishes) {
			if (fish != null) {
				fish.draw(graphics);
			}
		}
		
		graphics.setColor(oldColor);
	}
	
	public void tick() {		
		// Перемещение рыбок
		for (BaseFish fish : fishes) {
			if (fish != null) {
				fish.tick();
			}
		}
	}

	@Override
	public boolean checkPoint(int x, int y) {
		int maxX = aquaRect.width;
		int maxY = aquaRect.height;
		
		if (x >= maxX || x <= 0) {
			return false;
		}
		if (y >= maxY || y <= 0) {
			return false;
		}
		return true;
	}
	
}

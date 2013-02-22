package test.aqua.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class AquaCore {
	
	public static final int FISH_TYPES = 5;
	
	public static final int FISH_SIZE = 5;
	
	public static final int OFFSET_FROM_BORDER = 15;
	
	private static Random rand = new Random();
	
	private Point[] fishPoints;
	
	private int fishCount;
	
	private Rectangle aquaRect;
	
	public void init(Rectangle aquaRect, int fishCount) {		
		this.aquaRect = aquaRect;
		this.fishCount = fishCount;
		
		int maxX = this.aquaRect.width;
		int maxY = this.aquaRect.height;
		
		fishPoints = new Point[this.fishCount];
		for (int i = 0; i < fishPoints.length; i++) {
			fishPoints[i] = new Point(rand.nextInt(maxX - OFFSET_FROM_BORDER),
					rand.nextInt(maxY - OFFSET_FROM_BORDER));
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
		
		graphics.setColor(Color.RED);
		for (Point fishPoint : 	fishPoints) {
			if (fishPoint != null) {
				graphics.drawRect(fishPoint.x, fishPoint.y,
						FISH_SIZE, FISH_SIZE);
			}
		}
		
		graphics.setColor(oldColor);
	}
	
	public void tick() {
		int maxX = aquaRect.width;
		int maxY = aquaRect.height;
		
		// Перемещение рыбок
		for (Point fishPoint : 	fishPoints) {
			if (fishPoint != null) {
				int xDirection = rand.nextInt(3) - 1;
				int yDirection = rand.nextInt(3) - 1;
				
				int newX = fishPoint.x + xDirection;
				int newY = fishPoint.y + yDirection;
				
				if (newX < maxX && newX > 0) {
					fishPoint.x = newX;
				}
				if (newY < maxY && newY > 0) {
					fishPoint.y = newY;
				}
			}
		}
	}
	
}

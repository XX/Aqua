package test.aqua.core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class BaseFish extends AbstractAquaObject {

	public static final int FISH_SIZE = 5;
	
	public BaseFish(Random rand, int x, int y) {
		super(rand, x, y);
	}

	@Override
	public void draw(Graphics graphics) {
		Color oldColor = graphics.getColor();
		graphics.setColor(Color.RED);
		graphics.drawRect(x, y,	FISH_SIZE, FISH_SIZE);
		graphics.setColor(oldColor);
	}

	@Override
	public void tick() {
		int xDirection = rand.nextInt(3) - 1;
		int yDirection = rand.nextInt(3) - 1;
		
		int newX = x + xDirection;
		int newY = y + yDirection;
		
//		int maxX = aquaRect.width;
//		int maxY = aquaRect.height;
//		
//		if (newX < maxX && newX > 0) {
//			fishPoint.x = newX;
//		}
//		if (newY < maxY && newY > 0) {
//			fishPoint.y = newY;
//		}
		
		x = newX;
		y = newY;
	}

}

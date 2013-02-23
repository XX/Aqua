package test.aqua.core.api;

import java.awt.Graphics;
import java.util.Random;


public abstract class AbstractAquaObject {
	
	protected Random rand;
	
	protected Internalable foreignBox;
	
	protected int x;
	
	protected int y;

	/**
	 * Конструктор по умолчанию, инициализирует поля
	 * 
	 * @param rand объект для получения случайных чисел 
	 * @param x начальная координата объекта
	 * @param y начальная координата объекта
	 */
	public AbstractAquaObject(Random rand, int x, int y, Internalable foreignBox) {
		this.rand = rand;
		this.x = x;
		this.y = y;
		this.foreignBox = foreignBox;
	}
	
	/**
	 * Рисование себя
	 * 
	 * @param graphics графический контекст для рисования
	 */
	public abstract void draw(Graphics graphics);
	
	/**
	 * Обработка тика таймера
	 */
	public abstract void tick();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}

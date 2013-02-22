package test.aqua;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	public static final String TITLE = "Aqua";
	
	private static final int FRAME_WIDTH = 800;
	
	private static final int FRAME_HIGHT = 400;
	
	private static final int TICK_INTERVAL = 100;
	
	public static final int FISH_COUNT = 5;
	
	public static final int FISH_SIZE = 5;
	
	public static final int OFFSET_FROM_BORDER = 15;
	
	private static Random rand = new Random();
	
	private Point[] fishPoints;
	
	private JPanel panel;
	
	private Timer mainTimer;
	
	private int maxX;
	
	private int maxY;
	
	public MainFrame() {
		super(TITLE);
		this.setSize(FRAME_WIDTH, FRAME_HIGHT);
		this.setLocationRelativeTo(this.getRootPane());
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub	
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				mainFrameKeyPressed(e);
			}
		});
		
		panel = new JPanel() {
			
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				paintContent(g);
			}
			
		};
		this.add(panel);
		
		mainTimer = new Timer(TICK_INTERVAL, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tickMainTimer();
			}
		});
	}

	/**
	 * Вызывается при загрузке фрейма
	 */
	private void load() {
		maxX = this.getWidth() - OFFSET_FROM_BORDER;
		maxY = this.getHeight() - OFFSET_FROM_BORDER;
		
		fishPoints = new Point[FISH_COUNT];
		for (int i = 0; i < fishPoints.length; i++) {
			fishPoints[i] = new Point(rand.nextInt(maxX), rand.nextInt(maxY));
		}
		mainTimer.start();
	}
	
	/**
	 * Вызывается при нажатии клавиш
	 */
	protected void mainFrameKeyPressed(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			close();
			break;

		default:
			break;
		}
	}

	/**
	 * Рисование содержимого
	 */
	protected void paintContent(Graphics g) {
		Rectangle drawRect = new Rectangle((int)g.getClipBounds().getWidth() - 2,
				(int) g.getClipBounds().getHeight() - 2);
		
		g.setColor(Color.BLACK);
		g.drawRect(1, 1,
				(int)drawRect.getWidth(),
				(int)drawRect.getHeight());
		
		if (fishPoints == null) {
			return;
		}
		
		g.setColor(Color.RED);
		for (Point fishPoint : 	fishPoints) {
			if (fishPoint != null) {
				g.drawRect(fishPoint.x, fishPoint.y, FISH_SIZE, FISH_SIZE);
			}
		}
	}
	
	/**
	 * Тик таймера
	 */
	protected void tickMainTimer() {
		if (fishPoints == null) {
			return;
		}
		// Перемещение рыбок
		for (Point fishPoint : 	fishPoints) {
			if (fishPoint != null) {
				
				int newX = fishPoint.x + rand.nextInt(3) - 1;
				int newY = fishPoint.y + rand.nextInt(3) - 1;
				
				if (newX < maxX && newX > 0) {
					fishPoint.x = newX;
				}
				if (newY < maxY && newY > 0) {
					fishPoint.y = newY;
				}
			}
		}
		
		panel.repaint();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame frame = new MainFrame();
				frame.present();
			}
		});
	}
	
	/**
	 * Вызывается при отображении фрейма
	 */
	public void present() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		load();		
	}
	
	private void close() {
		setVisible(false);
		dispose();
		System.exit(0);
	}
	
}

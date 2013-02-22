package test.aqua;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import test.aqua.core.AquaCore;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private static final String TITLE = "Aqua";
	
	private static final int FRAME_WIDTH = 800;
	
	private static final int FRAME_HIGHT = 400;
	
	private static final int TICK_INTERVAL = 100;
	
	private static final int FISH_COUNT = 5;
	
	private AquaCore core = new AquaCore();
	
	private JPanel panel;
	
	private Timer mainTimer;
	
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
		core.init(panel.getVisibleRect(), FISH_COUNT);
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
		core.draw(g);
	}
	
	/**
	 * Тик таймера
	 */
	protected void tickMainTimer() {
		core.tick();		
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
	
	/**
	 * Закрывает фрейм и приложение
	 */
	private void close() {
		setVisible(false);
		dispose();
		System.exit(0);
	}
	
}

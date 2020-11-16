import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.awt.geom.*;
import java.awt.font.*;

public class Test extends JFrame {
	
	static Data site;
	static final int WIDTH = 1800, HEIGHT = 800;
	
	public Test() {
		super("Tag Cloud Project");
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		getContentPane().add(new Bruh());
		setVisible(true);
		System.out.println("size:" + getContentPane().getSize());
		System.out.println("height:" + getContentPane().getHeight());
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					dispose();
			}
		});
	}
	
	public static void main(String[] args) throws Exception {
		
		new Test();
		//kb.close();
	}
	
	private class Bruh extends Canvas{
		public void paint(Graphics g) {
			g.drawRect(20, 20, 100, 100);
			g.drawRect(20, 120, 100, 100);
		}
	}
}


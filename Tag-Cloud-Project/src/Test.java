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
		System.out.println("x".matches(".*[gjpqy].*"));
		new Test();
		//kb.close();
	}
	
	private class Bruh extends Canvas{
		public void paint(Graphics g) {
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, (int)(800/3.7460000000000004)));
			FontMetrics fm = g.getFontMetrics();
			g.drawString("X", 400, 400+fm.getHeight()-fm.getDescent()-fm.getDescent());
			g.drawRect(400,400,fm.stringWidth("X"), fm.getHeight()-fm.getDescent()-fm.getDescent());
		}
	}
}


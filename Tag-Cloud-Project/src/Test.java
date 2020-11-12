import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;

public class Test extends JFrame {
	
	static Data site;
	static final int WIDTH = 800, HEIGHT = 800;
	
	public Test() {
		super("Tag Cloud Project");
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		getContentPane().add(new Bruh());
		setVisible(true);
		System.out.println("size:" + getContentPane().getSize());
		System.out.println("height:" + getContentPane().getHeight());
	}
	
	public static void main(String[] args) throws Exception {
		/*Scanner kb = new Scanner(System.in);
		System.out.println("What website would you like to make a tag cloud out of?");
		String url = kb.nextLine();*/
		/*String url = "https://www.pornhub.com/";
		Scraper scr = new Scraper(url);
		String s = scr.getWebsite();
		site = new Data(s);		
		
		System.out.println(s);
		
		System.out.println(site.getCloud());
		System.out.println(site.getMax());*/
		
		Test Cloud = new Test();
		//kb.close();
	}
	
	static class Bruh extends JPanel {
		Word e;
		int x, y;
		
		
		public Bruh() {
			setBackground(Color.BLACK);
			e = new Word("abcdefghijklmnopqrstuvwxyz",50);
			x = 50;
			y = 200;
		}
		
		public void paint(Graphics w) {
			w.setFont(e.getFont());
			FontMetrics pp = w.getFontMetrics();
			
			int width = pp.stringWidth(e.getWord());
			int height = pp.getHeight() - pp.getDescent();
			
			e.setDimensions(x, y, width, height);
			
			w.drawRect(e.x, e.y, e.width, e.height);
			w.drawString(e.getWord(), e.x, e.y+height-pp.getDescent());
			
			
			
			String str = "abcdefghijklmnopqrstuvwxyz";
	        Rectangle2D rect = pp.getStringBounds(str, w);

	        int x = 5;
	        int y = 100; 

	        w.drawRect(x, y - (int)rect.getHeight(), (int)rect.getWidth(), (int)rect.getHeight());
	        w.drawString(str, x, y-pp.getDescent());
			
		}
	}
	
	
}


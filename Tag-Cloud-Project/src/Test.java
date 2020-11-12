import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.font.*;

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
			e = new Word("hd",100);
			x = 50;
			y = 200;
		}
		
		public void paint(Graphics w) {
		    w.setFont(e.getFont());
			FontMetrics pp = w.getFontMetrics();
			
			int width = pp.stringWidth(e.getWord());
			int height = pp.getHeight() - pp.getDescent();
			
			e.setDimensions(x, y, width, height);
			
			/*w.drawRect(e.x, e.y, e.width, e.height);
			w.drawString(e.getWord(), e.x, e.y);*/
			
			
			Graphics2D g2 = (Graphics2D) w;
			
			/*FontRenderContext frc = g2.getFontRenderContext();
	        GlyphVector gv = g2.getFont().createGlyphVector(frc, e.getWord());
	        Rectangle r = gv.getPixelBounds(null, 400, 400);
	        
	        System.out.println(r.y);
	        r.y += r.height;
	        
	        System.out.println(r.y);
	        System.out.println(r.height);*/
	        
	        Rectangle r = getBounds(g2,e.getWord(),x,y);
	        
	        g2.drawString(e.getWord(), x, y);
	        g2.draw(r);
	        
	        
	        
	        r = getBounds(g2,"gdfasgppp", 50,400);
	        
	        r.y -= pp.getDescent();
	        
	        g2.drawString("gdfasgppp", 50, 400);
	        g2.draw(r);
	        
	        
	        
	        g2.setColor(Color.RED);
	        g2.drawRect(50, 400, 100, 100);
	        
		}
		
		public Rectangle getBounds(Graphics2D g, String s, int x, int y) {
			FontRenderContext render = g.getFontRenderContext();
	        GlyphVector vec = g.getFont().createGlyphVector(render, s);
	        return vec.getPixelBounds(null, x, y);
		}
	}
	
	
}


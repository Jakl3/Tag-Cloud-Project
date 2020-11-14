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
		
		//Test Cloud = new Test();
		
		/*String s = "<h1> word </h1> worth 10\r\n" + 
				"<h2> word </h2> worth 8\r\n" + 
				"<h3> word </h3> worth 6\r\n" + 
				"<h4> word </h4> worth 4\r\n" + 
				"<h5> word </h5> worth 2\r\n" + 
				"<h6> word </h6> worth 1\r\n" + 
				"<p> word </p> worth 1\r\n" + 
				"<title> word </title> worth 10\r\n" + 
				"<a href = \"word\"> word </a> worth 5\r\n" + 
				"<ul>\r\n" + 
				" <li> word </li> worth 1\r\n" + 
				" <li> word </li> worth 1\r\n" + 
				"</ul>\r\n" + 
				"<h1><h2><h3><a href=\"dab\"><li>dabby</li></a></h3></h2></h1>";
		
		site = new Data(s);*/
		
		new Test();
		//kb.close();
	}
	
	static class Bruh extends JPanel {
		Word e;
		int x, y;
		
		
		public Bruh() {
			setBackground(Color.BLACK);
			e = new Word("abcdefghijklmnopqrstuvwxyz",100);
			x = 50;
			y = 200;
		}
		
		public void paint(Graphics w) {
		    w.setFont(e.getFont());
			FontMetrics pp = w.getFontMetrics();
			
			/*w.drawRect(e.x, e.y, e.width, e.height);
			w.drawString(e.getWord(), e.x, e.y);*/
			
			/*w.drawRect(e.x, e.y+e.height/15, e.width, e.height-pp.getDescent());
			w.drawString(e.getWord(), e.x, e.y+height-pp.getDescent());*/
			
			
			Graphics2D g2 = (Graphics2D) w;
			
			/*FontRenderContext frc = g2.getFontRenderContext();
	        GlyphVector gv = g2.getFont().createGlyphVector(frc, e.getWord());
	        Rectangle r = gv.getPixelBounds(null, 400, 400);
	        
	        System.out.println(r.y);
	        r.y += r.height;
	        
	        System.out.println(r.y);
	        System.out.println(r.height);*/
	        
	        /*Rectangle r = getBounds(g2,e.getWord(),x,y);
	        
	        double shiftX = Math.sqrt(r.width)/5;
	        r.x -= shiftX;
	        r.width += 2 * shiftX;
	        
	        double shiftY = Math.sqrt(r.width)/5;
	        r.y -= shiftY;
	        r.height += 2 * shiftY;
	        
	        
	        g2.draw(r);
	        g2.drawString(e.getWord(), x, y);*/
	       
	        //set 2
	        
			int w1 = pp.stringWidth(e.getWord());
			int h1 = pp.getHeight() - pp.getDescent();
			
	       /* e.setDimensions(50, 500, width, height);
	        w.drawRect(50, 500, e.width, e.height);

			w.drawString(e.getWord(), e.x, e.y+height);*/
			
			String t = "abcdefghijklmnopqrstuvwxyz";
			w.drawString(t, 50, 100+h1);
			w.drawRect(50, 100+pp.getDescent()+(int)Math.sqrt(h1)/3, pp.stringWidth(t), h1-pp.getDescent());
			
			String s = "abc";
			
			int width = pp.stringWidth(e.getWord());
			int height = s.matches(".*[gjpqy].*") ? h1 : h1 - pp.getDescent();
			
			/*if(s.matches(".*[gjpqy].*")) {
				System.out.println("AAAAAAA");
				w.drawRect(600, 500+pp.getDescent()+(int)Math.sqrt(height)/3, pp.stringWidth(s), h1);
			}
			else {
				w.drawRect(600, 500+pp.getDescent()+(int)Math.sqrt(height)/3, pp.stringWidth(s), h1-pp.getDescent());
			}*/
			w.drawRect(600, 500+pp.getDescent()+(int)Math.sqrt(h1)/3, pp.stringWidth(s), height);
			w.drawString(s, 600, 500+h1);
			
	        
		}
		
		
		public Rectangle getBounds(Graphics2D g, String s, int x, int y) {
			FontRenderContext render = g.getFontRenderContext();
	        GlyphVector vec = g.getFont().createGlyphVector(render, s);
	        return vec.getPixelBounds(null, x, y);
		}
	}
	
	
}


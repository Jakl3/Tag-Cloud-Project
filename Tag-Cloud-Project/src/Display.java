import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JPanel;
@SuppressWarnings("serial")

/** 
 * The main driving code of the graphics portion of the Tag Cloud. Takes in
 * a map of the cloud as an input from the Data class and arranges them in
 * a pattern to create the Tag Cloud
 * 
 * @author Nathan Nguyen and Jack Le
 */
public class Display extends JPanel  {
	
	// Instance variables
	private final Color background = Color.BLACK;
	private ArrayList<Word> words;
	private double SCALE;
	private static final int centerX = Main.WIDTH/2, centerY = Main.HEIGHT/2;
	private int cnt;
	
	/**
	 * Constructor - receives the Map of words and uses that
	 * to calculate the value to scale the words by so
	 * that they do not exceed the bounds of the window.
	 * 
	 * @author Nathan Nguyen
	 */
	public Display(Map<String,Integer> w) {
		// Sets up the window and instantiates variables
		setBackground(background);	
		words = new ArrayList<Word>();
		cnt = 0;
		
		// Calculates the font scaling value
		double count = 0,avg = 0; 
		for(Map.Entry<String,Integer> k : w.entrySet()) {
			if(count<7) { avg += k.getValue(); count++; }
			words.add(new Word(k.getKey(),k.getValue()));
		}
		avg/=count;
		SCALE = ((double)avg/100.0);
		
		System.out.println("Number of words included in Tag Cloud: " + words.size());
		System.out.println("Font Scaling: " + SCALE);
	}
	
	/**
	 * Arranges the pattern to draw the Tag Cloud
	 * by finding the best possible position to place
	 * each word. The best position for each word
	 * is defined as the position that has the smallest
	 * distance to the center of the window.
	 * 
	 * Due to how Graphics and Painting works in Java,
	 * this method will run at least three times if left
	 * alone. However, the graphics displayed will not change
	 * with each run of the method.
	 * 
	 * @author Jack Le and Nathan Nguyen
	 */
	public void paint(Graphics w) {
		long startTime = System.nanoTime();
		
		ArrayList<Word> drawnWords = new ArrayList<>();
		// Goes through every word in Words
		for(Word word : words) {
			w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, (int)(word.getWeight()/SCALE)));
			w.setColor(word.getColor());
			FontMetrics fm = w.getFontMetrics();
			
			// Calculates the bounds of the word
			Rectangle bounds = getBounds((Graphics2D) w, word.getWord(), 0,0);
			
			// Calculates the width of the word
			int width = fm.stringWidth(word.getWord());
			width += Math.sqrt(width)/3;
			if(width<=0) { width = 1; }
			
			// Calculates the height of the word
			int h1 = fm.getHeight() - fm.getDescent();
			int height = word.getWord().matches(".*[gjpqy].*") ? h1 : h1 - fm.getDescent();
			height = word.getWord().matches(".*[bdfhijklt].*") ? height : height - fm.getDescent();
			if(height<=0) { height = 1; }
			
			/**
			 * Finds the best possible position to place the word
			 * by going through every 5th pixel on the window and 
			 * finding the pixel with the shortest distance to the
			 * center, not intersecting any other placed words.
			 */
			int finX = 0, finY = 0;
			double mindis = 1e8;
			Word end = null;
			for(int i = 0; i < Main.WIDTH; i+=5) {
				for(int j = 0; j < Main.HEIGHT; j+=5) {
					Word pos = new Word(word.getWord(),word.getWeight());
					pos.setDimensions(i, j, width, height);
			        
					// Checks if the current position intersects any other word
					boolean intersects = false;
					for(Rectangle item : drawnWords) {
						if(pos.intersects(item)) {
							intersects = true;
							break;
						}
					}
					if(intersects) continue;
					
					/**
					 * Calculates the distance from the center of
					 * the rectangle to the center of the window
					 */
					double midRectX = pos.getCenterX();
					double midRectY = pos.getCenterY();
					double dis = distance(centerX,midRectX,centerY,midRectY);
					
					/**
					 * Checks if the current pixel is a better
					 * position than the previous best pixel
					 */
					if(dis < mindis) {
						mindis = dis;
						end = pos;
						finX = i;
						finY = j;
					}
				}
			}
			drawnWords.add(end);
			
			// Calculates where to place the word
			int placeY = 0;
			if(word.getWord().matches(".*[gjpqy].*")) {
				placeY = finY+height-fm.getDescent();
			}
			else {
				placeY = finY + height;
			}
			
			// Draws the word at the calculated position
			w.drawString(word.getWord(), finX+(int)(Math.sqrt(width)/3), placeY - (height - bounds.height)/2);
		}
		
		cnt++;
		long endTime = System.nanoTime();
		System.out.println("Time to paint Tag Cloud " + cnt + ": " + ((endTime - startTime)/1000000) + " ms");
	}
	
	/**
	 * Uses a GlyphVector to find the exact
	 * bounds of the word
	 * 
	 * @author Jack Le
	 */
	public Rectangle getBounds(Graphics2D g, String s, int x, int y) {
		FontRenderContext render = g.getFontRenderContext();
		GlyphVector vec = g.getFont().createGlyphVector(render, s);
		return vec.getPixelBounds(null, x, y);
	}
	
	// Distance formula
	public double distance(double x1, double x2, double y1, double y2) {
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}
}

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.util.*;
import javax.swing.*;

public class Display extends Canvas  {
	
	private final Color background = Color.BLACK;
	private ArrayList<Word> words;
	private double SCALE;
	private static final int centerX = Main.WIDTH/2, centerY = Main.HEIGHT/2;
	
	public Display(int max, Map<String,Integer> w) {
		// Rank words by rank
		words = new ArrayList<Word>();
		double avg = 0, count = 0;
		double sum = 0; double avgmax = 0; 
		for(Map.Entry<String,Integer> k : w.entrySet()) {
			if(count<7) {avgmax += k.getValue();  count++;}
			if(k.getValue()>5) {
				avg+=k.getValue();
			}
			sum += k.getValue();
			words.add(new Word(k.getKey(),k.getValue()));
		}
		avgmax/=count;
		System.out.println(words);
		System.out.println("number of words: " + words.size());
		setBackground(background);
		
		SCALE = ((double)avgmax/100.0);
				//avg>15?avg/(max/avg):avg>=5.0?(avg/(avg*1.667)):max/250.0;
				//avg>=5.0?(avg/(avg*1.667)):max/250.0;
		System.out.println("scale: " + SCALE);
	}
	
	// paint method
	public void paint(Graphics w) {
		ArrayList<Word> drawnWords = new ArrayList<>();
		for(Word e : words) {
			w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, (int)(e.getWeight()/SCALE)));
			//w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, e.getWeight()));
			w.setColor(e.getColor());
			FontMetrics fm = w.getFontMetrics();
			
			Rectangle bounds = getBounds((Graphics2D) w, e.getWord(), 0,0);
			
			int h1 = fm.getHeight() - fm.getDescent();
			int width = fm.stringWidth(e.getWord());
			width += Math.sqrt(width)/3;
			
			int height = e.getWord().matches(".*[gjpqy].*") ? h1 : h1 - fm.getDescent();
			//System.out.println(height);
			height = e.getWord().matches(".*[bdfijklt].*") ? height : height - fm.getDescent();
			//System.out.println(height);
			if(height<=0) { height = 1; }
			if(width<=0) { width = 1; }
			System.out.println(e.getWord() + " weight: " + e.getWeight() + " width: " + width + " height: " + height);
			int finX = 0, finY = 0;
			double mindis = 1e8;
			Word end = new Word();
			for(int i = 0; i < Main.WIDTH; i+=5) {
				for(int j = 0; j < Main.HEIGHT; j+=5) {
					//if(Math.abs(Main.WIDTH/2 - i) < 100 && Math.abs(Main.HEIGHT/2-j) < 100) continue;
					//Rectangle pos = new Rectangle(i,j,width,height);
					Word pos = new Word(e.getWord(),e.getWeight());
					pos.setDimensions(i, j, width, height);
			        
					boolean ok = true;
					for(Rectangle item : drawnWords) {
						if(pos.intersects(item)) {
							ok = false;
							break;
						}
					}
					if(!ok) continue;
					
					double midRectX = pos.getCenterX();
					double midRectY = pos.getCenterY();
					
					double dis = distance(centerX,midRectX,centerY,midRectY);
					
					if(dis < mindis) {
						//System.out.println(dis + " " + mindis + "rep");
						mindis = dis;
						end = pos;
						finX = i;
						finY = j;
					}
					
					/*if(dis < 10) {
						System.out.println(e);
						System.out.println(drawnWords);
						for(Rectangle item : drawnWords) {
							if(pos.intersects(item)) {
								System.out.println("NOPEEEEEEEEEEEEEEEE");
								return;
							}
						}
					}*/
				}
			}
			drawnWords.add(end);
			
			//draw the rectangle borders for each word
			//w.drawRect(end.x, end.y, end.width, end.height);
			
			
			//visited[end.x][end.y] = true; 
			
			int placeY = 0;
			if(e.getWord().matches(".*[gjpqy].*")) {
				placeY = finY+height-fm.getDescent();
			}
			else {
				placeY = finY + height;
			}
				
			w.drawString(e.getWord(), finX+(int)(Math.sqrt(width)/3), placeY - (height - bounds.height)/2);
			
		}
		
		System.out.println("DONE");
	}
	
	public Rectangle getBounds(Graphics2D g, String s, int x, int y) {
		FontRenderContext render = g.getFontRenderContext();
        GlyphVector vec = g.getFont().createGlyphVector(render, s);
        return vec.getPixelBounds(null, x, y);
	}
	
	public double distance(double x1, double x2, double y1, double y2) {
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}
}

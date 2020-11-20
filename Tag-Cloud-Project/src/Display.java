import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Display extends JPanel  {
	
	private final Color background = Color.BLACK;
	private ArrayList<Word> words;
	private double SCALE;
	private static final int centerX = Main.WIDTH/2, centerY = Main.HEIGHT/2;
	
	public Display(int max, Map<String,Integer> w) {
		setBackground(background);	
		words = new ArrayList<Word>();
		double count = 0,avg = 0; 
		for(Map.Entry<String,Integer> k : w.entrySet()) {
			if(count<7) { avg += k.getValue(); count++; }
			words.add(new Word(k.getKey(),k.getValue()));
		}
		avg/=count;
		SCALE = ((double)avg/100.0);
		
		System.out.println(words);
		System.out.println("number of words: " + words.size());
		System.out.println("scale: " + SCALE);
	}
	
	public void paint(Graphics w) {
		ArrayList<Word> drawnWords = new ArrayList<>();
		for(Word word : words) {
			w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, (int)(word.getWeight()/SCALE)));
			w.setColor(word.getColor());
			FontMetrics fm = w.getFontMetrics();
			
			Rectangle bounds = getBounds((Graphics2D) w, word.getWord(), 0,0);
			
			int h1 = fm.getHeight() - fm.getDescent();
			int width = fm.stringWidth(word.getWord());
			width += Math.sqrt(width)/3;
			
			int height = word.getWord().matches(".*[gjpqy].*") ? h1 : h1 - fm.getDescent();
			height = word.getWord().matches(".*[bdfhijklt].*") ? height : height - fm.getDescent();

			if(height<=0) { height = 1; }
			if(width<=0) { width = 1; }

			int finX = 0, finY = 0;
			double mindis = 1e8;
			Word end = new Word();
			for(int i = 0; i < Main.WIDTH; i+=5) {
				for(int j = 0; j < Main.HEIGHT; j+=5) {
					Word pos = new Word(word.getWord(),word.getWeight());
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
						mindis = dis;
						end = pos;
						finX = i;
						finY = j;
					}
				}
			}
			drawnWords.add(end);
				
			int placeY = 0;
			if(word.getWord().matches(".*[gjpqy].*")) {
				placeY = finY+height-fm.getDescent();
			}
			else {
				placeY = finY + height;
			}
			
			//draw the border rectangles for each word
			//w.drawRect(end.x, end.y, end.width, end.height);
			w.drawString(word.getWord(), finX+(int)(Math.sqrt(width)/3), placeY - (height - bounds.height)/2);
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

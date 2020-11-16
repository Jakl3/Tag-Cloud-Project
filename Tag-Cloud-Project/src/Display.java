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
	private boolean[][] visited;
	
	public Display(int max, Map<String,Integer> w) {
		// Rank words by rank
		words = new ArrayList<Word>();
		visited = new boolean[5000][5000];
		for(Map.Entry<String,Integer> k : w.entrySet()) {
			words.add(new Word(k.getKey(),k.getValue()));
		}
		System.out.println(words);
		System.out.println(words.size());
		setBackground(background);
		
		SCALE = max/250.0;
		System.out.println(SCALE);
	}
	
	public void paint(Graphics w) {
		ArrayList<Rectangle> drawnWords = new ArrayList<>();
		
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
			int finX = 0, finY = 0;
			double mindis = 1e8;
			Rectangle end = new Rectangle();
			for(int i = 0; i < Main.WIDTH; i++) {
				height: for(int j = 0; j < Main.HEIGHT; j++) {
					if(visited[i][j]) continue;
					Rectangle pos = new Rectangle(i,j,width,height);
			        
					for(Rectangle item : drawnWords) {
						if(pos.intersects(item)) {
							continue height;
						}
					}
					
					double midRectX = pos.getCenterX();
					double midRectY = pos.getCenterY();
					
					double dis = distance(centerX,midRectX,centerY,midRectY);
					
					//System.out.println("dis: " + dis + " mindis: " + mindis);
					if(dis < mindis) {
						//System.out.println(dis + " " + mindis + "rep");
						mindis = dis;
						end = pos;
						finX = i;
						finY = j;
					}
				}
			}
			drawnWords.add(end);
			editVisited(end.x,end.y,end.width,end.height);
			w.drawRect(end.x, end.y, end.width, end.height);
			int placeY = e.getWord().matches(".*[gjpqy].*") ? finY+height-fm.getDescent() :
				finY + height;
			w.drawString(e.getWord(), finX, placeY - (height - bounds.height)/2);
			
		}
		
		System.out.println("DONE");
		
	}
	
	public void editVisited(int x, int y, int width, int height) {
		// i is row, j is column
		for(int i = y; i <= y+height; i++) {
			for(int j = x; j <= x+width; j++) {
				visited[i][j] = true;
			}
		}
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

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.util.*;
import javax.swing.*;

// DOESNT WORK - STACK OVERFLOW ERROR
public class Display_Recursion extends Canvas  {
	
	private final Color background = Color.BLACK;
	private ArrayList<Word> words;
	private double SCALE;
	//private static final int centerX = Main.WIDTH/2, centerY = Main.HEIGHT/2;
	private static final int centerX = Test.WIDTH/2, centerY = Test.HEIGHT/2;
	private ArrayList<Rectangle> drawnWords;
	private boolean[][] visited;
	
	public Display_Recursion(int max, Map<String,Integer> w) {
		// Rank words by rank
		words = new ArrayList<Word>();
		drawnWords = new ArrayList<>();
		for(Map.Entry<String,Integer> k : w.entrySet()) {
			words.add(new Word(k.getKey(),k.getValue()));
		}
		visited = new boolean[5000][5000];
		System.out.println(words);
		System.out.println(words.size());
		setBackground(background);
		
		SCALE = max/250.0;
		System.out.println(SCALE);
	}
	
	public void paint(Graphics w) {
		drawnWords = new ArrayList<>();
		
		for(Word e : words) {
			
			int[] fin = new int[2];
			double minDis = (int)1e8;
			
			
			w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, (int)(e.getWeight()/SCALE)));
			w.setColor(e.getColor());
			FontMetrics fm = w.getFontMetrics();
			Rectangle bounds = getBounds((Graphics2D) w, e.getWord(), 0,0);
			
			int h1 = fm.getHeight() - fm.getDescent();
			int width = fm.stringWidth(e.getWord());
			width += Math.sqrt(width)/3;
			int height = e.getWord().matches(".*[gjpqy].*") ? h1 : h1 - fm.getDescent();
			
			// fin[0] = x, fin[1] = y;
			
			findSpot(centerX, centerY, width, height, fin, minDis);
			
			int finX = fin[0];
			int finY = fin[1];
			Rectangle end = new Rectangle(finX,finY,width,height);
			drawnWords.add(end);
			//editVisited(end.x,end.y,end.width,end.height);
			w.drawRect(end.x, end.y, end.width, end.height);
			int placeY = e.getWord().matches(".*[gjpqy].*") ? finY+height-fm.getDescent() :
				finY + height;
			w.drawString(e.getWord(), finX, placeY - (height - bounds.height)/2);
			
			//System.out.println(finX + " " +finY);
			
			/*for(boolean[] item : visited) {
				System.out.println(Arrays.toString(item));
			}*/
			
			System.out.println("min " + minDis);
		}
		
		System.out.println("DONE");
		
	}
	
	public void findSpot(int x, int y, int width, int height, int[] fin, double minDis) {
		if(visited[x][y]) return;
		Rectangle curr = new Rectangle(x,y,width,height);
		double midRectX = curr.getCenterX();
		double midRectY = curr.getCenterY();
		double dis = distance(centerX,midRectX,centerY,midRectY);
		//System.out.println("DISS " + dis + " " + minDis);
		if(dis >= minDis) return;
		//System.out.println("cool");
		for(Rectangle item : drawnWords) {
			if(curr.intersects(item)) {
				return;
			}
		}
		
		visited[x][y] = true;
		minDis = dis;
		fin[0] = x;
		fin[1] = y;
		
		findSpot(x+1,y,width,height, fin, minDis);
		findSpot(x-1,y,width,height, fin, minDis);
		findSpot(x,y+1,width,height, fin, minDis);
		findSpot(x,y-1,width,height, fin, minDis);
		findSpot(x+1,y+1,width,height, fin, minDis);
		findSpot(x+1,y-1,width,height, fin, minDis);
		findSpot(x-1,y+1,width,height, fin, minDis);
		findSpot(x-1,y-1,width,height, fin, minDis);
		
	}
	
	public void editVisited(int x, int y, int width, int height) {
		// i is row, j is column
		for(int i = y; i <= y+height;i++) {
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

import java.awt.*;
import java.util.*;

public class Display extends Canvas{
	
	private ArrayList<Word> words;
	private int max;
	private ArrayList<Rectangle> rectangles;
	int X, Y;
	
	public Display(int m, Map<String,Integer> w) {
		// Rank words by rank
		words = new ArrayList<Word>();
		for(Map.Entry<String,Integer> k : w.entrySet()) {
			words.add(new Word(k.getKey(),k.getValue()));
		}
		System.out.println("AA" + words);
		setBackground(Color.black);
		max = m;
		System.out.println("MAXXX " + max);
		
		rectangles = new ArrayList<>();
		
		X = 400; Y = 400;
	}
	
	public void paint(Graphics w) {
		ArrayList<Word> drawnWords = new ArrayList<Word>();
		//use to check intersects stuff later
		w.setColor(Color.CYAN);
		for(Word e : words) {
			//w.setFont(new Font("Gulim",Font.PLAIN,e));
			w.setFont(e.getFont());
			FontMetrics pp = w.getFontMetrics();
			int width = pp.stringWidth(e.getWord());
			int height = pp.getMaxAscent();
			int i = 0;
			int[] dx = {1,1,1,0,0,-1,-1,-1};
			int[] dy = {-1,0,1,1,-1,-1,0,1};
			int x = (int)(Math.random()*800), y = (int)(Math.random()*800);
			Rectangle r = new Rectangle(x,y,width,height);
			e.setDimensions(x, y, width, height);
			drawnWords.add(e);
			w.drawRect(x, y, width, height);
			w.drawString(e.getWord(), x, y+height);
				//int width = w.getFontMetrics().stringWidth(a);	
				//int xcor = (int)(Math.random()*800), ycor = (int)(Math.random()*800);
				
				//Rectangle r = new Rectangle(X, Y, width, height);
//				boolean ok = false;
//				while(!ok) {
//					boolean dab = false;
//					for(Rectangle item : rectangles) {
//						if(r.intersects(item)) {
//							break;
//						}
//					}
//					X += dx[i];
//					Y += dy[i];
//				}
				
//				w.drawRect(r.x,r.y,r.width,r.height);
//				rectangles.add(r);
				
				
				//w.drawString(a, xcor,ycor);
				
				//System.out.println(pp.getStringBounds(a, w));
//				
//				
//				i++;
//				i%=8;
			
		}
	}
}

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
		
		X = Main.WIDTH/2; Y = Main.HEIGHT/2;
	}
	
	public void paint(Graphics w) {
		ArrayList<Word> drawnWords = new ArrayList<Word>();
		//use to check intersects stuff later
		w.setColor(Color.CYAN);
		int i = 0;
		for(Word e : words) {
			//w.setFont(new Font("Gulim",Font.PLAIN,e));
			w.setFont(e.getFont());
			FontMetrics pp = w.getFontMetrics();
			int width = pp.stringWidth(e.getWord());
			//int height = pp.getMaxAscent();
			int height = (int) pp.getLineMetrics(e.getWord(), w).getHeight();
//			int[] dx = {1,1,1,0,0,-1,-1,-1};
//			int[] dy = {-1,0,1,1,-1,-1,0,1};
			int[] dx = {0,-1,-1,-1,0,1,1,1};
			int[] dy = {-1,-1,0,1,1,1,0,-1};
//			int x = (int)(Math.random()*800), y = (int)(Math.random()*800);
			int x = 400, y = 400;
			//Rectangle r = new Rectangle(x,y,width,height);
			e.setDimensions(x, y, width, height);
				//int width = w.getFontMetrics().stringWidth(a);	
				//int xcor = (int)(Math.random()*800), ycor = (int)(Math.random()*800);
				//Rectangle r = new Rectangle(X, Y, width, height);
				boolean ok = false;
				if(drawnWords.size()>0) {
				while(!ok) {
					for(Word item : drawnWords) {
						if(e.intersects(item)) {
							ok = false;
							break;
						}
						else {
							ok = true;
						}
					}
					if(ok) { break; } 
					e.changeX(dx[i]);
					e.changeY(dy[i]);
					}
				}
//				w.drawRect(r.x,r.y,r.width,r.height);
//				rectangles.add(r);	
//				w.drawString(a, xcor,ycor);
//				System.out.println(pp.getStringBounds(a, w));
			
				drawnWords.add(e);
				w.drawRect(e.x, e.y, e.width, e.height);
				w.drawString(e.getWord(), e.x, e.y+height);
				i++;
				i%=8;
		}
	}
}

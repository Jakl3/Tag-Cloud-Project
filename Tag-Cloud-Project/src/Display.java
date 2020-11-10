import java.awt.*;
import java.util.*;

public class Display extends Canvas{
	private Map<Integer, HashSet<String>> words;
	private int max;
	private ArrayList<Rectangle> rectangles;
	int X, Y;
	
	public Display(int m, Map<String,Integer> w) {
		//rank words by rank
		words = new TreeMap<Integer,HashSet<String>>(Collections.reverseOrder());
		for(Map.Entry<String,Integer> k : w.entrySet()) {
			if(!words.containsKey(k.getValue())) {
				words.put(k.getValue(), new HashSet<String>());
			}
			words.get(k.getValue()).add(k.getKey());
		}
		System.out.println("AA" + words);
		setBackground(Color.black);
		max = m;
		System.out.println("MAXXX " + max);
		
		rectangles = new ArrayList<>();
		
		X = 400; Y = 400;
	}
	
	public void paint(Graphics w) {
		w.setColor(Color.CYAN);
		for(Integer e : words.keySet()) {
			w.setFont(new Font("Gulim",Font.PLAIN,e));
			FontMetrics pp = w.getFontMetrics();
			int height = pp.getMaxAscent();
			int i = 0;
			int[] dx = {1,1,1,0,0,-1,-1,-1};
			int[] dy = {-1,0,1,1,-1,-1,0,1};
			for(String a : words.get(e)) {
				int width = w.getFontMetrics().stringWidth(a);	
				//int xcor = (int)(Math.random()*800), ycor = (int)(Math.random()*800);
				
				Rectangle r = new Rectangle(X, Y, width, height);
				boolean ok = false;
				while(!ok) {
					boolean dab = false;
					for(Rectangle item : rectangles) {
						if(r.intersects(item)) {
							break;
						}
					}
					X += dx[i];
					Y += dy[i];
				}
				
				w.drawRect(r.x,r.y,r.width,r.height);
				rectangles.add(r);
				
				
				//w.drawString(a, xcor,ycor);
				
				//System.out.println(pp.getStringBounds(a, w));
				
				
				i++;
				i%=8;
			}
		}
	}
}

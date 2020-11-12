import java.awt.*;
import java.util.*;

public class Display extends Canvas{
	private final Color background = Color.BLACK;
	private ArrayList<Word> words;
	private int max;
	int x, y;
	
	public Display(int m, Map<String,Integer> w) {
		// Rank words by rank
		words = new ArrayList<Word>();
		for(Map.Entry<String,Integer> k : w.entrySet()) {
			words.add(new Word(k.getKey(),k.getValue()));
		}
		System.out.println("AA" + words);
		setBackground(background);
		max = m;
		System.out.println("MAXXX " + max);
		
		x = Main.WIDTH/2; y = Main.HEIGHT/2;
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
			int[] dx = {0,-1,-1,-1,0,1,1,1};
			int[] dy = {-1,-1,0,1,1,1,0,-1};
			e.setDimensions(x, y, width, height);
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
				drawnWords.add(e);
				w.drawRect(e.x, e.y, e.width, e.height);
				w.drawString(e.getWord(), e.x, e.y+height);
				i++;
				i%=8;
		}
	}
}

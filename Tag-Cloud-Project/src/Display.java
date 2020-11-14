import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.util.*;

public class Display extends Canvas{
	private final Color background = Color.BLACK;
	//private final Color text = Color.CYAN;
	private ArrayList<Word> words;
	private int max;
	//int x, y;
	static final int centerX = Main.WIDTH/2, centerY = Main.HEIGHT/2;
	
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
		
		//x = Main.WIDTH/2; y = Main.HEIGHT/2;
	}
	
	public void paint(Graphics w) {
		ArrayList<Rectangle> drawnWords = new ArrayList<>();
		//use to check intersects stuff later
		//w.setColor(text);
		//int i = 0;
		
		Graphics2D w2 = (Graphics2D) w;
		
		for(Word e : words) {
			w.setFont(e.getFont());
			w.setColor(e.getColor());
			FontMetrics pp = w.getFontMetrics();
			
			/*int width = pp.stringWidth(e.getWord());
			int height = pp.getHeight() - pp.getDescent();*/
			
			int finX = 0, finY = 0;
			double mindis = 1e8;
			Rectangle end = new Rectangle();
			for(int i = 0; i < Main.WIDTH; i++) {
				height: for(int j = 0; j < Main.HEIGHT; j++) {
					//Rectangle pos = new Rectangle(i,j,width,height);
					Rectangle pos = getBounds(w2, e.getWord(), i, j);
					double shiftX = pos.width/20;
					pos.x -= shiftX;
					pos.width += 2 * shiftX;
			        
			        double shiftY = pos.height/20;
			        pos.y -= shiftY;
			        pos.height += 2 * shiftY;
					
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
			
			//System.out.println(finX + " " + finY);
			drawnWords.add(end);
			/*e.setDimensions(finX,finY,width,height);
			w.drawRect(e.x, e.y, e.width, e.height);
			w.drawString(e.getWord(), e.x, e.y+height-pp.getDescent());*/
			
			//w2.draw(end);
			w2.drawString(e.getWord(), finX, finY);
			
			/*int width = pp.stringWidth(e.getWord());
			//int height = pp.getMaxAscent();
			int height = pp.getHeight() - pp.getDescent();
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
				//w.setColor(background);
				//rectangle color
				w.drawRect(e.x, e.y, e.width, e.height);
				w.drawString(e.getWord(), e.x, e.y+height-pp.getDescent());
				i++;
				i%=8;*/
				
				
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

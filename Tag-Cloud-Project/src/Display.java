import java.awt.*;
import java.util.*;

public class Display extends Canvas{
	Map<String,Integer> words;
	public Display(Map<String,Integer> w) {
		words = w;
		setBackground(Color.black);
	}
	public void paint(Graphics w) {
		w.setColor(Color.green);
		for(String e : words.keySet()) {
			w.setFont(new Font("SANS_SERIF",Font.ITALIC,words.get(e)*5));
			w.drawString(e, (int)(Math.random()*1100), (int)(Math.random()*900));
		}
	}
}

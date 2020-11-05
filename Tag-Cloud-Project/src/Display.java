import java.awt.*;
import java.util.*;

public class Display extends Canvas{
	Map<Integer, HashSet<String>> words;
	public Display(Map<String,Integer> w) {
		//rank words by rank
		words = new TreeMap<Integer,HashSet<String>>(Collections.reverseOrder());
		for(Map.Entry<String,Integer> k : w.entrySet()) {
			if(!words.containsKey(k.getValue())) {
				words.put(k.getValue(), new HashSet<String>());
			}
			words.get(k.getValue()).add(k.getKey());
		}
		System.out.println(words);
		setBackground(Color.black);
	}
	public void paint(Graphics w) {
		w.setColor(Color.CYAN);
		for(Integer e : words.keySet()) {
			w.setFont(new Font("Gulim",Font.PLAIN,e*10));
			for(String a : words.get(e)) {
				int width = w.getFontMetrics().stringWidth(a);
				int height = w.getFontMetrics().getHeight();
				w.drawString(a, (int)(Math.random()*1100),(int)(Math.random()*900));
				
			}
		}
	}
}

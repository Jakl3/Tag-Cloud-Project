import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
@SuppressWarnings("serial")

public class Main extends JFrame {
	
	private static Data site;
	private static Display disp;
	public static final int WIDTH = 1200, HEIGHT = 1000;
	
	public Main() {
		super("Tag Cloud Project - Jack Le & Nathan Nguyen");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		setBackground(Color.BLACK);
		getContentPane().add(disp);
		
		setVisible(true);
		setLocationRelativeTo(null);
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					dispose();
			}
		});
		
		System.out.println("size: " + getContentPane().getSize().toString());
		System.out.println("height: " + getContentPane().getHeight());
		
	}
		
	public static void main(String[] args) throws Exception {
		/*Scanner kb = new Scanner(System.in);
		System.out.println("What website would you like to make a tag cloud out of?");
		String url = kb.nextLine();*/
		//String url = "https://www.espn.com/";
		String url = "https://www.xvideos.com/";
		//String url = "https://genshin.cc/";
		//String url = "fsafasf";
		Scraper scr = new Scraper(url);
		String s = scr.getWebsite();
		//System.out.println(s);
		site = new Data(s);	
		
		Map<String,Integer> tester = new TreeMap<>();
		tester.put("fsadfsaf", 10);
		tester.put("fsdafsffsadf", 10);
		tester.put("fdsafsagsag", 10);
		disp = new Display(site.getMax(),site.getCloud());
		//disp = new Display(site.getMax(),tester);
		
		/*System.out.println(s);
		
		System.out.println(site.getCloud());
		System.out.println("MAX " + site.getMax());*/
		
		new Main();
		//kb.close();
	}
}


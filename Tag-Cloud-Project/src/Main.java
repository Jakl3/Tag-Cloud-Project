import javax.swing.JFrame;
import java.awt.Color;
import java.util.*;

public class Main extends JFrame {
	
	static Data site;
	static final int WIDTH = 800, HEIGHT = 800;
	
	public Main() {
		super("Tag Cloud Project");
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		getContentPane().add(new Display(site.getMax(),site.getCloud()));
		setVisible(true);
		System.out.println("size:" + getContentPane().getSize());
		System.out.println("height:" + getContentPane().getHeight());
	}
	
	public static void main(String[] args) throws Exception {
		/*Scanner kb = new Scanner(System.in);
		System.out.println("What website would you like to make a tag cloud out of?");
		String url = kb.nextLine();*/
		String url = "https://www.pornhub.com/";
		Scraper scr = new Scraper(url);
		String s = scr.getWebsite();
		site = new Data(s);		
		
		System.out.println(s);
		
		System.out.println(site.getCloud());
		System.out.println(site.getMax());
		
		Main Cloud = new Main();
		//kb.close();
	}
}


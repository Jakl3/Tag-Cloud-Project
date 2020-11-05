import javax.swing.JFrame;
import java.util.*;

public class Main extends JFrame {
	static Data site;
	
	public Main() {
		super("Tag Cloud Project");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(new Display(site.getCloud()));
		setVisible(true);
		System.out.println(getContentPane().getSize());
		System.out.println(getContentPane().getHeight());
	}
	public static void main(String[] args) throws Exception {
		Scanner kb = new Scanner(System.in);
		System.out.println("What website would you like to make a tag cloud out of?");
		String url = kb.nextLine();
		Scraper scr = new Scraper(url);
		String s = scr.getWebsite();
		site = new Data(s);		
		
		Main Cloud = new Main();
		kb.close();
	}
}


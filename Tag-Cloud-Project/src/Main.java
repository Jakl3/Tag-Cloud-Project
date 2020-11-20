import java.awt.Color;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import javax.swing.JFrame;
@SuppressWarnings("serial")
//Computer Science 3 Tag Cloud Project - Nathan Nguyen(7th period) & Jack Le (4th Period)

/*
 * Main.java - extends the JFrame and sets it up. Instantiates the other classes
 * and allows them to interact with each other. It is the file that is run to
 * start the program.
 * 
 * @author Nathan Nguyen
 */
public class Main extends JFrame {
	
	//instance variables
	private static Data site;
	private static Display disp;
	public static final int WIDTH = 1200, HEIGHT = 950;
	
	//constructor, sets JFrame configurations
	public Main() {
		super("Tag Cloud Project - Jack Le & Nathan Nguyen");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WIDTH+20,HEIGHT+50);
		setBackground(Color.BLACK);
		getContentPane().add(disp);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		
	}
	
	//allows user to input website link to generate cloud from
	public static void main(String[] args) throws Exception {
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter the website you want to generate a tag cloud from.");
		String url = kb.nextLine();
		
		//String url = "https://www.espn.com/";
		//String url = "https://www.cfisd.net/en";
		System.out.println(url);
		
		Scraper scr = new Scraper(url);
		String s = scr.getWebsite();
		site = new Data(s);	
		disp = new Display(site.getMax(),site.getCloud());

		new Main();
	}
}


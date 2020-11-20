import java.awt.Color;
import java.util.Scanner;

import javax.swing.JFrame;
@SuppressWarnings("serial")

/**
 * Extends the JFrame and sets it up. Instantiates the other classes to
 * set up the tag cloud and allow them to interact with each other.
 * This is the file that is ran to start the program.
 * 
 * @author Nathan Nguyen
 */
public class Main extends JFrame {
	
	// Instance variables
	private static Data site;
	private static Display disp;
	public static final int WIDTH = 1200, HEIGHT = 950;
	
	// Constructor
	public Main() {
		super("Tag Cloud Project - Jack Le (4th Period) & Nathan Nguyen (7th Period)");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WIDTH+20,HEIGHT+50);
		setBackground(Color.BLACK);
		getContentPane().add(disp);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	// Main method that takes user input and starts the program
	public static void main(String[] args) throws Exception {
		Scanner kb = new Scanner(System.in);
		
		// Takes in keyboard input of the URL
		System.out.println("Enter the website you want to generate a tag cloud from.");
		System.out.println("Press \"Enter\" to use the default website");
		String url = kb.nextLine().equals("") ? "https://www.cfisd.net/en" : kb.nextLine();
		System.out.println("Creating Tag Cloud for \"" + url + "\"");
		
		// Instantiates the other classes and creates the Tag Cloud
		Scraper scr = new Scraper(url);
		String s = scr.getWebsite();
		site = new Data(s);	
		disp = new Display(site.getCloud());
		new Main();
		
		kb.close();
	}
}


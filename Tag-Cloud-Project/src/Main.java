import java.awt.*;
import java.util.*;
import javax.swing.*;
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
	public static final int WIDTH = 1200, HEIGHT = 950;
	private static final String defaultWebsite = "https://www.cfisd.net/en";
	private static Data site;
	private static Display disp;
	
	
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
		
		// Introduction
		System.out.println("Tag Cloud Project - CS3K 2020-2021");
		System.out.println("Created by Jack Le (4th Period) and Nathan Nguyen (7th Period)\n");
		
		// Takes in keyboard input of the URL
		System.out.println("Enter the website you want to generate a tag cloud from.");
		System.out.println("Press \"Enter\" to use the default website");
		String input = kb.nextLine();
		String url = input.equals("") ? defaultWebsite : input;
		
		// Instantiates the other classes and creates the Tag Cloud
		Scraper scr = new Scraper(url);
		site = new Data(scr.getWebsite());	
		disp = new Display(site.getCloud());
		System.out.println("Creating Tag Cloud for \"" + url + "\"\n");
		System.out.println(scr.getTime());
		System.out.println(site.getTime());
		System.out.println(disp.getNumWords());
		new Main();
		
		kb.close();
	}
}


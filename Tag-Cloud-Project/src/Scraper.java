import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Establishes a connection with the website of a given
 * URL. It then reads in the entire HTML of that website
 * and stores into a string, for quick access.
 * 
 * @author Jack Le
 */
public class Scraper  {
	
	// Instance Variables
	private String URL;
	private String website;
	
	// Constructor
	public Scraper(String url) throws Exception {
		this.URL = url;
		website = "";
		setup();
	}
	
	// Returns the string storing the HTML of the website
	public String getWebsite() {
		return website;
	}
	
	// Scrapes the website and stores the HTML into a string
	private void setup() throws Exception {
		try {
			URL url = new URL(URL);
			Scanner f = new Scanner(url.openStream());
			website = f.useDelimiter("\\Z").next().toLowerCase();
			f.close();
		}
		// Error: The given URL is not a proper URL address
		catch (MalformedURLException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			System.out.println(sw.toString());
			throw new MalformedURLException("\"" + URL + "\"" + " is not a proper URL address");
		}
		// Error: A connection could not be established with the website
		catch (IOException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			System.out.println(sw.toString());
			throw new IOException("A connection could not be established with \"" + URL + "\"");
		}
	}
}

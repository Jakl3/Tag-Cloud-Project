import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.NoSuchElementException;
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
		connect();
	}
	
	// Establishes a connection with the site
	private void connect() throws Exception {
		long startTime = System.nanoTime();
		URL url;
		URLConnection site;
		try {
			url = new URL(URL);
			site = url.openConnection();
			site.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) "
					+ "AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			site.connect();
			setup(site);
		}
		// Error: The HTML when accessing the website as a user is empty
		catch(NoSuchElementException e) {
			url = new URL(URL);
			site = url.openConnection();
			site.connect();
			setup(site);
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
		
		long endTime = System.nanoTime();
		System.out.println("Time to scrape HTML: " + ((endTime - startTime)/1000000) + " ms");
	}

	// Scrapes the website and stores the HTML as a string
	private void setup(URLConnection site) throws Exception {
		Scanner f = new Scanner(site.getInputStream());
		website = (f.useDelimiter( "\\Z" ).next().toLowerCase());
		f.close();
	}
	
	// Returns the string storing the HTML of the website
	public String getWebsite() {
		return website;
	}
}

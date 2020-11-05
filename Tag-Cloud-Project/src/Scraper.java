import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.net.*;

public class Scraper  {
	
	private String URL;
	private String website;
	
	// Constructor
	public Scraper(String url) throws Exception {
		this.URL = url;
		website = "";
		setup();
	}
	
	// Scrapes the website and converts the HTML into a string
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
			throw new MalformedURLException("This is not a proper URL");
		}
		// Error: Connection could not be opened with the website
		catch (IOException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			System.out.println(sw.toString());
			throw new IOException("Connection couldn't be opened");
		}
	}
	
	public String getWebsite() {
		return website;
	}
}

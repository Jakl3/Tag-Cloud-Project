import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.net.*;

public class Scraper  {
	
	private String URL;
	private String website;
	
	public Scraper(String url) throws Exception {
		this.URL = url;
		website = "";
		setup();
	}
	
	// change mayb?
	private void setup() throws Exception {
		try {
			URL url = new URL(URL);
			Scanner f = new Scanner(url.openStream());
			website = f.useDelimiter("\\Z").next().replaceAll("&.*?;", "").toLowerCase();
			f.close();
		}
		catch (MalformedURLException e) {
			// new URL() failed
			// ...
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			System.out.println(sw.toString());
			throw new MalformedURLException("Malformed URL");
		}
		catch (IOException e) {
			// openConnection() failed
			// ...
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

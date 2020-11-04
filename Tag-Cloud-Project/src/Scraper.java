import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.net.*;

public class Scraper  {
	
	private static Pattern p = Pattern.compile("(<(.+?)>(.+?)<(.+?)>)");
	private static String URL;
	private static String website;
	
	public Scraper(String url) {
		this.URL = url;
		setup();
	}
	
	private void setup() {
		try {
			URL myURL = new URL("http://books.toscrape.com/");
			URLConnection site = myURL.openConnection();
			site.connect();
			Scanner reader = new Scanner( site.getInputStream() );
			website = ( reader.useDelimiter( "\\Z" ).next() );
			reader.close();
		}
		catch (MalformedURLException e) {
			// new URL() failed
			// ...
			System.out.println("Malformed URL");
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			System.out.println(sw.toString());
		}
		catch (IOException e) {
			// openConnection() failed
			// ...
			System.out.println("Connection couldn't be opened");
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			System.out.println(sw.toString());
		}
		
		
		
	}
}

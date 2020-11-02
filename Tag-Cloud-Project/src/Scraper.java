import java.util.*;
import java.io.*;
import java.net.*;

public class Scraper  {
	
	public static void main(String[] args) {
		try {
			URL myURL = new URL("http://endless.horse/");
			URLConnection site = myURL.openConnection();
			site.connect();
			Scanner reader = new Scanner( site.getInputStream() );
			System.out.println( reader.useDelimiter( "\\Z" ).next() );
		}
		catch (MalformedURLException e) {
		// new URL() failed
			// ...
			}
			catch (IOException e) {
			// openConnection() failed
			// ...
			}
	}
}

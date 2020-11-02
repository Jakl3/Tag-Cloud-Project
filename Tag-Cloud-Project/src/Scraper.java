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
			reader.close();
		}
		catch (MalformedURLException e) {
			// new URL() failed
			// ...
			System.out.println("malformed");
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			System.out.println(sw.toString());
		}
		catch (IOException e) {
			// openConnection() failed
			// ...
			System.out.println("not open");
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			System.out.println(sw.toString());
		}
	}
}

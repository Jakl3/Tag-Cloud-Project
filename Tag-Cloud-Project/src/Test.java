import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scraper scr = new Scraper("https://www.pornhub.com/");
		String s = scr.getWebsite();
		
		PrintWriter out = new PrintWriter(new File("out.txt"));
		
		out.println(s);

		/*String s = "<a href = \"word\"> word </a>\r\n" + 
				"<a href=\"word\"> word </a>\r\n" + 
				"<h1> word </h1> worth 10\r\n" + 
				"<h2> word </h2> worth 8\r\n" + 
				"<h3> word </h3> worth 6\r\n" + 
				"<h4> word </h4> worth 4\r\n" + 
				"<h5> word </h5> worth 2\r\n" + 
				"<h6> word </h6> worth 1\r\n" + 
				"<p> word </p> worth 1\r\n" + 
				"<title> word </title> worth 10\r\n" + 
				"<a href = \"word\"> word </a> worth 5\r\n" + 
				"<ul>\r\n" + 
				" <li> word </li> worth 1\r\n" + 
				" <li> word </li> worth 1\r\n" + 
				"</ul>\r\n" + 
				"";*/
		
		Data d = new Data(s);
		System.out.println(d.getTags());
		System.out.println(d.getCloud());
	}
	
	
	private class Word {
		int weight;
		String word;
		
		public Word(int weight, String word) {
			this.weight = weight;
			this.word = word;
		}
	}

}

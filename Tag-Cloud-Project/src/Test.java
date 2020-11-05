import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class Test {

	public static void main(String[] args) throws Exception {
		Scraper scr = new Scraper("https://www.pornhub.com/");
		String s = scr.getWebsite();
		
		PrintWriter out = new PrintWriter(new File("C:\\Users\\jackl\\Desktop\\test\\out1.txt"));
		
		out.println(s);

		//String s = "<h1><h2><h3><a href=\"dab\"><li>dabby</li></a></h3></h2></h1>";
		
		Data d = new Data(s);
		System.out.println(d.getTags());
		for(String key : d.getCloud().keySet())
			System.out.println(key);
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

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scraper scr = new Scraper("http://endless.horse/");
		String s = scr.getWebsite();
		System.out.println(s);
		Data d = new Data(s);
		System.out.println(d.getTags());
		System.out.println(d.getTags().size());
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

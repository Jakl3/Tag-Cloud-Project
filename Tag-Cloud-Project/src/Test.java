import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	
	private static Pattern p = Pattern.compile("(<(.+?)>(.+?)<(.+?)>)");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "fsafsafafs<p>this is between the tags<\\p>fsafsafafs<p>fafsfthis is between the tags<\\\\p>fsafsafafs<p>this is behdshdshtween the tags<\\\\p>fsafsafafs<p>this is bggdsetween the tags<\\\\p>";
		
		System.out.println(getTagValues(s));
		
	}
	
	private static List<String> getTagValues(final String str) {
	    final List<String> tagValues = new ArrayList<String>();
	    final Matcher matcher = p.matcher(str);
	    while (matcher.find()) {
	        tagValues.add(matcher.group(1));
	    }
	    return tagValues;
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

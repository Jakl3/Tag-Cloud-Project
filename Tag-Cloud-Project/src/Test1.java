import java.util.*;
import java.util.regex.*;

public class Test1 {

	public static void main(String[] args) {
	    String str = "<h1> word </h1> worth 10\r\n" + 
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
	    		"</ul>";
	    String str3 = "<p>fsafasdfs</p>";
	    
	    String str2 = "<h1>apple</h1><h1><fasf></h1>";
	    System.out.println(str);
	    System.out.println();
	    for(String item : getTagValues(str)) System.out.println(item); // Prints [apple, orange, pear]
	}

	private static final Pattern TAG_REGEX = Pattern.compile("((<((h\\d)|(p)|(li)|(title))>)(.+?)(</((h\\d)|(p)|(li)|(title))>))");

	private static List<String> getTagValues(final String str) {
	    final List<String> tagValues = new ArrayList<String>();
	    final Matcher matcher = TAG_REGEX.matcher(str);
	    while (matcher.find()) {
	        tagValues.add(matcher.group(3) + " " + matcher.group(8).replaceAll(" ", ""));
	    }
	    return tagValues;
	}
}

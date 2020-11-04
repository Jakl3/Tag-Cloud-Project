import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Data {
	
	private static Pattern p = Pattern.compile("(<(.+?)>(.+?)<(.+?)>)");
	private static Map<String,Integer> cloud;
	
	public Data(String website) {
		List<String> tags = getTags(website);
		System.out.println(tags);
	}
	
	private static List<String> getTags(String str) {
	    final List<String> tagValues = new ArrayList<String>();
	    final Matcher matcher = p.matcher(str);
	    while (matcher.find()) {
	        tagValues.add(matcher.group(1));
	    }
	    return tagValues;
	}
}

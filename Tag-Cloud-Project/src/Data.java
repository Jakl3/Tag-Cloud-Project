import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Data {
	
	private static Pattern p = Pattern.compile("((<((h\\d)|(p)|(li)|(title))>)(.+?)(</((h\\d)|(p)|(li)|(title))>))");
	static List<String> tags;
	private static Map<String,Integer> cloud;
	
	public Data(String website) {
		tags = new ArrayList<String>();
		getTags(website);
		System.out.println(tags);
	}
	
	private void getTags(String str) {
	    final Matcher matcher = p.matcher(str);
	    while (matcher.find()) {
	    	tags.add(matcher.group(3) + " " + matcher.group(8).replaceAll(" ", ""));
	    }
	}
	
	public List<String> getTags() {
		return tags;
	}
	
	public Map<String,Integer> getCloud() {
		return cloud;
	}
}

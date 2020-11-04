import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Data {
	
	private static Pattern p = Pattern.compile("((<((h\\d)|(p)|(li)|(title))>)(.+?)(</((h\\d)|(p)|(li)|(title))>))");
	private List<String> tags;
	private Map<String,Integer> cloud;
	
	private Map<String,Integer> worth = new HashMap<String,Integer>() {{
			put("h1",10);
			put("h2",8);
			put("h3",6);
			put("h4",4);
			put("h5",2);
			put("h6",1);
			put("p",1);
			put("title",10);
			put("li",1);
	}};
	
	public Data(String website) {
		tags = new ArrayList<String>();
		cloud = new HashMap<String,Integer>();
		
		getTags(website);
		for(String item : tags) {
			String[] temp = item.split(" ");
			if(!cloud.containsKey(temp[1])) cloud.put(temp[1], 0);
			cloud.put(temp[1], cloud.get(temp[1])+worth.get(temp[0]));
		}
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

import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Data {
	
	//private static Pattern p = Pattern.compile("((<((h\\d)|(p)|(li)|(title))>)(.+?)(</((h\\d)|(p)|(li)|(title))>))");
	private static Pattern p1 = Pattern.compile("<(?<capture>h\\d|p|title)>\\s*(?<word>.*?)\\s*</\\k<capture>>",Pattern.DOTALL);
	private static Pattern p2 = Pattern.compile("<a href.*?=.*?\".*?\">\\s*(?<word>.*?)\\s*</a>",Pattern.DOTALL);
	private static Pattern p3 = Pattern.compile("<li>\\s*(?<word>.*?)\\s*</li>",Pattern.DOTALL);
	
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
			put("href",5);
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
		
	    Matcher m = p1.matcher(str);
	    while (m.find()) {	    	
	    	String tag = m.group("capture");
	    	String w = m.group("word").replaceAll("\n","");
	    	System.out.println(tag + " " + w);
	    	w = w.replaceAll("<.*?>.*?<.*?>", "");
	    	System.out.println(tag + " " + w);
	    	String[] word = w.split(" ");
	    	for(String item : word) {
	    		tags.add(tag + " " + item.trim());
	    	}
	    }
	    
	    m = p2.matcher(str);
	    while (m.find()) {
	    	String tag = "href";
	    	String w = m.group("word").replaceAll("<.*?>.*?<.*?>", "");
	    	String[] word = w.split(" ");
	    	for(String item : word) {
	    		tags.add(tag + " " + item.trim());
	    	}
	    }
	    
	    m = p3.matcher(str);
	    while (m.find()) {
	    	String tag = "li";
	    	String w = m.group("word").replaceAll("<.*?>.*?<.*?>", "");
	    	String[] word = w.split(" ");
	    	for(String item : word) {
	    		tags.add(tag + " " + item.trim());
	    	}
	    }
	}
	
	public List<String> getTags() {
		return tags;
	}
	
	public Map<String,Integer> getCloud() {
		return cloud;
	}
}

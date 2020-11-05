import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Data {
	
	private static Pattern p1 = Pattern.compile("(<(?<capture1>h\\d|p|title)>\\s*(?<word1>.*?)\\s*</\\k<capture1>>)|(<a (?<capture2>href).*?=.*?\".*?\">\\s*(?<word2>.*?)\\s*</a>)|(<(?<capture3>li)>\\s*(?<word3>.*?)\\s*</li>)",Pattern.DOTALL);
	
	private List<String> tags;
	private Map<String,Integer> cloud;
	
	private static Map<String,Integer> worth = new HashMap<String,Integer>() {{
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
	    	String tag = m.group("capture1");
	    	String w = m.group("word1");
	    	
	    	if(tag==null || w==null) {
	    		tag = m.group("capture2");
	    		w = m.group("word2");
	    	}
	    	if(tag==null || w==null) {
	    		tag = m.group("capture3");
	    		w = m.group("word3");
	    	}
	    	w = w.replaceAll("\n","").replaceAll("\\s+"," ");
	    	
	    	getTags(w);
	    	w = w.replaceAll("<.*?>", "");
	    	w = w.replaceAll("[^A-Za-z ]", "").replaceAll(" +", " ");
	    	String[] word = w.split(" ");
	    	for(String item : word) {
	    		if(item.equals("")) continue;
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

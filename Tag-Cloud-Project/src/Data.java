import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Data {
	
	// Pattern
	private static Pattern p = Pattern.compile("(<(?<capture1>h\\d|p|title)>\\s*(?<word1>.*?)\\s*</\\k<capture1>>)|(<a (?<capture2>href).*?=.*?\".*?\">\\s*(?<word2>.*?)\\s*</a>)|(<(?<capture3>li)>\\s*(?<word3>.*?)\\s*</li>)",Pattern.DOTALL);
	
	// Weights of each tag
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
		
	private List<String> tags;
	private Map<String,Integer> cloud;
	
	
	
	// Constructor
	public Data(String website) {
		tags = new ArrayList<String>();
		cloud = new HashMap<String,Integer>();
		getTags(website);
		
		for(String item : tags) {
			String[] temp = item.split(" ");
			if(!cloud.containsKey(temp[1])) cloud.put(temp[1], 0);
			cloud.put(temp[1], cloud.get(temp[1])+worth.get(temp[0]));
		}
		
		cloud = sortByValue(cloud);
	}
	
	// Finds groups of valid tags and words to include in the Tag Cloud
	private void getTags(String str) {
	    Matcher m = p.matcher(str);
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
	    	
	    	w = w.replaceAll("<.*?>", "").replaceAll("[^A-Za-z -_]", "").replaceAll("\\s+", " ");
	    	String[] word = w.split("[ -_]");
	    	for(String item : word) {
	    		if(item.equals("")) continue;
	    		tags.add(tag + " " + item.trim());
	    	}
	    }
	}
	
	// Returns the list of tags and words
	public List<String> getTags() {
		return tags;
	}
	
	// Returns the Tag Cloud
	public Map<String,Integer> getCloud() {
		return cloud;
	}
	
	// Sort the map by its values
	private static Map<String,Integer> sortByValue(Map<String,Integer> map) {
		Comparator<String> cmp = new vlc(map);
		TreeMap<String,Integer> res = new TreeMap<>(cmp);
		res.putAll(map);
		return res;
	}
	
	// Comparator to sort a map by its values
	private static class vlc implements Comparator<String> {
		 
		HashMap<String, Integer> map = new HashMap<String, Integer>();
	 
		public vlc(Map<String, Integer> map){
			this.map.putAll(map);
		}
	 
		public int compare(String s1, String s2) {
			if(map.get(s1) >= map.get(s2)) return -1;
			else return 1;
		}
	}
}

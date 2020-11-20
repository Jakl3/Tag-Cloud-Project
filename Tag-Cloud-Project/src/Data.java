import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@SuppressWarnings("serial")

/**
 * Receives the full HTML of a website in String form as an input. Assigns
 * the weight of each word enclosed in a tag based on the weight of every
 * tag that is enclosing the word. It then stores that weight inside a map
 * for quick access.
 * 
 * @author Jack Le
 */
public class Data {
	
	// Pattern
	private static final Pattern p = Pattern.compile("(<(?<capture1>h\\d|p|title|li)>\\s*(?<word1>.*?)\\s*</\\k<capture1>>)|"
			+ "(<a (?<capture2>href).*?=.*?\".*?\">\\s*(?<word2>.*?)\\s*</a>)",Pattern.DOTALL);
	
	// Weights of each tag
	private static final Map<String,Integer> worth = new HashMap<String,Integer>() {{
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
	
	// Instance Variables
	private List<String> tags;
	private Map<String,Integer> cloud;
	
	// Constructor
	public Data(String website) {
		long startTime = System.nanoTime();
		
		tags = new ArrayList<String>();
		cloud = new HashMap<String,Integer>();
		getTags(website);
		createCloud();
		cloud = sortByValue(cloud);
		
		long endTime = System.nanoTime();
		System.out.println("Data time: " + ((endTime - startTime)/1000000) + " ms");
	}
	
	// Returns the maximum weight of all words included in the Tag Cloud
	public int getMax() {
		return Collections.max(cloud.values());
	}
	
	// Returns the list of words and their associated tag
	public List<String> getTags() {
		return tags;
	}
	
	// Returns the Tag Cloud map
	public Map<String,Integer> getCloud() {
		return cloud;
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
	
	// Creates the tag cloud with the given list of tags
	private void createCloud() {
		for(String item : tags) {
			String[] temp = item.split(" ");
			if(!cloud.containsKey(temp[1])) {
				cloud.put(temp[1], 0);
			}
			cloud.put(temp[1], cloud.get(temp[1])+worth.get(temp[0]));
		}
	}
	
	// Sort the map by its values
	private static TreeMap<String,Integer> sortByValue(Map<String,Integer> map) {
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
			int a1 = map.get(s1), a2 = map.get(s2);
			if(a1 > a2) return -1;
			else if(a1 == a2) return s1.compareTo(s2);
			else return 1;
		}
	}
}

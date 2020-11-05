import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class Test {

	public static void main(String[] args) throws Exception {
		/*Scanner f = new Scanner(System.in);
		String web = f.nextLine();
		
		Scraper scr = new Scraper(web);
		String s = scr.getWebsite();*/
		String s = "<h1>hello fsfsaf your mom is super gay</h1>\r\n" + 
				"<h1>hello fsfsaf your mom is super gay</h1>\r\n" + 
				"<h1>hello fsfsaf your mom is super gay</h1>\r\n" + 
				"<h1>word fsfsaf your mom is super gay</h1>";

		Data d = new Data(s);
		System.out.println(d.getCloud());
		/*Map<String,Integer> cloud = sortByValue(d.getCloud());
		System.out.println(cloud);*/
		
		//f.close();
	}
	
	
	private static Map<String,Integer> sortByValue(Map<String,Integer> map) {
		Comparator<String> cmp = new vlc(map);
		TreeMap<String,Integer> res = new TreeMap<>(cmp);
		res.putAll(map);
		return res;
	}
	
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

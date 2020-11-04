import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner( System.in );
		String s = ( reader.useDelimiter( "\\Z" ).next() );
		reader.close();
		System.out.println(s);
		Data d = new Data(s);
		System.out.println(d.getTags());
		System.out.println(d.getTags().size());
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

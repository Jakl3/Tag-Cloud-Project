import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.awt.*;

public class Word extends Rectangle {
	
	String word;
	int weight;
	Font f;
	
	public Word(String word, int weight) {
		this.word = word;
		this.weight = weight;
		f = new Font("Verdana",Font.PLAIN,weight);
	}
}

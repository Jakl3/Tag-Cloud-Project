import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.awt.*;

public class Word extends Rectangle {
	
	String word;
	int weight,height,width;
	Font f;
	
	public Word(String word, int weight) {
		this.word = word;
		this.weight = weight;
		f = new Font("Verdana",Font.PLAIN,weight);
	}
	public Font getFont() {
		return f;
	}
	public String getWord() {
		return word;
	}
	public Integer getWeight() {
		return weight;
	}
	public String toString() {
		return word + ":" + weight;
	}
}

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
		f = new Font(Font.SANS_SERIF, Font.BOLD,weight);
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
	public void changeX(int n) {
		this.x += n;
	}
	public void changeY(int n) {
		this.y += n;
	}
	public void setDimensions(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	public String toString() {
		return word + ":" + weight;
	}
}

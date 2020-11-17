import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.awt.*;

public class Word extends Rectangle {
	
	private String word;
	private int weight;
	private Color c;
	public Word() {
		c = new Color((int)(Math.random()*0x1000000));
	}
	public Word(String word, int weight) {
		this.word = word;
		this.weight = weight;
		c = new Color((int)(Math.random()*0x1000000));
	}
	public Color getColor() {
		return c;
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
	public void setHeight(int n) {
		this.height = n;
	}
	public void setWidth(int n) {
		this.width = n;
	}
	public String toString() {
		return word + ":" + weight;
	}
}

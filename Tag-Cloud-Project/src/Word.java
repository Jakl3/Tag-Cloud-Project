import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.awt.*;

public class Word extends Rectangle {
	
	String word;
	int weight;
	
	public Word(String word, int weight) {
		this.word = word;
		this.weight = weight;
	}
}

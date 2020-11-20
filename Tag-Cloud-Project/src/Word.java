import java.awt.Color;
import java.awt.Rectangle;
@SuppressWarnings("serial")

/**
 * Stores data about each "Word", storing the string
 * itself, the weight of that word, as well as the
 * color to use when painting the word.
 * 
 * @author Nathan Nguyen
 */
public class Word extends Rectangle {
	
	// Instance Variables
	private String word;
	private int weight;
	private Color c;
	
	// Constructor
	public Word(String word, int weight) {
		this.word = word;
		this.weight = weight;
		c = new Color(Color.HSBtoRGB(((float)Math.random()),
				((float)Math.random()),
				(0.5F + ((float)Math.random())/2F)));
	}
	
	// Sets the dimensions of the rectangle
	public void setDimensions(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	// Returns the color to use when painting
	public Color getColor() {
		return c;
	}
	
	// Returns the string containing the word
	public String getWord() {
		return word;
	}
	
	// Returns the weight of the word
	public Integer getWeight() {
		return weight;
	}

	// Returns a string representation of Word
	public String toString() {
		return word + ":" + weight;
	}
}

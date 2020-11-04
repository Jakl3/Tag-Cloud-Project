import javax.swing.JFrame;
import java.util.*;

public class Main extends JFrame {
	public Main() {
		super("Tag Cloud Project");
		setSize(1100,900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HashMap<String,Integer> temp = new HashMap<String,Integer>();
		temp.put("test1", 5);
		temp.put("test2", 10);
		temp.put("test3", 15);
		temp.put("test4", 20);
		getContentPane().add(new Display(temp));
		setVisible(true);
	}
	public static void main(String[] args) throws Exception {
		Scanner kb = new Scanner(System.in);
		//System.out.println("What website would you like to use?");
		//String url = kb.nextLine();
		String url = "https://codingbat.com/java";
		Scraper scrap = new Scraper(url);
		Main Cloud = new Main();
		
	}
}


import javax.swing.JFrame;

public class Main extends JFrame {
	public Main() {
		super("Tag Cloud Project");
		setSize(1100,900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(new Display());
		setVisible(true);
	}
	public static void main(String[] args) {
		Main Cloud = new Main();
		System.out.println("bruh");
	}
}

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class Main extends JFrame {
	
	static Data site;
	static Display disp;
	static final int WIDTH = 1200, HEIGHT = 1000;
	static BufferedImage image;
	
	public Main() {
		super("Tag Cloud Project - Jack Le & Nathan Nguyen");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		setBackground(Color.BLACK);
		getContentPane().add(disp);
		
		
		setVisible(true);
		setLocationRelativeTo(null);
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					dispose();
			}
		});
		
		// Output into an image
		BufferedImage bi = new BufferedImage ( disp.getWidth (), disp.getHeight (), BufferedImage.TYPE_INT_ARGB );
        Graphics2D g2d = bi.createGraphics ();
        disp.paintAll ( g2d );
        g2d.dispose ();

        try
        {
            ImageIO.write ( bi, "png", new File ( "image.png" ) );
        }
        catch ( IOException e )
        {
            e.printStackTrace ();
        }
		
		System.out.println("size: " + getContentPane().getSize().toString());
		System.out.println("height: " + getContentPane().getHeight());
		
	}
	
	public static void main(String[] args) throws Exception {
		/*Scanner kb = new Scanner(System.in);
		System.out.println("What website would you like to make a tag cloud out of?");
		String url = kb.nextLine();*/
		//String url = "https://www.espn.com/";
		String url = "https://www.xvideos.com/";
		Scraper scr = new Scraper(url);
		String s = scr.getWebsite();
		//System.out.println(s);
		site = new Data(s);	
		
		Map<String,Integer> tester = new TreeMap<>();
		tester.put("my", 10);
		tester.put("a", 50);
		tester.put("use", 6);
		disp = new Display(site.getMax(),site.getCloud());
		//disp = new Display(site.getMax(),tester);
		
		/*System.out.println(s);
		
		System.out.println(site.getCloud());
		System.out.println("MAX " + site.getMax());*/
		
		new Main();
		//kb.close();
	}
}


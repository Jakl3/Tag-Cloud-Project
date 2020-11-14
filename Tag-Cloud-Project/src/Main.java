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
	static final int WIDTH = 1200, HEIGHT = 800;
	
	public Main() {
		super("Tag Cloud Project - Jack Le & Nathan Nguyen");
		
		setSize(WIDTH,HEIGHT);
		setBackground(Color.BLACK);
		getContentPane().add(disp);
		setVisible(true);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					dispose();
			}
		});
		
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
		String url = "https://www.pornhub.com/";
		Scraper scr = new Scraper(url);
		String s = scr.getWebsite();
		site = new Data(s);	
		disp = new Display(site.getMax(),site.getCloud());
		
		/*System.out.println(s);
		
		System.out.println(site.getCloud());
		System.out.println("MAX " + site.getMax());*/
		
		new Main();
		//kb.close();
	}
}


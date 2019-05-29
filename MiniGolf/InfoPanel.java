import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InfoPanel extends JPanel
{
	public static final int WIDTH = 700;
	public static final int HEIGHT = 700;
	public static final int i_WIDTH = 500;
	public static final int i_HEIGHT = 500;
	public static ImageIcon background = new ImageIcon("Astro Turf.jpg");
	
	JEditorPane instr = new JEditorPane();
	JScrollPane instr_scroll = new JScrollPane();
	
	java.net.URL instrURL = InfoPanel.class.getResource("TextSamplerDemoHelp.html");

	/**
	 * Overrides the paintComponent method to draw a background.
	 */
//	public void paintComponent(Graphics g) 
//	{		
//		super.paintComponent(g);
//		g.drawImage(background.getImage(),
//				0, 0, WIDTH, HEIGHT, null);
//	}//end paintComponent
	
	
	/**
	 * Setups the InfoPanel object so it can displayed.
	 */
	public InfoPanel()
	{
		try
		{
			instr.setPage(instrURL);
		}catch (IOException e) 
		{
			System.err.println("Attempted to read bad file");
		}
		
		instr_scroll.add(instr);
		instr_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		instr_scroll.setPreferredSize(new Dimension(i_WIDTH, i_HEIGHT));
		
		//add(instr);
		add(instr_scroll);
	}
    
}//end InfoPanel

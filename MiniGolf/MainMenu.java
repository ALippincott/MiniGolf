import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends JPanel
{
	public static final int WIDTH = 700;
	public static final int HEIGHT = 700;
	public static final int WIN_X = 400;
	public static final int WIN_Y = 200;
	public static ImageIcon background =new ImageIcon("Astro Turf.jpg");
	
	JButton playGame = new JButton("PLAY GAME");
	JButton exitButton = new JButton("EXIT");
	JButton leaderBoard = new JButton("LEADERBOARD");
	JPanel buttonPanel = new JPanel(new GridLayout(10, 1));
	
	/**
	 * Overrides the paintComponent method to draw a background.
	 */
	public void paintComponent(Graphics g) 
	{		
		super.paintComponent(g);
		g.drawImage(background.getImage(),
				0, 0, WIDTH, HEIGHT, null);
	}//end paintComponent
	
	/**
	 * Creates a MainMenu object that will display the game.
	 */
	public MainMenu()
	{
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		/**
		 * Formats the playGame button.
		 */
		playGame.setOpaque(false);
		playGame.setContentAreaFilled(false);
		playGame.setBorderPainted(false);
		playGame.setBorder(null);
		playGame.setFocusPainted(false);
		playGame.setForeground(Color.white);
		
		/**
		 * Formats the leaderBoard button.
		 */
		leaderBoard.setOpaque(false);
		leaderBoard.setContentAreaFilled(false);
		leaderBoard.setBorderPainted(false);
		leaderBoard.setBorder(null);
		leaderBoard.setFocusPainted(false);
		leaderBoard.setForeground(Color.white);
		
		/**
		 * Formats the exitButton button.
		 */
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.setBorder(null);
		exitButton.setFocusPainted(false);
		exitButton.setForeground(Color.white);
		
		/**
		 * Adds buttons to the JPanel
		 */
		buttonPanel.add(playGame);
		buttonPanel.add(leaderBoard);
		buttonPanel.add(exitButton);
		buttonPanel.setOpaque(false);
		
		add(buttonPanel, BorderLayout.CENTER);
		
		/**
		 * Creates a font based off a text file
		 */
		try 
		{
			Font acFont = Font.createFont(Font.TRUETYPE_FONT, 
					 new File("ArcadeClassic.ttf")).deriveFont(35f);
		    GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("ArcadeClassic.ttf")));
			exitButton.setFont(acFont); 
			leaderBoard.setFont(acFont);
			playGame.setFont(acFont);
		} catch (IOException e) 
		{
			e.printStackTrace();
		} catch (FontFormatException e){
			e.printStackTrace();
		}//end try-catch
	}//end MainMenu method
}//end MainMenu class

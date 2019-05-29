import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Main Window Where Mini Golf Starts
 *
 * Creates the JFrame to be used for the game.
 */
public class GamePlay extends JFrame {
	protected GamePlayPanel panel;
	protected MainMenu menu;
	protected InfoPanel iPanel;
	protected LeaderBoard board;
	
	/**
	 * Sets up the JFrame.
	 * Creates the Panels for the JFrame to display the different items.
	 * Loads the Menu.
	 */

	public GamePlay() 
	{
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setTitle("Mini Golf");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new GamePlayPanel();
		board = new LeaderBoard();
		menu = new MainMenu();
		iPanel = new InfoPanel();
		add(menu);
		pack();
	}// end GamePlay()
	
	/**
	 * Initialized the window and calls the MainMenu object.
	 * @param args Ignored
	 */
	public static void main(String[] args) 
	{
		GamePlay menu = new GamePlay();
	}//end main method
	
	/**
	 * Creates the Main Menu JPanel
	 * @author Team
	 * Sets up the Main Menu with options such as play game, instructions, leaderboard or exit.
	 */

	class MainMenu extends JPanel
	{
		static final int WIDTH = 700;
		public static final int HEIGHT = 700;
		public static final int WIN_X = 400;
		public static final int WIN_Y = 200;
		public ImageIcon background =
				new ImageIcon("Astro Turf.jpg");
		
		// Options for Main Menu
		JButton playGame = new JButton("PLAY GAME");
		JButton howto = new JButton("HOW   TO   PLAY");
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
			playGame.addActionListener(new PlayListener());
			
			/**
			 * Formats the How-To button.
			 */
			howto.setOpaque(false);
			howto.setContentAreaFilled(false);
			howto.setBorderPainted(false);
			howto.setBorder(null);
			howto.setFocusPainted(false);
			howto.setForeground(Color.white);
			howto.addActionListener(new HowToListener());
			
			/**
			 * Formats the leaderBoard button.
			 */
			leaderBoard.setOpaque(false);
			leaderBoard.setContentAreaFilled(false);
			leaderBoard.setBorderPainted(false);
			leaderBoard.setBorder(null);
			leaderBoard.setFocusPainted(false);
			leaderBoard.setForeground(Color.white);
			leaderBoard.addActionListener(new BoardListener());
			
			/**
			 * Formats the exitButton button.
			 */
			exitButton.setOpaque(false);
			exitButton.setContentAreaFilled(false);
			exitButton.setBorderPainted(false);
			exitButton.setBorder(null);
			exitButton.setFocusPainted(false);
			exitButton.setForeground(Color.white);
			exitButton.addActionListener(new ExitListener());
			
			/**
			 * Adds buttons to the JPanel
			 */
			buttonPanel.add(playGame);
			buttonPanel.add(howto);
			buttonPanel.add(leaderBoard);
			buttonPanel.add(exitButton);
			buttonPanel.setOpaque(false);
			
			add(buttonPanel, BorderLayout.CENTER);
			
			/**
			 * Creates a font based off of a font file
			 */
			try 
			{
				Font acFont = Font.createFont(Font.TRUETYPE_FONT, 
						 new File("ArcadeClassic.ttf")).deriveFont(50f);
			    GraphicsEnvironment ge = 
			         GraphicsEnvironment.getLocalGraphicsEnvironment();
			    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("ArcadeClassic.ttf")));
				exitButton.setFont(acFont); 
				leaderBoard.setFont(acFont);
				playGame.setFont(acFont);
				howto.setFont(acFont);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (FontFormatException e){
				e.printStackTrace();
			}//end try-catch
		}//end MainMenu method
		
		class PlayListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				setFocusable(false);
				remove(buttonPanel);
				add(panel);
				setVisible(true);
				setFocusable(true);
			}//end actionPerformed (PlayListener)
		}//end PlayListener
		
		class BoardListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				setFocusable(false);
				remove(buttonPanel);
				add(board);
				setVisible(true);
				setFocusable(true);
			}//end actionPerformed (BoardListener)
		}//end BoardListener
		
		class HowToListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				setFocusable(false);
				remove(buttonPanel);
				add(iPanel);
				setVisible(true);
				setFocusable(true);
			}//end actionPerformed (BoardListener)
		}//end BoardListener
		
		class ExitListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}//end actionPerformed (ExitListener)
		}//end ExitListener
	}//end MainMenu class
}//end GamePlay




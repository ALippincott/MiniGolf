import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LeaderBoard extends JPanel
{
	public static final int WIDTH = 700;
	public static final int HEIGHT = 700;
	public static final int b_WIDTH = 500;		//width of gray jpanel
	public static final int b_HEIGHT = 500;		//height of gray jpanel
	public static ImageIcon background = new ImageIcon("Astro Turf.jpg");
	
	ScoreBoard scoreBoard = new ScoreBoard(b_WIDTH,b_HEIGHT);

	/**
	 * Overrides the paintComponent method to draw a background.
	 */
	public void paintComponent(Graphics g) 
	{		
		super.paintComponent(g);
		g.drawImage(background.getImage(),
				0, 0, WIDTH, HEIGHT, null);
	}//end paintComponent
	
	public LeaderBoard()
	{
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		scoreBoard.setPreferredSize(new Dimension(b_WIDTH, b_HEIGHT));
		add(scoreBoard, BorderLayout.CENTER);
	}//end LeaderBoard()
}//end LeaderBoard

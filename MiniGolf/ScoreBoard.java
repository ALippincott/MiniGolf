import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreBoard extends JPanel{
	public int width;
	public int height;
	
	public ScoreBoard(int a, int b){
		width = a;
		height = b;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Color customColor = new Color(200,250,180);	//R G B (200, 225, 150) (200,250,150)
		g.setColor(customColor);
		//g.setColor(Color.RED);
		g.fillRect(0, 0, width, height);
	}
	
	/**
	 * Add JLabel "title" to top of ScoreBoard JPanel
	 */
	JLabel title = new JLabel("Mini Golf"); {
	add(title, BorderLayout.CENTER);
	title.setFont(new Font("Arial", Font.BOLD, 50));
	
	}
}//end class scoreboard

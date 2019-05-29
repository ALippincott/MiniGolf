import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class GamePlayPanel extends JPanel {
	// Window Height and Width
	public static final int WIDTH = 700;
	public static final int HEIGHT = 700;
	// Set Background Image
	public static ImageIcon background = new ImageIcon("Astro Turf.jpg");
	// Set Starting Location of the Ball
	protected double xBall = 350;  
	protected double yBall = 650;
	// Set Min and Max of Progress Bar
	private static int min = 0;
	private static int max = 100;
	JProgressBar progressBar = new JProgressBar(min, max);
	// Timer for Progress Bar
	protected javax.swing.Timer progressBarTimer = new javax.swing.Timer(100, new ProgressBarListener());
	// Timer for Direction
	protected javax.swing.Timer doplerRadarTimer = new javax.swing.Timer(30, new doplerRadarTimerListener());
	// Move Ball and Detect Collision
	protected javax.swing.Timer timer = new javax.swing.Timer(30, new TimeListener());
	// Buttons For Direction Speed and to Putt
	protected JButton directionButton = new JButton("Direction");
	protected JButton powerButton = new JButton("Speed");
	protected JButton puttButton = new JButton("PUTT");
	// Labels for Name and Score
	protected JLabel nameLabel = new JLabel("");
	protected JLabel scoreLabel = new JLabel("");
	// Enter Name and Submit
	protected JButton nameSubmit = new JButton("Enter Name");
	protected JTextField nameField = new JTextField(10);
	// Values of power and direction
	protected int power;
	protected int direction;
	protected BallMovement ball;
	protected BallMovement hole;
	// Creates Random Hole Location
	protected Random rand = new Random();
	protected int xCoordHole=rand.nextInt(575)+50;
	protected int yCoordHole=rand.nextInt(400)+100;
	// Store Score
	protected int score = 0;
	// Set Position of Direction Bar
	protected int xArrow = 362;
	protected int yArrow = 662;
	// Set Angle of Direction Bar
	protected double angle = 90;
	// Acutal Direction Bar
	protected Rectangle directionBar;
	// SlopeX and SlopeY are the sin and cos of the angle from the direction
	protected double slopeX;
	protected double slopeY;
	// Angle Move adds 90 to the angle from the direction to fit the movement
	protected double angleMove;
	// Controls the speed of the ball by multiplying the sin and cos
	protected double speed;
	/**
	 * 
	 * 
	 */
	public GamePlayPanel() {
		setBackground(Color.black);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		add(nameField);
		add(nameSubmit);
		add(nameLabel);
		add(scoreLabel);
		progressBar.setStringPainted(true); // Make the progress bar show the numbers
		add(progressBar);
		add(directionButton);
		add(powerButton);
		puttButton.setEnabled(false);
		add(puttButton);
		
		// Create 1st Instance of Ball and Hole
		ball= new BallMovement(xBall,yBall);
		hole = new BallMovement(xCoordHole, yCoordHole);
		
		// Add all of the action listeners
		directionButton.addActionListener(new DirectionListener());
		powerButton.addActionListener(new PowerListener());
		puttButton.addActionListener(new PuttListener());
		nameSubmit.addActionListener(new NameListener());
		timer.addActionListener(new TimeListener());
		doplerRadarTimer.addActionListener(new doplerRadarTimerListener());
		powerButton.setEnabled(false);
		doplerRadarTimer.start();
	}
	/**
	 * Draws the background and the rectangle for direction.
	 * @param g The graphics element used for drawing.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(background.getImage(),
				0, 0, WIDTH, HEIGHT, null);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		hole.drawHole(g2,xCoordHole, yCoordHole);
		ball.draw(g2,xBall,yBall); 
		
		AffineTransform oldTransform = g2.getTransform();
		directionBar = new Rectangle(xArrow, yArrow, 5, 100);
		
		AffineTransform transform = g2.getTransform();
		transform.rotate(Math.toRadians(angle), xArrow, yArrow);
	
		g2.setTransform(transform);
		g2.fill(directionBar);
		g2.setTransform(oldTransform);
		
	}
	/**
	 * Sets the value of the progress bar.
	 * @param value The integer passed to set the value of the progress bar.
	 */
	public void updateProgress(int value){
		progressBar.setValue(value);
	}
	
	class ProgressBarListener implements ActionListener{
		private int a = 0;
		public void actionPerformed(ActionEvent e) {
			a += 1;
			updateProgress(a);
			if(a ==100){
				a = 0;	
			}
			// If both buttons have been clicked start the putt
			if(directionButton.isEnabled() == false && powerButton.isEnabled()== false){
				progressBarTimer.stop();
				puttButton.setEnabled(true);
				progressBar.setValue(0);
			}
		}
	}
	class NameListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			nameLabel.setText(nameField.getText());
			nameField.setEnabled(false);
			nameSubmit.setEnabled(false);
		}
	}
	
	class doplerRadarTimerListener implements ActionListener{
		// Every time click the Direction Bar is moved and reset once too big
		public void actionPerformed(ActionEvent e) {
			if(angle < 90 || angle > 270){
				angle = 90;
			}
			angle++;
			repaint();
		}
	}
	
	class DirectionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			doplerRadarTimer.stop();
			progressBarTimer.start();
			directionButton.setEnabled(false);
			powerButton.setEnabled(true);
		}
	}
	
	class PowerListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			power= progressBar.getValue();
			powerButton.setEnabled(false);	
		}	
	}
	
	class PuttListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			angleMove = angle + 90;
			timer.start();
			puttButton.setEnabled(false);
		}
	}
	
	public void reset(){
		xCoordHole=rand.nextInt(575)+50;
		yCoordHole=rand.nextInt(400)+100;
		doplerRadarTimer.start();
		directionButton.setEnabled(true);
	}
	
	class TimeListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(xBall + 12.5 <= xCoordHole + 45  && yBall +12.5 <= yCoordHole + 45 && 
					xBall + 12.5 >= xCoordHole + 5 && yBall + 12.5 >= yCoordHole+5){
				timer.stop();
				// Update Score
				score ++;
				scoreLabel.setText(""+score);
				// Reset ball position
				xBall = 350;
				yBall = 650;
				ball= new BallMovement(xBall,yBall);
				hole = new BallMovement(xCoordHole, yCoordHole);
				// *********animation to pause reload***************
				// Call to function to reset game
				reset();
				repaint();
			}
			// Sets Bounds to determine if no collision with the hole
			if(xBall < 0 || xBall > 675 || yBall < 100 || yBall > 700){
				timer.stop();
				//try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("LeaderBoard.txt", true)))) {
				    //out.println(nameLabel.getText() +"\t"+score);
				//}catch (IOException e1) {
				    //exception handling left as an exercise for the reader
				//}
				JOptionPane.showMessageDialog(null,"GAMEOVER, THANKS FOR PLAYING");
				System.exit(0);
			}
			// Select case to deal with Speed from Progress Bar
			if( power >= 0 && power <= 20){
				speed = 1;
			}
			else if (power > 20 && power <= 40){
				speed = 2;
			}
			else if (power > 40 && power <= 60){
				speed = 3;
			}
			else if (power > 60 && power <= 80){
				speed = 4;
			}
			else if (power > 80 && power <= 100){
				speed = 5;
			}	
			
			// Add 90 to angle coming in
			// Angle from 180 to 360 For Movement
			slopeX = Math.cos(Math.toRadians(angleMove))*speed;
			slopeY = Math.sin(Math.toRadians(angleMove))*speed;
			
			if (angle <=360){
				xBall += slopeX;
				yBall += slopeY;
				repaint();
			}	
		}// end of action performed
	}// end of Time Listener
}

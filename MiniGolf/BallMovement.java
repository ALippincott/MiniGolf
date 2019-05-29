import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;

public class BallMovement  {
	private double x;
	private double y;
	
	public BallMovement(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public BallMovement(double xBall, double yBall) {
		this.x = xBall;
		this.y = yBall;
	}

	public void draw(Graphics2D g2,double xBall,double yBall)
	{
		Ellipse2D.Double ball = new Ellipse2D.Double(xBall,yBall, 25, 25);
		g2.setColor(Color.WHITE);
		g2.fill(ball);
		g2.draw(ball);
	}
	
	public void drawHole(Graphics2D g2,int x,int y)
	{
		Ellipse2D.Double hole = new Ellipse2D.Double(x,y, 50, 50);
		g2.setColor(Color.BLACK);
		g2.fill(hole);
		g2.draw(hole);
	}
}

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class MapMaker extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Point> points = new ArrayList<>();
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		
		Graphics twoDGraphics = (Graphics2D) g;
		
		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;
		
		if(!points.isEmpty())
		{
			Point lastPoint = points.get(0);
			
			for (int i = 1; i < points.size(); i++) {
				Point newPoint = points.get(i);
				
				if(lastPoint.distance(newPoint) < 40)
				{
					twoDGraphics.drawLine(lastPoint.x + centerX, centerY - lastPoint.y, newPoint.x + centerX, centerY - newPoint.y);
				}
				lastPoint = newPoint;
			}
		}
	}
	
	public void addPosition(double x, double y)
	{
		int xInt = (int) Math.round(x);
		int yInt = (int) Math.round(y);
		points.add(new Point(xInt, yInt));
		repaint();
	}
}

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class MyOval extends MyDrawing {

	public MyOval(int x, int y, int w, int h, Color fc, Color lc, boolean isShadowed, boolean isDashed, int lineWidth,
			float[] strokePattern, int number) {
		super(x, y, w, h, fc, lc, isShadowed, isDashed, lineWidth, strokePattern, number);
	}

	public MyOval(int xpt, int ypt) {
		super(xpt, ypt);
	}

	@Override
	public void draw(Graphics g) {
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		int a = getAlpha();

		if (w < 0) {
			x += w;
			w *= -1;
		}
		if (h < 0) {
			y += h;
			h *= -1;
		}

		Graphics2D g2 = (Graphics2D) g;
		if (getDashed())
			g2.setStroke(new MyDashStroke(getLineWidth(), getStrokePattern()));
		else
			g2.setStroke(new BasicStroke(getLineWidth()));
		if (getShadowed()) {
			g2.setColor(Color.black);
			g2.fillOval(x + 5, y + 5, w, h);
		}
		int R = getfillColor().getRed();
		int G = getfillColor().getGreen();
		int B = getfillColor().getBlue();
		if(getisTransparency()) {
			g2.setColor(new Color(R, G, B, a));
			}else {
				g2.setColor(getfillColor());
			}
		g2.fillOval(x, y, w, h);
		g2.setColor(getLineColor());
		g2.drawOval(x, y, w, h);
		setRegion();
		super.draw(g2);
	}

	@Override
	public boolean contains(int x, int y) {
		return region.contains(x, y);
	}

	@Override
	public void setRegion() {
		region = new Ellipse2D.Double((double) x, (double) y, (double) w, (double) h);
	}

}

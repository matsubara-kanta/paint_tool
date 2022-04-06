import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class MyHexagon extends MyDrawing {

	private int rot;
	private int cx, cy;
	private int first_x, first_y;
	boolean flag = false;
	private double w1, w2, rad;
	int xw[] = new int[6];
	int yw[] = new int[6];
	int shadow_xw[] = new int[6];
	int shadow_yw[] = new int[6];

	public MyHexagon(int x, int y, int width, int height, Color fc, Color lc, boolean isShadowed,
			boolean isDashed, int lineWidth, float[] strokePattern,int number) {
		super(x, y, width, height, fc, lc, isShadowed, isDashed, lineWidth, strokePattern,number);
		first_x = x;
		first_y = y;
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
		rad = 360 / 6;
		w1 = (double) 0.5 * (w + h); //半径
		w2 = 0.0;
		cx = x; //中心座標
		cy = y; //中心座標
		calcPosition(xw, yw);
		if (rot != 0)
			for (int i = 0; i < 11; i++)
				calcRotate(i, xw, yw);

		for (int i = 0; i < 6; i++) {
			shadow_xw[i] = xw[i] + 5;
			shadow_yw[i] = yw[i] + 5;
		}

		if (flag) {
			if (getShadowed()) {
				g2.setColor(Color.black);
				g2.fillPolygon(shadow_xw, shadow_yw, 6);
			}

			int R = getfillColor().getRed();
			int G = getfillColor().getGreen();
			int B = getfillColor().getBlue();
			if(getisTransparency()) {
				g2.setColor(new Color(R, G, B, a));
				}else {
					g2.setColor(getfillColor());
				}
			g2.fillPolygon(xw, yw, 6);
			g2.setColor(getLineColor());
			g2.drawPolygon(xw, yw, 6);
			setRegion();
			drawOriginal(g);
		}
		flag = true;
/*
		for (int i = 0; i < 6; i++) {
			System.out.println("xw[" + i + "]:" + xw[i] + "   yw[" + i + "]:" + yw[i]);
		}
*/
	}

	// 多角形の座標計算
	void calcPosition(int x[], int y[]) {
		int i;
		for (i = 0; i < 6; i++) {
			if (i % 2 == 0 || w2 == 0.0) {
				x[i] = (cx + (int) (Math.sin((rad * i) / 180 * 3.14) * w1));
				y[i] = cy - (int) (Math.cos((rad * i) / 180 * 3.14) * w1);
			} else {
				x[i] = cx + (int) (Math.sin((rad * i) / 180 * 3.14) * w2);
				y[i] = cy - (int) (Math.cos((rad * i) / 180 * 3.14) * w2);
			}
		}
	}

	// 回転計算
	void calcRotate(int n, int x[], int y[]) {
		double px, py, w;
		px = x[n] - cx;
		py = y[n] - cy;
		w = rot / 180.0 * 3.14;
		x[n] = (int) (px * Math.cos(w)) - (int) (py * Math.sin(w)) + cx;
		y[n] = (int) (px * Math.sin(w)) + (int) (py * Math.cos(w)) + cy;
	}

	public int getFirst_x() {
		return first_x;
	}

	public int getFirst_y() {
		return first_y;
	}

	public int getPositionX() {
		return xw[5];
	}

	public int getPositionY() {
		return yw[0];
	}

	public int getWidth() {
		return xw[1] - xw[5];
	}

	public int getHeight() {
		return yw[3] - yw[0];
	}

	public void drawOriginal(Graphics g) {
		if (isSelected) {
			g.setColor(Color.black);
			g.fillRect(xw[0] - SIZE / 2, yw[0] - SIZE, SIZE, SIZE);
			g.fillRect(xw[1], yw[0] - SIZE / 2, SIZE, SIZE);
			g.fillRect(xw[2], (yw[1] + yw[2])/2 - SIZE / 2, SIZE, SIZE);
			g.fillRect(xw[2], yw[3] - SIZE / 2, SIZE, SIZE);
			g.fillRect(xw[3] - SIZE / 2, yw[3], SIZE, SIZE);
			g.fillRect(xw[4] - SIZE, yw[3] - SIZE / 2, SIZE, SIZE);
			g.fillRect(xw[4] - SIZE, (yw[4] + yw[5])/2 - SIZE / 2, SIZE, SIZE);
			g.fillRect(xw[5] - SIZE, yw[0] - SIZE / 2, SIZE, SIZE);
		}
	}

	public int[] getXpoints() {
		return xw;
	}

	public int[] getYpoints() {
		return yw;
	}

	@Override
	public boolean contains(int x,int y) {
		return region.contains(x, y);
	}

	@Override
	public void setRegion() {
		region = new Polygon(xw,yw,6);
		//System.out.println("領域を" + "x:" + x + "   y:" + y + "   w:" + w + "   h:" + h + " にセットしました");
	}
}

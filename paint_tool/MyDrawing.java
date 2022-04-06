import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;

public class MyDrawing implements Cloneable, Serializable {
	int x;
	int y;
	int w;
	int h;
	Color lineColor, fillColor;
	int lineWidth;
	boolean isDashed;
	boolean isShadowed;
	boolean isSelected;
	boolean isTransparency;
	Shape region;
	int alpha;
	final int SIZE = 7;
	private float strokePattern[] = { 10, 15, 0, 0 };
	int number; //図形判別用 1:四角形    2:楕円    3:11角形    4:6角形

	public MyDrawing(int xpt, int ypt, int width, int height, Color fillColor, Color lineColor, boolean isShadowed,
			boolean isDashed, int lineWidth, float[] strokePattern, int number) {
		x = xpt;
		y = ypt;
		w = width;
		h = height;
		this.fillColor = fillColor;
		this.lineColor = lineColor;
		this.lineWidth = lineWidth;
		this.isShadowed = isShadowed;
		this.isDashed = isDashed;
		this.strokePattern = strokePattern;
		this.number = number;
		alpha = 50;
		this.isTransparency = false;
	}

	public MyDrawing(int xpt, int ypt) {
		x = xpt;
		y = ypt;
		w = h = 40;
		lineColor = Color.black;
		fillColor = Color.white;
		lineWidth = 1;
	}

	public void draw(Graphics g) {
		if (isSelected) {
			g.setColor(Color.black);
			g.fillRect(x + w / 2 - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
			g.fillRect(x - SIZE / 2, y + h / 2 - SIZE / 2, SIZE, SIZE);
			g.fillRect(x + w / 2 - SIZE / 2, y + h - SIZE / 2, SIZE, SIZE);
			g.fillRect(x + w - SIZE / 2, y + h / 2 - SIZE / 2, SIZE, SIZE);
			g.fillRect(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
			g.fillRect(x + w - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
			g.fillRect(x - SIZE / 2, y + h - SIZE / 2, SIZE, SIZE);
			g.fillRect(x + w - SIZE / 2, y + h - SIZE / 2, SIZE, SIZE);
		}
	}

	public void drawOriginal(Graphics g, int xpt[], int ypt[], int width, int height) {
		if (isSelected) {
			g.setColor(Color.black);
			g.fillRect(xpt[0] - SIZE / 2, ypt[0] - SIZE, SIZE, SIZE);
			g.fillRect(xpt[3] - SIZE / 2, ypt[0] - SIZE / 2, SIZE, SIZE);
			g.fillRect(xpt[3] + SIZE / 2, (ypt[0] + ypt[6]) / 2 - SIZE / 2, SIZE, SIZE);
			g.fillRect(xpt[3] - SIZE / 2, ypt[6] - SIZE / 2, SIZE, SIZE);
			g.fillRect(xpt[0] - SIZE / 2, ypt[6] + SIZE / 2, SIZE, SIZE);
			g.fillRect(xpt[8] - SIZE / 2, ypt[6] - SIZE / 2, SIZE, SIZE);
			g.fillRect(xpt[8] - SIZE, (ypt[0] + ypt[6]) / 2 - SIZE / 2, SIZE, SIZE);
			g.fillRect(xpt[8] - SIZE / 2, ypt[0] - SIZE / 2, SIZE, SIZE);
		}
	}

	public MyDrawing clone() {
		MyDrawing buffer;
		try {
			buffer = (MyDrawing) super.clone();
		} catch (CloneNotSupportedException ce) {
			throw new RuntimeException();
		}
		return buffer;
	}

	public boolean contains(int x, int y) {
		return region.contains(x, y);
	}

	public void move(int dx, int dy) {
		x = dx;
		y = dy;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setSize(int w, int h) {
		this.w = w;
		this.h = h;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public void setfillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}

	public void setDashed(boolean b) {
		isDashed = b;
	}

	public void setStrokePattern(float[] strokePattern) {
		this.strokePattern = strokePattern;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void setRegion() {
		region = new Rectangle(x, y, w, h);
		//System.out.println("領域を" + "x:" + x + "   y:" + y + "   w:" + w + "   h:" + h + " にセットしました");
	}

	public void setShadowed(boolean isShadowed) {
		this.isShadowed = isShadowed;
	}

	public void setisTransparency(boolean isTransparency) {
		this.isTransparency = isTransparency;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getW() {
		return this.w;
	}

	public int getH() {
		return this.h;
	}

	public int getLineWidth() {
		return this.lineWidth;
	}

	public boolean getDashed() {
		return isDashed;
	}

	public Color getLineColor() {
		return this.lineColor;
	}

	public Color getfillColor() {
		return this.fillColor;
	}

	public float[] getStrokePattern() {
		return this.strokePattern;
	}

	public boolean getSelected() {
		return this.isSelected;
	}

	public boolean getShadowed() {
		return this.isShadowed;
	}

	public int getAlpha() {
		return this.alpha;
	}

	public boolean getisTransparency() {
		return this.isTransparency;
	}


}

import java.awt.Color;

public class RectState implements State {
	StateManager stateManager;
	private boolean isShadowed = false;
	private boolean isDashed = false;
	private int lineWidth = 1;
	private float strokePattern[] = { 10, 15, 0, 0 };
	MyRectangle myrectangle;
	int first_x;
	int first_y;

	public RectState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	public void mouseDown(int x, int y) {
		myrectangle = new MyRectangle(x, y, 0, 0, Color.white, Color.blue, isShadowed, isDashed, lineWidth,
				strokePattern, 1);
		stateManager.addDrawing(myrectangle);
		first_x = x;
		first_y = y;
		myrectangle.setRegion();
	}

	public void mouseUp(int x, int y) {
		if(myrectangle == null)
			return;
		int w = myrectangle.getW();
		int h = myrectangle.getH();
		if (Math.abs(w) < 10 || Math.abs(h) < 10) {
			stateManager.mediator.removeDrawing(myrectangle);
		}
		myrectangle.setRegion();

	}

	public void mouseDrag(int x2, int y2) {
		if (myrectangle.getX() < x2) { //右ドラッグ
			if (myrectangle.getY() < y2) { //下ドラッグ
				myrectangle.setSize(x2 - first_x, y2 - first_y);
				myrectangle.setLocation(first_x, first_y);
			} else {
				myrectangle.setSize(x2 - first_x, first_y - y2);
				myrectangle.setLocation(first_x, y2);
			}
		} else { //左ドラッグ
			if (myrectangle.getY() < y2) { //下ドラッグ
				myrectangle.setSize(first_x - x2, y2 - first_y);
				myrectangle.setLocation(x2, first_y);
			} else {
				myrectangle.setSize(first_x - x2, first_y - y2);
				myrectangle.setLocation(x2, y2);
			}
		}

	}

	public void setisShadowed(boolean isShadowed) {
		this.isShadowed = isShadowed;
	}

	public void setisDashed(boolean isDashed) {
		this.isDashed = isDashed;
	}

	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}

	public void setStrokePattern(float[] strokePattern) {
		this.strokePattern = strokePattern;
	}

}

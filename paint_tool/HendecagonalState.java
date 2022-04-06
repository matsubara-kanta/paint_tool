import java.awt.Color;

public class HendecagonalState implements State {
	StateManager stateManager;
	private boolean isShadowed = false;
	private boolean isDashed = false;
	private int lineWidth = 1;
	private float strokePattern[] = { 10, 15, 0, 0 };
	MyHendecagonal myhendecagonal;
	int first_x;
	int first_y;

	public HendecagonalState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	public void mouseDown(int x, int y) {
		myhendecagonal = new MyHendecagonal(x, y, 0, 0, Color.cyan, Color.black, isShadowed, isDashed, lineWidth,
				strokePattern, 3);
		stateManager.addDrawing(myhendecagonal);
		first_x = x;
		first_y = y;
		myhendecagonal.setRegion();
	}

	public void mouseUp(int x, int y) {
		if(myhendecagonal == null)
			return;
		int w = myhendecagonal.w;
		int h = myhendecagonal.h;
		System.out.println("w:" + w);
		System.out.println("h:" + h);
		if (Math.abs(w) < 10 && Math.abs(h) < 10) {
			stateManager.mediator.removeDrawing(myhendecagonal);
		}

		myhendecagonal.setRegion();
		//System.out.println("first_x:" + first_x + "   first_y:" + first_y);
	}

	public void mouseDrag(int x2, int y2) {
		if (myhendecagonal.getX() < x2) { //右ドラッグ
			if (myhendecagonal.getY() < y2) { //下ドラッグ
				myhendecagonal.setSize(x2 - first_x, y2 - first_y);
			} else {
				myhendecagonal.setSize(x2 - first_x, first_y - y2);
			}
		} else { //左ドラッグ
			if (myhendecagonal.getY() < y2) { //下ドラッグ
				myhendecagonal.setSize(first_x - x2, y2 - first_y);
			} else {
				myhendecagonal.setSize(first_x - x2, first_y - y2);
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

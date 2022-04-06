import java.awt.Color;

public class HexagonState implements State {
	StateManager stateManager;
	private boolean isDashed = false;
	private boolean isShadowed = false;
	private int lineWidth = 1;
	private float strokePattern[] = { 10, 15, 0, 0 };
	MyHexagon myhexagon;
	int first_x;
	int first_y;

	public HexagonState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	public void mouseDown(int x, int y) {
		myhexagon = new MyHexagon(x, y, 0, 0, Color.white, Color.black, isShadowed, isDashed, lineWidth, strokePattern,
				4);
		stateManager.addDrawing(myhexagon);
		first_x = x;
		first_y = y;
		myhexagon.setRegion();
	}

	public void mouseUp(int x, int y) {
		if(myhexagon == null)
			return;
		int w = myhexagon.getW();
		int h = myhexagon.getH();
		if (Math.abs(w) < 10 && Math.abs(h) < 10) {
			stateManager.mediator.removeDrawing(myhexagon);
		}


		myhexagon.setRegion();
		System.out.println("x:" + x + "   y:" + y + "   w:" + w + "   h:" + h);
	}

	public void mouseDrag(int x2, int y2) {
		if (myhexagon.getX() < x2) { //右ドラッグ
			if (myhexagon.getY() < y2) { //下ドラッグ
				myhexagon.setSize(x2 - first_x, y2 - first_y);
				System.out.println("右下");
			} else {
				myhexagon.setSize(x2 - first_x, first_y - y2);
				System.out.println("右上");

			}
		} else { //左ドラッグ
			if (myhexagon.getY() < y2) { //下ドラッグ
				myhexagon.setSize(first_x - x2, y2 - first_y);
				System.out.println("左下");
				System.out.println("x2:" + x2 + "   y2:" + y2);
				System.out.println("first_x:" + first_x + "   first_y:" + first_y);
				System.out.println("x:" + myhexagon.x + "   y:" + myhexagon.y + "   w:" + myhexagon.w
						+ "     h:" + myhexagon.h);
			} else {
				myhexagon.setSize(first_x - x2, first_y - y2);
				System.out.println("左上");
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

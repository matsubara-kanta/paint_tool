import java.awt.Color;

public class OvalState implements State {
	StateManager stateManager;
	private boolean isShadowed = false;
	private boolean isDashed = false;
	private int lineWidth = 1;
	private float strokePattern[] = { 10, 15, 0, 0 };
	MyOval myoval;
	MyOval shadow_myoval;
	int first_x;
	int first_y;

	public OvalState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	public void mouseDown(int x, int y) {
		if (isShadowed) {
			shadow_myoval = new MyOval(x + 10, y + 10, 0, 0, Color.black, Color.black, isShadowed, isDashed, 1,
					strokePattern,2);
			stateManager.addDrawing(shadow_myoval);
		}
		myoval = new MyOval(x, y, 0, 0, Color.white, Color.cyan, isShadowed, isDashed, lineWidth, strokePattern, 2);
		stateManager.addDrawing(myoval);
		first_x = x;
		first_y = y;
		myoval.setRegion();
	}

	public void mouseUp(int x, int y) {
		if(myoval == null)
			return;
		int w = myoval.getW();
		int h = myoval.getH();
		if (Math.abs(w) < 10 || Math.abs(h) < 10) {
			stateManager.mediator.removeDrawing(myoval);
			if (isShadowed) {
				stateManager.mediator.removeDrawing(shadow_myoval);
			}
			return;
		}
		myoval.setRegion();
	}

	public void mouseDrag(int x2, int y2) {
		if (myoval.getX() < x2) { //右ドラッグ
			if (myoval.getY() < y2) { //下ドラッグ
				myoval.setSize(x2 - first_x, y2 - first_y);
				myoval.setLocation(first_x, first_y);
				System.out.println("右下");
			} else {
				myoval.setSize(x2 - first_x, first_y - y2);
				myoval.setLocation(first_x, y2);
				System.out.println("右上");
			}
		} else { //左ドラッグ
			if (myoval.getY() < y2) { //下ドラッグ
				myoval.setSize(first_x - x2, y2 - first_y);
				myoval.setLocation(x2, first_y);
				System.out.println("左下");
			} else {
				myoval.setSize(first_x - x2, first_y - y2);
				myoval.setLocation(x2, y2);
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

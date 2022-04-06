import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;

public class SpoitState implements State {
	StateManager stateManager;
	Robot robot;
	public SpoitState(StateManager stateManager) {
		this.stateManager = stateManager;
			try {
				robot = new Robot();
			} catch (AWTException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}


	}

	public void mouseDown(int x, int y) {
		PointerInfo pi = MouseInfo.getPointerInfo();
		  int sx = (int)pi.getLocation().getX();
		  int sy = (int)pi.getLocation().getY();
	Color c = robot.getPixelColor(sx, sy);
	stateManager.mediator.setColor(c);
	}

	public void mouseUp(int x, int y) {
stateManager.currentState = new SelectState(stateManager);
	}

	public void mouseDrag(int x2, int y2) {

	}

	public void setisShadowed(boolean isShadowed) {

	}

	public void setisDashed(boolean isDashed) {

	}

	public void setLineWidth(int lineWidth) {

	}

	public void setStrokePattern(float[] strokePattern) {

	}

}

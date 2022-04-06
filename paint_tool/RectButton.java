import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RectButton extends JButton implements State {
	StateManager stateManager;
	RectState rectState;

	public RectButton(StateManager stateManager) {
		super("Rectangle");

		addActionListener(new RectListener());

		this.stateManager = stateManager;
	}

	class RectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new RectState(stateManager));
		}
	}

	public void mouseDown(int x1, int x2) {
		stateManager.setState(rectState);
	}

	public void mouseUp(int x1, int x2) {

	}

	public void mouseDrag(int x1, int x2) {

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

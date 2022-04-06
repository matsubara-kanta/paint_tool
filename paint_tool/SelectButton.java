import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SelectButton extends JButton implements State {
	StateManager stateManager;
	SelectState selectState;

	public SelectButton(StateManager stateManager) {
		super("Select");

		addActionListener(new SelectListener());

		this.stateManager = stateManager;
	}

	class SelectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new SelectState(stateManager));
		}
	}

	public void mouseDown(int x1, int x2) {
		stateManager.setState(selectState);
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OvalButton extends JButton implements State {
	StateManager stateManager;
	OvalState ovalState;

	public OvalButton(StateManager stateManager) {
		super("Oval");

		addActionListener(new OvalListener());

		this.stateManager = stateManager;
	}

	class OvalListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new OvalState(stateManager));
		}
	}

	public void mouseDown(int x1, int x2) {
		stateManager.setState(ovalState);
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

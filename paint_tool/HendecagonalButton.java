import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class HendecagonalButton extends JButton implements State {
	StateManager stateManager;
	HendecagonalState hendecagonalState;

	public HendecagonalButton(StateManager stateManager) {
		super("Hendecagonal");

		addActionListener(new HendecagonalListener());

		this.stateManager = stateManager;
	}

	class HendecagonalListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new HendecagonalState(stateManager));
		}
	}

	public void mouseDown(int x, int y) {
		stateManager.setState(hendecagonalState);
	}

	public void mouseUp(int x1, int x2) {

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

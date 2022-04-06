import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class HexagonButton extends JButton implements State {
	StateManager stateManager;
	HexagonState hexagonState;

	public HexagonButton(StateManager stateManager) {
		super("Hexagon");

		addActionListener(new HexagonListener());

		this.stateManager = stateManager;
	}

	class HexagonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new HexagonState(stateManager));
		}
	}

	public void mouseDown(int x, int y) {
		stateManager.setState(hexagonState);
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

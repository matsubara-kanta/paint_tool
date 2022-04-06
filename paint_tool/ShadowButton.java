import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ShadowButton extends JButton implements State {
	StateManager stateManager;

	ShadowButton(StateManager stateManager) {
		super("Shadow");

		addActionListener(new ShadowListener());

		this.stateManager = stateManager;
	}

	class ShadowListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (stateManager.mediator.selectedDrawings == null)
				return;

			for (int i = 0; i < stateManager.mediator.selectedDrawings.size(); i++) {
				if (stateManager.mediator.selectedDrawings.get(i).isShadowed == false) {
					System.out.println("影をつけます");
					stateManager.mediator.setisShadowed(true,i);
					System.out.println(stateManager.mediator.selectedDrawings.get(i));
					System.out.println(stateManager.mediator.selectedDrawings.get(i).isShadowed);
				} else {
					stateManager.mediator.setisShadowed(false,i);
					System.out.println(stateManager.mediator.selectedDrawings.get(i));
					System.out.println(stateManager.mediator.selectedDrawings.get(i).isShadowed);
					System.out.println("影をつけません");
				}
			}
		}
	}

	public void mouseDown(int x1, int x2) {

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

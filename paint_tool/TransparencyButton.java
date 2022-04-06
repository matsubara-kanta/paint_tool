import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TransparencyButton extends JButton implements State {
	StateManager stateManager;
	int count = 0;

	TransparencyButton(StateManager stateManager) {
		super("TransParent");

		addActionListener(new TransparentListener());

		this.stateManager = stateManager;
	}

	class TransparentListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (stateManager.mediator.selectedDrawings == null)
				return;

			for (int i = 0; i < stateManager.mediator.selectedDrawings.size(); i++) {
				if (stateManager.mediator.selectedDrawings.get(i).isTransparency == false) {
					System.out.println("透明度をつけます");
					stateManager.mediator.setisTransparency(true, i);
				} else {
					stateManager.mediator.setisTransparency(false, i);
					System.out.println("透明度をつけません");
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

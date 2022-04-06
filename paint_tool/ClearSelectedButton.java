import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ClearSelectedButton extends JButton implements State {
	StateManager stateManager;

	public ClearSelectedButton(StateManager stateManager) {
		super("Clear_Selected");

		addActionListener(new ClearSelectedListener());

		this.stateManager = stateManager;
	}

	class ClearSelectedListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(stateManager.mediator.selectedDrawings != null) {
				System.out.println("選択を解除しました");
				stateManager.mediator.selectedDrawings.clear();
				for (int i = 0; i < stateManager.mediator.drawings.size(); i++) {
					stateManager.mediator.drawings.get(i).isSelected = false;
				}
				stateManager.mediator.repaint();
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

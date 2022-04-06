import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class ChangeColorButton extends JButton {
	Color color;
	JComboBox<String> comboBox;
	Mediator med;

	ChangeColorButton(JComboBox<String> comboBox) {
		super("Change Color");
		this.comboBox = comboBox;
		addActionListener(new ColorButtonActionListener());

	}

	class ColorButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			comboBox.setVisible(true);
		}
	}

	public void mouseDown(int x1, int x2) {

	}

	public void mouseUp(int x1, int x2) {

	}

	public void mouseDrag(int x1, int x2) {
	}
}

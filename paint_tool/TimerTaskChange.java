import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class TimerTaskChange {
	Mediator med;
	int r, g, b;
	int R, G, B;
	Color color;
	boolean flag = false;
	Vector<MyDrawing> selectedDrawings = null;

	public TimerTaskChange(Mediator med) {
		r = 0;
		g = 0;
		B = 0;
		this.med = med;
		TimeStar star = new TimeStar(); //タイマータスクインスタンス作成
		Timer timer = new Timer(); //タイマーインスタンス作成
		selectedDrawings = (Vector<MyDrawing>) med.selectedDrawings.clone();
		if (selectedDrawings.isEmpty()) {
			return;
		} else {
			for(int i = 0; i < med.selectedDrawings.size(); i++) {
				selectedDrawings.get(i).isSelected = false;
			}
			med.selectedDrawings.clear();
			timer.schedule(star, 0, 50);
		}
	}

	class TimeStar extends TimerTask {
		public void run() {
			if (B < 255) {
				B = b;
				color = new Color(255, 255, 255 - B);
				b++;
				for (int i = 0; i < selectedDrawings.size(); i++) {
					selectedDrawings.get(i).setfillColor(color);
					selectedDrawings.get(i).move(b*2,
							0);
				}
			} else if (G < 255) {
				G = g;
				color = new Color(255, 255 - G, 255 - B);
				g++;
				for (int i = 0; i < selectedDrawings.size(); i++) {
					selectedDrawings.get(i).setfillColor(color);
					selectedDrawings.get(i).move((b*2 + g*2) ,
							0);
				}
			} else {
				R = r;
				color = new Color(255 - R, 255 - G, 255 - B);
				r++;
				for (int i = 0; i < selectedDrawings.size(); i++) {
					selectedDrawings.get(i).setfillColor(color);
					selectedDrawings.get(i).move((b*2 + g*2 + r),
							0);
				}
			}
			med.repaint();
			if (flag) {
				if (R > 253 && G == 255 && B == 255) {
					for (int i = 0; i < selectedDrawings.size(); i++) {
						med.removeDrawing(selectedDrawings.get(i));
					}
					selectedDrawings.clear();
					med.repaint();

					cancel();
				}
			}
			if (!flag)
				flag = true;
		}
	}
}

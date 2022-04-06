import java.awt.Color;
import java.util.Enumeration;
import java.util.Vector;

public class Mediator {

	Vector<MyDrawing> drawings;
	MyCanvas canvas;
	Vector<MyDrawing> selectedDrawings = null;
	Vector<MyDrawing> buffer = null;
	Vector<Integer> f;
	Vector<Integer> cx2;
	Vector<Integer> cy2;
	MyRectangle myrectangle;
	public float strokePattern[] = { 10, 15, 0, 0 };
	boolean mouse_flag = false; //右クリックかどうか、pasteの時にmouseupが反応してしまうのを防ぐ

	public Mediator(MyCanvas canvas) {
		this.canvas = canvas;
		drawings = new Vector<MyDrawing>();
		selectedDrawings = new Vector<MyDrawing>();
		buffer = new Vector<MyDrawing>();
		f = new Vector<Integer>();
		cx2 = new Vector<Integer>();
		cy2 = new Vector<Integer>();
	}

	public Enumeration<MyDrawing> drawingsElements() {
		return drawings.elements();
	}

	public void addDrawing(MyDrawing d) {
		drawings.add(d);
	}

	public void addDrawing_front(MyDrawing d) {
		drawings.add(0, d);
	}

	public void removeDrawing(MyDrawing d) {
		drawings.remove(d);
	}

	public void clearBuffer() {
		buffer.clear();
	}

	public void copy() {
		clearBuffer();
		for (int i = 0; i < selectedDrawings.size(); i++) {
			buffer.add(selectedDrawings.get(i).clone());
		}

	}

	public void cut() {
		clearBuffer();
		for (int i = 0; i < selectedDrawings.size(); i++) {
			buffer.add(selectedDrawings.get(i).clone());
			removeDrawing(selectedDrawings.get(i));
		}
		selectedDrawings.clear();
		System.out.println("cutしました");
		repaint();
	}

	public void paste(int x, int y) {
		if (buffer.isEmpty())
			return;

		for (int i = 0; i < buffer.size(); i++) {
			MyDrawing clone = (MyDrawing) buffer.get(i).clone();
			clone.isSelected = false;
			if (clone.number == 3 || clone.number == 4) {
				clone.setLocation(x, y);
			} else {
				clone.setLocation(x - clone.w / 2, y - clone.h / 2);
			}
			addDrawing(clone);
			clone.setRegion();
		}

		repaint();
		mouse_flag = true;
	}

	public void move(int dx, int dy, int i) {
		selectedDrawings.get(i).move(dx, dy);
	}

	public void repaint() {
		canvas.repaint();
	}

	public void setSelected(int x, int y) {
		for (int i = 0; i < drawings.size(); i++) {
			drawings.get(i).isSelected = false;
		}
		int index = 0;
		for (int i = 0; i < drawings.size(); i++) {
			if (drawings.get(i).contains(x, y)) {
				index = i;
			}
		}
		setSelectedDrawing(drawings.get(index));
		selectedDrawings.get(0).isSelected = true;
		System.out.println();

	}

	public void setSelected_multiple(int x, int y) {
		if (myrectangle == null) {
			return;
		}

		for (int i = 1; i < drawings.size(); i++) { //選択用の矩形を対象から外すため i = 1
			if (drawings.get(i).number == 1) {
				if (myrectangle.region.intersects(drawings.get(i).x, drawings.get(i).y, drawings.get(i).w,
						drawings.get(i).h)) {
					setSelectedDrawing(drawings.get(i));
				}
			} else if (drawings.get(i).number == 2) {
				if (myrectangle.region.intersects(drawings.get(i).x, drawings.get(i).y, drawings.get(i).w,
						drawings.get(i).h)) {
					setSelectedDrawing(drawings.get(i));
				}
			} else {
				if (myrectangle.region.intersects(drawings.get(i).x - drawings.get(i).w / 2,
						drawings.get(i).y - drawings.get(i).h / 2, drawings.get(i).w, drawings.get(i).h)) {
					setSelectedDrawing(drawings.get(i));
				}
			}
		}
		for (int i = 0; i < selectedDrawings.size(); i++) {
			selectedDrawings.get(i).isSelected = true;
		}
		System.out.println();
	}

	public void setSelectedDrawing(MyDrawing d) {
		selectedDrawings.add(d);
	}

	public Vector<MyDrawing> getSelectedDrawing() {
		return selectedDrawings;
	}

	public void setColor(Color c) {
		f.clear();
		for (int i = 0; i < selectedDrawings.size(); i++) {
			if (selectedDrawings.get(i).isTransparency == true) {
				f.add(1);
			} else {
				f.add(0);
			}
			selectedDrawings.get(i).isTransparency = false;
			selectedDrawings.get(i).setfillColor(c);

		}
		repaint();
		for (int i = 0; i < f.size(); i++) {
			if (f.elementAt(i) == 1) {
				selectedDrawings.get(i).isTransparency = true;
			} else {
				selectedDrawings.get(i).isTransparency = false;
			}

		}
	}

	public void setLineColor(Color c) {
		for (int i = 0; i < selectedDrawings.size(); i++) {
			selectedDrawings.get(i).setLineColor(c);
		}
		repaint();
	}

	public void setLineWidth(int linewidth) {
		for (int i = 0; i < selectedDrawings.size(); i++) {
			selectedDrawings.get(i).setLineWidth(linewidth);
		}
		repaint();
	}

	public void setisShadowed(boolean isShadowed, int i) {
		selectedDrawings.get(i).setShadowed(isShadowed);
		repaint();
	}

	public void setisTransparency(boolean isTransparency, int i) {
		selectedDrawings.get(i).setisTransparency(isTransparency);
		repaint();
	}

	public void setAlpha(int alpha) {
		for (int i = 0; i < drawings.size(); i++) {
			drawings.get(i).alpha = alpha;
		}
		repaint();
	}

}

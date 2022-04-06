import java.awt.Color;

public class SelectState implements State {
	StateManager stateManager;
	int firstdown_x;
	int firstdown_y;

	public SelectState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	public void mouseDown(int x, int y) {

		boolean flag = false; //図形がクリックした位置にあるかどうか

		for (int i = 0; i < stateManager.mediator.drawings.size(); i++) {
			if (stateManager.mediator.drawings.get(i).contains(x, y)) {
				flag = true;
				break;
			}
		}

		if (flag) { //キャンバスをクリックした時に図形がある
			stateManager.mediator.selectedDrawings.clear();
			stateManager.mediator.setSelected(x, y);
		} else {
			if (stateManager.mediator.selectedDrawings.size() == 1) {
				for (int i = 0; i < stateManager.mediator.drawings.size(); i++) {
					stateManager.mediator.drawings.get(i).isSelected = false;
				}

				stateManager.mediator.selectedDrawings.clear();
			}
			stateManager.mediator.myrectangle = new MyRectangle(x, y, 0, 0, Color.white, Color.black, false, true, 1,
					stateManager.mediator.strokePattern, 1);
			stateManager.addDrawing_front(stateManager.mediator.myrectangle);
		}
		firstdown_x = x;
		firstdown_y = y;
	}

	public void mouseUp(int x, int y) {

		if(stateManager.mediator.mouse_flag) {
			stateManager.mediator.mouse_flag = false;
			return;
		}

		stateManager.mediator.setSelected_multiple(x, y);
		stateManager.mediator.removeDrawing(stateManager.mediator.myrectangle);
		stateManager.mediator.myrectangle = null;
	}

	public void mouseDrag(int x2, int y2) {
		if (!stateManager.mediator.selectedDrawings.isEmpty()) {
			if (firstdown_x < x2) { //右ドラッグ
				if (firstdown_y < y2) { //下ドラッグ
					for (int i = 0; i < stateManager.mediator.selectedDrawings.size(); i++) {
						stateManager.mediator.move(
								stateManager.mediator.selectedDrawings.get(i).getX() + (x2 - firstdown_x),
								stateManager.mediator.selectedDrawings.get(i).getY() + (y2 - firstdown_y), i);
						stateManager.mediator.selectedDrawings.get(i).setRegion();
						System.out.println();
					}
					//System.out.println("右下");
					firstdown_x = x2;
					firstdown_y = y2;
					System.out.println();
				} else {
					for (int i = 0; i < stateManager.mediator.selectedDrawings.size(); i++) {
						stateManager.mediator.move(
								stateManager.mediator.selectedDrawings.get(i).getX() + (x2 - firstdown_x),
								stateManager.mediator.selectedDrawings.get(i).getY() - (firstdown_y - y2), i);
						stateManager.mediator.selectedDrawings.get(i).setRegion();
					}
					//System.out.println("右上");
					firstdown_x = x2;
					firstdown_y = y2;
					System.out.println();
				}
			} else { //左ドラッグ
				if (firstdown_y < y2) { //下ドラッグ
					for (int i = 0; i < stateManager.mediator.selectedDrawings.size(); i++) {
						stateManager.mediator.move(stateManager.mediator.selectedDrawings.get(i).x - (firstdown_x - x2),
								stateManager.mediator.selectedDrawings.get(i).y + (y2 - firstdown_y), i);
						stateManager.mediator.selectedDrawings.get(i).setRegion();
						//System.out.println(stateManager.mediator.drawings.get(i).isSelected);
					}
					//System.out.println("左下");
					firstdown_x = x2;
					firstdown_y = y2;
					System.out.println();
				} else {
					for (int i = 0; i < stateManager.mediator.selectedDrawings.size(); i++) {
						stateManager.mediator.move(stateManager.mediator.selectedDrawings.get(i).x - (firstdown_x - x2),
								stateManager.mediator.selectedDrawings.get(i).y - (firstdown_y - y2), i);
						stateManager.mediator.selectedDrawings.get(i).setRegion();
						System.out.println();
					}
					//System.out.println("左上");
				}
				firstdown_x = x2;
				firstdown_y = y2;
				System.out.println();
			}
		} else {
			if(stateManager.mediator.myrectangle == null){
			return;
		}

			if (stateManager.mediator.myrectangle.getX() < x2) { //右ドラッグ
				if (stateManager.mediator.myrectangle.getY() < y2) { //下ドラッグ
					stateManager.mediator.myrectangle.setSize(x2 - firstdown_x, y2 - firstdown_y);
					stateManager.mediator.myrectangle.setLocation(firstdown_x, firstdown_y);
					//System.out.println("右下");
				} else {
					stateManager.mediator.myrectangle.setSize(x2 - firstdown_x, firstdown_y - y2);
					stateManager.mediator.myrectangle.setLocation(firstdown_x, y2);
					//System.out.println("右上");
				}
			} else { //左ドラッグ
				if (stateManager.mediator.myrectangle.getY() < y2) { //下ドラッグ
					stateManager.mediator.myrectangle.setSize(firstdown_x - x2, y2 - firstdown_y);
					stateManager.mediator.myrectangle.setLocation(x2, firstdown_y);
					//System.out.println("左下");
				} else {
					stateManager.mediator.myrectangle.setSize(firstdown_x - x2, firstdown_y - y2);
					stateManager.mediator.myrectangle.setLocation(x2, y2);
					//System.out.println("左上");
				}
			}
		}
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

public class StateManager implements State {

	State currentState;
	boolean isDashed = false;
	boolean isShadowed = false;
	float strokePattern[] = {10,15,0,0};
	int lineWidth = 1;
	MyCanvas canvas;
	Mediator mediator;


	public StateManager(MyCanvas canvas) {
		this.canvas = canvas;
		this.currentState = new RectState(this);
		mediator = canvas.getMediator();
	}

	public void setState(State state) {
		this.currentState = state;
		currentState.setisShadowed(isShadowed);
		currentState.setisDashed(isDashed);
		currentState.setLineWidth(lineWidth);
		currentState.setStrokePattern(strokePattern);
	}

	public void addDrawing(MyDrawing d) {
		mediator.addDrawing(d);
	}

	public void addDrawing_front(MyDrawing d) {
		mediator.addDrawing_front(d);
	}

	public void mouseDown(int x1, int x2) {
		if(currentState != null)
		currentState.mouseDown(x1, x2);
	}

	public void mouseUp(int x1, int x2) {
		if(currentState != null)
		currentState.mouseUp(x1, x2);
	}

	public void mouseDrag(int x1, int x2) {
		if(currentState != null)
		currentState.mouseDrag(x1, x2);
	}

	public void setSpoitState() {
		currentState = new SpoitState(this);
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

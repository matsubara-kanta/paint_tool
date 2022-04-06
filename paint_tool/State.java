
public interface State {


	 void mouseDown(int x1,int x2);

	 void mouseUp(int x1,int x2);

	 void mouseDrag(int x1,int x2);

	 void setisDashed(boolean isDashed);

	 void setisShadowed(boolean isShadowed);

	 void setLineWidth(int lineWidth);

	 void setStrokePattern(float strokePattern[]);
}

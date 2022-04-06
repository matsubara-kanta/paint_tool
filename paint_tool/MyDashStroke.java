import java.awt.BasicStroke;

	public class MyDashStroke extends BasicStroke {

		public MyDashStroke(float lineWidth,float[] pattern) {
			super(lineWidth,CAP_BUTT,JOIN_BEVEL,1.0f,pattern,0);
		}
	}

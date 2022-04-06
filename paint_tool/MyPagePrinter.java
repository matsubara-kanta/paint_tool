import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class MyPagePrinter implements Printable {
	Mediator med;

	@Override
	public int print(Graphics g, PageFormat pmt, int pageIndex) throws PrinterException {
		if (pageIndex >= 1) {
			return NO_SUCH_PAGE;
		} else {
			  Paper paper = new Paper();
			  paper.setSize(612.0, 832.0);
			  double margin = 10;
			  paper.setImageableArea(margin, margin, paper.getWidth() - margin, paper.getHeight() - margin);
			  pmt.setPaper(paper);
			pmt.setOrientation(PageFormat.LANDSCAPE);
			for(int i = 0; i < med.drawings.size(); i++) {
				med.drawings.get(i).draw(g);
			}
			return PAGE_EXISTS;
		}

	};

}

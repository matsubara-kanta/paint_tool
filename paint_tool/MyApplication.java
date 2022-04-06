import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyApplication extends JFrame implements ActionListener, ChangeListener {
	StateManager stateManager;
	MyCanvas canvas;
	Mediator med;
	JCheckBox chk;
	private JComboBox<String> comboBox;
	JColorChooser colorchooser;
	private JMenuBar menuBar;
	private JMenu linewidthMenu, fileMenu, alphaMenu, printMenu;
	private JMenuItem normalItem, largeItem, hugeItem, inputItem, outputItem, pasteItem, frontItem, backItem, printItem,
			imageItem, copyItem,cutItem, deleteItem;
	JSlider slider;
	Color color;
	JPopupMenu popup;
	int xpt = 0;
	int ypt = 0;

	public MyApplication() {
		super("My Paint Application");
		canvas = new MyCanvas();
		canvas.setBackground(Color.white);
		stateManager = new StateManager(canvas);
		med = canvas.getMediator();
		comboBox = new JComboBox<>();
		menuBar = new JMenuBar();
		slider = new JSlider();
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(2);
		slider.setPaintTicks(true);
		setJMenuBar(menuBar);
		popup = new JPopupMenu();
		chk = new JCheckBox("Animation");

		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		RectButton rectButton = new RectButton(stateManager);
		jp.add(rectButton);
		OvalButton ovalButton = new OvalButton(stateManager);
		jp.add(ovalButton);
		HendecagonalButton hendecagonalButton = new HendecagonalButton(stateManager);
		jp.add(hendecagonalButton);
		HexagonButton hexagonButton = new HexagonButton(stateManager);
		jp.add(hexagonButton);
		ShadowButton shadowButton = new ShadowButton(stateManager);
		jp.add(shadowButton);
		SelectButton selectButton = new SelectButton(stateManager);
		jp.add(selectButton);
		ChangeColorButton changeColorButton = new ChangeColorButton(comboBox);
		jp.add(changeColorButton);
		ClearSelectedButton clearSelectedButton = new ClearSelectedButton(stateManager);
		jp.add(clearSelectedButton);
		TransparencyButton transparencyButton = new TransparencyButton(stateManager);
		jp.add(transparencyButton);
		jp.add(chk);

		comboBox.addItem("Red");
		comboBox.addItem("Blue");
		comboBox.addItem("Yellow");
		comboBox.addItem("Black");
		comboBox.addItem("White");
		comboBox.addItem("Other FillColors");
		comboBox.addItem("RedLine");
		comboBox.addItem("BlueLine");
		comboBox.addItem("YellowLine");
		comboBox.addItem("BlackLine");
		comboBox.addItem("WhiteLine");
		comboBox.addItem("Other LineColors");
		comboBox.addItem("Spoit");
		comboBox.addActionListener(new ChangeColorActionListener());
		linewidthMenu = new JMenu("LineWidth");
		normalItem = new JMenuItem("1pt");
		largeItem = new JMenuItem("3pt");
		hugeItem = new JMenuItem("5pt");
		fileMenu = new JMenu("File");
		inputItem = new JMenuItem("Input");
		outputItem = new JMenuItem("Output");
		alphaMenu = new JMenu("Transparency");
		cutItem = new JMenuItem("Cut");
		copyItem = new JMenuItem("Copy");
		deleteItem = new JMenuItem("Delete");
		pasteItem = new JMenuItem("Paste");
		frontItem = new JMenuItem("Move_TheFront");
		backItem = new JMenuItem("Move_TheBack");
		printMenu = new JMenu("Print");
		printItem = new JMenuItem("Print");
		imageItem = new JMenuItem("Image");
		popup.add(cutItem);
		popup.add(copyItem);
		popup.add(deleteItem);
		popup.add(pasteItem);
		popup.add(frontItem);
		popup.add(backItem);
		linewidthMenu.add(normalItem);
		linewidthMenu.add(largeItem);
		linewidthMenu.add(hugeItem);
		fileMenu.add(inputItem);
		fileMenu.add(outputItem);
		alphaMenu.add(slider);
		printMenu.add(printItem);
		printMenu.add(imageItem);
		normalItem.addActionListener(new ChangeLineWidthActionListener());
		largeItem.addActionListener(new ChangeLineWidthActionListener());
		hugeItem.addActionListener(new ChangeLineWidthActionListener());
		inputItem.addActionListener(new FileInputstreamActionListener());
		outputItem.addActionListener(new FileOutputstreamActionListener());
		cutItem.addActionListener(new PopupActionListener());
		copyItem.addActionListener(new PopupActionListener());
		deleteItem.addActionListener(new PopupActionListener());
		pasteItem.addActionListener(new PopupActionListener());
		frontItem.addActionListener(new PopupActionListener());
		backItem.addActionListener(new PopupActionListener());
		printItem.addActionListener(new PrintActionListener());
		imageItem.addActionListener(new PrintActionListener());
		chk.addActionListener(new CheckBoxListener());
		slider.addChangeListener(this);
		menuBar.add(linewidthMenu);
		menuBar.add(fileMenu);
		menuBar.add(alphaMenu);
		menuBar.add(printMenu);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jp, BorderLayout.NORTH);
		getContentPane().add(canvas, BorderLayout.CENTER);
		getContentPane().add(comboBox, BorderLayout.SOUTH);
		comboBox.setVisible(false);
		canvas.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				switch (e.getButton()) {
				case MouseEvent.BUTTON1:
					stateManager.mouseDown(e.getX(), e.getY());
					canvas.repaint();
					//System.out.println("左クリック");
					break;
				case MouseEvent.BUTTON2:
					/*
					stateManager.mouseDown(e.getX(), e.getY());
					canvas.repaint();
					System.out.println("ホイールクリック");
					*/
					break;
				case MouseEvent.BUTTON3:
					xpt = e.getX();
					ypt = e.getY();
					showPopup(e);
					canvas.repaint();
					break;
				}
			}
		});

		canvas.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				stateManager.mouseUp(e.getX(), e.getY());
				canvas.repaint();
			}
		});

		canvas.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				stateManager.mouseDrag(e.getX(), e.getY());
				canvas.repaint();
			}

			public void mouseMoved(MouseEvent e) {

			}
		});

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 600);
	}

	class ChangeColorActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (comboBox.getSelectedItem() == "Red") {
				med.setColor(Color.red);
			} else if (comboBox.getSelectedItem() == "Blue") {
				med.setColor(Color.blue);
			} else if (comboBox.getSelectedItem() == "Yellow") {
				med.setColor(Color.yellow);
			} else if (comboBox.getSelectedItem() == "Black") {
				med.setColor(Color.black);
			} else if (comboBox.getSelectedItem() == "White") {
				med.setColor(Color.white);
			} else if (comboBox.getSelectedItem() == "Other FillColors") {
				med.setColor(JColorChooser.showDialog(null, "Choose color", null));
			} else if (comboBox.getSelectedItem() == "RedLine") {
				med.setLineColor(Color.red);
			} else if (comboBox.getSelectedItem() == "BlueLine") {
				med.setLineColor(Color.blue);
			} else if (comboBox.getSelectedItem() == "YellowLine") {
				med.setLineColor(Color.yellow);
			} else if (comboBox.getSelectedItem() == "BlackLine") {
				med.setLineColor(Color.black);
			} else if (comboBox.getSelectedItem() == "WhiteLine") {
				med.setLineColor(Color.white);
			} else if (comboBox.getSelectedItem() == "Other LineColors") {
				med.setLineColor(JColorChooser.showDialog(null, "Choose color", null));
			} else if (comboBox.getSelectedItem() == "Spoit") {
				stateManager.setSpoitState();
			}
			comboBox.setVisible(false);
		}
	}

	class ChangeLineWidthActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if ((JMenuItem) e.getSource() == normalItem) {
				med.setLineWidth(1);
			} else if ((JMenuItem) e.getSource() == largeItem) {
				med.setLineWidth(3);
			} else if ((JMenuItem) e.getSource() == hugeItem) {
				med.setLineWidth(5);
			}
		}
	}

	class FileInputstreamActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Vector<MyDrawing> v = null;
			try {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					FileInputStream fin = new FileInputStream(file);
					ObjectInputStream in = new ObjectInputStream(fin);

					for (int i = 0; i < med.drawings.size(); i++) {
						med.removeDrawing(med.drawings.get(i));
					}

					for (int j = 0; j < med.drawings.size(); j++) {
						med.removeDrawing(med.selectedDrawings.get(j));
					}

					v = (Vector<MyDrawing>) in.readObject();
					System.out.println("v:" + v);
					fin.close();
					for (int i = 0; i < v.size(); i++) {
						v.get(i).isSelected = false;
						med.addDrawing(v.get(i));
					}
					med.repaint();
					System.out.println("ファイルを入力しました");
				} else {
					System.out.println("キャンセルしました");
				}
			} catch (Exception ex) {

			}
		}
	}

	class FileOutputstreamActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Vector<MyDrawing> v = med.drawings;
			try {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					FileOutputStream fout = new FileOutputStream(file);
					ObjectOutputStream out = new ObjectOutputStream(fout);

					out.writeObject(v);
					System.out.println("v:" + v);
					out.flush();

					fout.close();
					System.out.println("ファイルを出力しました");
				} else {
					System.out.println("キャンセルしました");
				}

			} catch (Exception ex) {

			}
		}
	}

	public static void main(String[] args) {
		MyApplication app = new MyApplication();
		app.pack();
		app.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		med.setAlpha(slider.getValue());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	private void showPopup(MouseEvent e) {
		if (e.isPopupTrigger()) {
			popup.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	class PopupActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if ((JMenuItem) e.getSource() == pasteItem) {
				med.paste(xpt, ypt);
			} else if ((JMenuItem) e.getSource() == frontItem) {
				if (med.selectedDrawings.size() == 1) {
					med.removeDrawing(med.selectedDrawings.get(0));
					med.addDrawing(med.selectedDrawings.get(0));
					med.selectedDrawings.clear();
					med.repaint();
				}
			} else if ((JMenuItem) e.getSource() == backItem) {
				med.removeDrawing(med.selectedDrawings.get(0));
				med.addDrawing_front(med.selectedDrawings.get(0));
				med.selectedDrawings.clear();
				med.repaint();
			} else if ((JMenuItem) e.getSource() == cutItem) {
				if (med.selectedDrawings != null) {
					med.cut();
				}
			} else if ((JMenuItem) e.getSource() == deleteItem) {
				for (int i = 0; i < med.selectedDrawings.size(); i++) {
					med.removeDrawing(med.selectedDrawings.get(i));
				}
				med.repaint();
				med.selectedDrawings.clear();
			}else if ((JMenuItem) e.getSource() == copyItem) {
				med.copy();
			}
		}
	}

	class CheckBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (chk.isSelected() == true) {
				TimerTaskChange timertask = new TimerTaskChange(med);

			}

		}
	}

	class PrintActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == printItem) {
				MyPagePrinter mpp = new MyPagePrinter();
				mpp.med = med;
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(mpp);
				boolean print = job.printDialog();
				if (print) {
					try {
						job.print();
					} catch (PrinterException e1) {
						// TODO 自動生成された catch ブロック
						e1.printStackTrace();
					}
				}
			} else if (e.getSource() == imageItem) {

				try {
					BufferedImage img = new BufferedImage(canvas.getWidth(), canvas.getHeight(),
							BufferedImage.TYPE_3BYTE_BGR);
					Graphics g = img.getGraphics();
					g.setColor(Color.WHITE);
					g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight()); //背景をまっしろに
					for (int i = 0; i < med.drawings.size(); i++) {
						med.drawings.get(i).draw(g);
					}
					g.dispose();
					JFileChooser fc = new JFileChooser();
					int returnVal = fc.showSaveDialog(null);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						ImageIO.write(img, "png", file);
					} else {
						System.out.println("キャンセルしました");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}

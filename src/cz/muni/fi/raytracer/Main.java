package cz.muni.fi.raytracer;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8662731190110457051L;
	Thread application;
	JPanelRenderer pane;
	static int width = 800;
	static int height = 600;

	public static void main(final String s[]) {
		final JFrame frame = new JFrame("Render window");
		final Main mainForm = new Main();
		mainForm.init_pane(frame);
		frame.setSize(width + 8, height + 29);
		frame.validate();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void init_pane(final JFrame frame) {
		this.pane = new JPanelRenderer(width, height);
		this.pane.setOpaque(true);
		this.pane.setLayout(null);
		frame.getContentPane().add(this.pane);
	}
}

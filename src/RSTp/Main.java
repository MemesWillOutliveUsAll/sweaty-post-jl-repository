package RSTp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Main extends JPanel implements ActionListener {

	public void mouseClicked(MouseEvent event) {
		bullets = (bullets - 1);

		repaint();
		if (bullets == 0) {
			int delay = 2000; // milliseconds
			ActionListener taskPerformer = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					bullets = 6;
					repaint();
				}
			};
			Timer timer = new Timer(delay, taskPerformer);
			timer.start();
			timer.setRepeats(false);
		}

	}

	public void actionPerformed(ActionEvent event) {

	}

	static Cursor cross_cursor;
	static int bullets = 6;

	public static void main(String[] args) {

		final JFrame frame = new JFrame("RST");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1920, 1080);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(1080, 1920, 0, 0));

		// Custom Cursor
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image imageCrossCursor = toolkit.getImage("Images/20083.png");
		cross_cursor = toolkit.createCustomCursor(imageCrossCursor, new Point(14, 14), "Cross cursor");
		contentPane.setCursor(cross_cursor);
		
		JPanel overlay = new JPanel();
		overlay.add(new DrawImages(bullets));
		overlay.setOpaque(true);
		
		frame.add(overlay);
		frame.add(contentPane);
		


		


		frame.validate();
		frame.repaint();
		
		contentPane.repaint();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.setVisible(true);
			}
		});
	}

}

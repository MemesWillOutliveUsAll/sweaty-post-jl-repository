package RSTp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

public class Main extends JPanel implements MouseListener {

	static Cursor cross_cursor;

	static JPanel glass = new JPanel(new GridLayout(0, 1));
	
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	static DrawImages draw = new DrawImages(); // This line is the problem, it creates the object with bullets
														// and then bullets doesnt change so the number is always 1
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public static void main(String[] args) {
		JPanel container = new JPanel();
		final JFrame frame = new JFrame("RST");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1920, 1080);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(1080, 1920, 0, 0));
		contentPane.setVisible(false);

		// Custom Cursor
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image imageCrossCursor = toolkit.getImage("Images/20083.png");
		cross_cursor = toolkit.createCustomCursor(imageCrossCursor, new Point(14, 14), "Cross cursor");
		contentPane.setCursor(cross_cursor);

		final JPanel overlay = new JPanel();
		overlay.add(draw);
		overlay.setCursor(cross_cursor);
		overlay.setOpaque(false);
		overlay.setBackground(Color.black);
		
		container.add(overlay);
		container.add(contentPane);
	


		frame.add(container);

		overlay.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				//DrawImages.DrawImages(bullets);
				int delay = 2000; //milliseconds
				
				draw.shoot();
				frame.repaint();
				
					ActionListener taskPerformer = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							frame.repaint();
						}
					};
					Timer timer = new Timer(delay, taskPerformer);
					timer.start();
					timer.setRepeats(false);
				

			}
		});

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.setVisible(true);
			}
		});
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}

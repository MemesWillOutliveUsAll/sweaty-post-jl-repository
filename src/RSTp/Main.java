package RSTp;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main extends JPanel implements MouseListener {

	static Cursor cross_cursor;
	static int bullets = 6;

	static int mouseX = 0;
	static int mouseY = 0;

	static DrawImages draw = new DrawImages(bullets);

	public final static JFrame frame = new JFrame("RST");

	public static void main(String[] args) {
		JPanel container = new JPanel();

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

		frame.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				draw.HeadshotWidth = -1000;
				if (bullets == 6) {
					stopMultipleReloads = 0;
				}
				// DrawImages.DrawImages(bullets);
				int delay = 1000; // milliseconds
				if (bullets >= 1) {
					draw.AmmoRemaining = bullets;
					mouseX = e.getX();
					mouseY = e.getY();
					bullets--;
					draw.wasShot();
					frame.repaint();
					try {
						playGunSound();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				draw.shoot();
				frame.repaint();
				if (bullets == 0) {

					ActionListener taskPerformer = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							frame.repaint();
							bullets = 6;
							try {
								reloadGun();
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					};
					frame.repaint();
					Timer timer = new Timer(delay, taskPerformer);
					timer.start();
					timer.setRepeats(false);

					frame.repaint();
				}

			}
		});

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.setVisible(true);
			}
		});
	}

	static int stopMultipleReloads = 0;

	public static void playGunSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		File soundFile = new File("Images//Gunshot.wav");
		Clip clip;
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();

	}

	public static void reloadGun() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		File soundFile = new File("Images//reload.wav");
		Clip clip;
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		if (stopMultipleReloads == 0) {
			clip.start();
		}
		stopMultipleReloads = 1;
		frame.repaint();
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

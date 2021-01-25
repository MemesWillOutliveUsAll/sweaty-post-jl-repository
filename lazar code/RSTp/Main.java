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
import java.io.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Main extends JPanel implements MouseListener {
	private  Clip clip;
	Cursor cross_cursor;
	int bullets = 6;
	int health = 1;
	
	final JFrame frame = new JFrame("RST");
	static int mouseX=0;
	static int mouseY=0;
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	StatusBar statusBar = new StatusBar(bullets);
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		Main newMain = new Main();
		newMain.initialize();
	}
	
	public void initialize()
	{
		
		//DrawImages draw = new DrawImages(bullets);
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
		
		JPanel overlay = new JPanel();
		overlay.add(statusBar);
		overlay.setCursor(cross_cursor);
		overlay.setOpaque(false);

		container.add(overlay);
		container.add(contentPane);

		frame.add(container);
		frame.validate();
		frame.repaint();
		
		
		frame.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (bullets == 6) {
					stopMultipleReloads = 0;
					health++;
					frame.repaint();
				}
				
				// DrawImages.DrawImages(bullets);
				int delay = 1000; // milliseconds
				if (bullets >= 1) {
					if (statusBar.AmmoRemaining == bullets) {
						mouseX = e.getX();
						mouseY = e.getY();
						bullets--;
						statusBar.wasShot(); 
						frame.repaint();
						try {
							playGunSound();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

				statusBar.shoot();
				//statusBar.drainHealth();
				frame.repaint();
				if (bullets <= 0) {

					ActionListener taskPerformer = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							bullets = 6;
							frame.repaint();
							try {
								reloadGun();
								frame.repaint();
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					};
					Timer timer = new Timer(delay, taskPerformer);
					timer.start();
					timer.setRepeats(false);

					//frame.repaint();
				}

			}
		});
		
				//hurtPlayer();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.setVisible(true);
			}
		});
		
					
		statusBar.drainHealth();
				
					
		
		

	}
	
	int stopMultipleReloads = 0;
	
	public void playGunSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		File soundFile = new File("Gunshot.wav");
		Clip clip;
		AudioInputStream  audioStream = AudioSystem.getAudioInputStream(soundFile);
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();
	}
	
	public void reloadGun() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		File soundFile = new File("reload.wav");
		Clip clip;
		AudioInputStream  audioStream = AudioSystem.getAudioInputStream(soundFile);
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
		try {
			reloadGun();
		} catch (UnsupportedAudioFileException | IOException
				| LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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

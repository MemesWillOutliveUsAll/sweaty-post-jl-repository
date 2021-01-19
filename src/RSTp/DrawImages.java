package RSTp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import javax.swing.Timer;
import javax.swing.JPanel;

public class DrawImages extends JPanel implements ActionListener{
	Image BottomBar, Saloon;
	Image[] Ammo = new Image[7];
	Image[] Health = new Image[11];
	Image[] Damage = new Image[11];
	Image[] Gangster = new Image[5];
	Thread runner;
	int AmmoRemaining=1, HealthRemaining=1;
	

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(1920, 1080);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1920, 1080);
	}
	
	
	public void shoot() {
    	if(AmmoRemaining < 7)
    	{
    		AmmoRemaining++;
    	}
    	else if(AmmoRemaining == 6)
    	{
    		int delay = 2000; //milliseconds
			ActionListener taskPerformer = new ActionListener() {
			      public void actionPerformed(ActionEvent evt) {
			    	  AmmoRemaining = 1;

			     }

			 };

			 Timer timer = new Timer(delay, taskPerformer);
			 timer.start();
			 timer.setRepeats(false);

    	}
    	
    }
	

	public DrawImages() {
		super();
		Toolkit kit = Toolkit.getDefaultToolkit();
		BottomBar = kit.getImage("Images/BottomBar.png");
		Saloon = kit.getImage("Images/saloon.jpg");
		for (int i = 0; i < 7; i++) {
			Ammo[i] = kit.getImage("Images/Ammo " + (i + 1) + ".png");
		}
		for (int i = 0; i < 5; i++) {
			Gangster[i] = kit.getImage("Images/Gangster " + (i + 1) + ".png");
		}
		for (int i = 0; i < 11; i++) {
			Health[i] = kit.getImage("Images/Health " + (i + 1) + ".png");
		}
		for (int i = 0; i < 11; i++) {
			Damage[i] = kit.getImage("Images/face " + (i + 1) + ".png");
		}
		
	}

	@Override
	public void paint(Graphics comp) {
		super.paintComponent(comp);
		comp.drawImage(Saloon, 0, 0, this);
		comp.drawImage(BottomBar, 0, 920, this);
		comp.drawImage(Ammo[(AmmoRemaining) - 1], 1750, 908, 140, 140, this);
		comp.drawImage(Health[(HealthRemaining) - 1], 5, 860, 500, 222, this);
		comp.drawImage(Damage[(HealthRemaining) - 1], 900, 860, 500, 222, this);
		comp.drawImage(Gangster[0], 1000, 547, 150, 150, this);
		comp.drawImage(Gangster[1], 750, 530, this);
		comp.drawImage(Gangster[2], 0, 0, this);
		comp.drawImage(Gangster[3], 0, 0, this);
		comp.drawImage(Gangster[4], 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
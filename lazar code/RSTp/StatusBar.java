package RSTp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StatusBar extends JPanel {
	Image BottomBar, Saloon;
	Image[] Ammo = new Image[8];
	Image[] Health = new Image[11];
	Image[] Damage = new Image[11];
	Image[] Gangster = new Image[5];
    Thread runner;
    int AmmoRemaining = 6, HealthRemaining=1;
    
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(1280, 720);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1920, 1080);
    }

    
    public void shoot() {
    	if(AmmoRemaining > 0)
    	{
    		AmmoRemaining--;
    	}
    	else if(AmmoRemaining <= 0)
    	{
    		int delay = 2000; //milliseconds
			ActionListener taskPerformer = new ActionListener() {
			      public void actionPerformed(ActionEvent evt) {
			    	  AmmoRemaining = 6;
			     }
			 };
			 Timer timer = new Timer(delay, taskPerformer);
			 timer.start();
			 timer.setRepeats(false);
    	}
    	
    }
    
    public StatusBar(int AmmoLeft) {
        super();
        AmmoRemaining=AmmoLeft;
        Toolkit kit = Toolkit.getDefaultToolkit();
        BottomBar = kit.getImage("Images/BottomBar.png");
        Saloon = kit.getImage("Images/saloon.jpg");
        for (int i = 0; i < 7; i++) {
            Ammo[i] = kit.getImage("Images/Ammo " + (i) + ".png");
        }
        
        for (int i = 0; i < 5; i++) {
			Gangster[i] = kit.getImage("Images/Gangster " + (i + 1) + ".png");
		}
		for (int i = 0; i < 11; i++) {
			Health[i] = kit.getImage("Images/Health " + (i + 1) + ".png");
		}


    }
    
    @Override
    public void paint(Graphics comp) {
        super.paintComponent(comp);
        comp.drawImage(Saloon, 0, 0, this);
        comp.drawImage(BottomBar, 0, 930, this);
        comp.drawImage(Ammo[(AmmoRemaining)], 1750, 917, 140, 140, this);
        comp.drawImage(Health[(HealthRemaining) - 1], 5, 860, 500, 222, this);
		comp.drawImage(Damage[(HealthRemaining) - 1], 900, 860, 500, 222, this);
		comp.drawImage(Gangster[0], 1000, 547, 150, 150, this);
		comp.drawImage(Gangster[1], 750, 530, this);
		comp.drawImage(Gangster[2], 0, 0, this);
		comp.drawImage(Gangster[3], 0, 0, this);
		comp.drawImage(Gangster[4], 0, 0, this);

    }

}
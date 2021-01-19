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
    Image BottomBar;
    Image[] Ammo = new Image[8];
    Image[] Health = new Image[11];
    Image[] Damage = new Image[11];
    Thread runner;
    int AmmoRemaining = 6;
    
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
    		int delay = 3000; //milliseconds
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
        for (int i = 0; i < 7; i++) {
            Ammo[i] = kit.getImage("Images/Ammo " + (i) + ".png");
        }


    }
    
    @Override
    public void paint(Graphics comp) {
        super.paintComponent(comp);
        comp.drawImage(BottomBar, 0, 930, this);
        comp.drawImage(Ammo[(AmmoRemaining)], 1750, 917, 140, 140, this);

    }

}
package RSTp;

import java.awt.*;
import java.awt.image.*;

import javax.swing.JPanel;

public class DrawImages extends JPanel {
    Image BottomBar;
    Image[] Ammo = new Image[7];
    Image[] Health = new Image[11];
    Image[] Damage = new Image[11];
    Thread runner;
    int AmmoRemaining;
    
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(1280, 720);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1920, 1080);
    }

    
    public DrawImages(int AmmoLeft) {
        super();
        AmmoRemaining=AmmoLeft;
        Toolkit kit = Toolkit.getDefaultToolkit();
        BottomBar = kit.getImage("Images/BottomBar.png");
        for (int i = 0; i < 7; i++) {
            Ammo[i] = kit.getImage("Images/Ammo " + (i + 1) + ".png");
        }


    }
    
    @Override
    public void paint(Graphics comp) {
        super.paintComponent(comp);
        comp.drawImage(BottomBar, 0, 930, this);
        comp.drawImage(Ammo[(AmmoRemaining)-1], 1750, 917, 140, 140, this);

    }

}
package RSTp;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class DrawImages extends JPanel implements ActionListener {

	Image BottomBar, Saloon;
	Image[] Ammo = new Image[7];
	Image[] Health = new Image[11];
	Image[] Gangster = new Image[5];
	Thread runner;
	int AmmoRemaining = 6, HealthRemaining = 1;

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(1920, 1080);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1920, 1080);
	}

	static int mouseX = 0;
	static int mouseY = 0;

	public DrawImages(int AmmoLeft) {
		super();

		AmmoRemaining = AmmoLeft;
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

	public void shoot() {
		mouseX = Main.mouseX;
		mouseY = Main.mouseY;
		if (AmmoRemaining > 0) {
			AmmoRemaining--;

		} else if (AmmoRemaining <= 0) {
			int delay = 2000; // milliseconds
			repaint();
			ActionListener taskPerformer = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					AmmoRemaining = 6;
					String eventName = evt.getActionCommand();

				}

			};

			Timer timer = new Timer(delay, taskPerformer);
			timer.start();
			timer.setRepeats(false);

		}

	}

	// The variable that determines when there will be a map switch
	int TotalGangstersShot = 0;

	int[] GangsterWidth = { 750, 1000, 1200 };
	int[] GangsterHeight = { 530, 547, 547 };
	int[] GangsterImgSize = { 288, 150, 150 };
	int ActiveGangster1 = 0, ActiveGangster2 = 1, ActiveGangster3 = 2;
	int RandomPos1, RandomPos2, RandomPos3;

	int[][] PossiblePos = { { 750, 530, 288 }, { 1000, 547, 150 }, { 1200, 547, 150 }, { 850, 0, 288 },
			{ 1500, 0, 288 }, { 300, 500, 288 }, { 1500, 500, 288 } };

	public void wasShot() {

		for (int i = 0; i < 7; i++) {

			if (mouseX > PossiblePos[i][0]) {
				if (mouseX < PossiblePos[i][0] + PossiblePos[i][2]) {
					if (mouseY > PossiblePos[i][1]) {
						if (mouseY < PossiblePos[i][1] + PossiblePos[i][2]) {

							if (PossiblePos[i][0] == GangsterWidth[0]) {
								GangsterShot(0);
							}
							if (PossiblePos[i][0] == GangsterWidth[1]) {
								GangsterShot(1);
							}
							if (PossiblePos[i][0] == GangsterWidth[2]) {
								GangsterShot(2);
							}
						}
					}
				}
			}
		}

	}

	public void GangsterShot(int whoGotShot) {

		// After Gangster got shot picks a random gangster to respawn
		if (whoGotShot == 0) {

			TotalGangstersShot++;

			// Placing new guy back
			do {
				RandomPos1 = (int) (Math.random() * (6 - 0 + 1));
			} while (ActiveGangster2 == RandomPos1 || ActiveGangster3 == RandomPos1);

			ActiveGangster1 = (RandomPos1);

			GangsterWidth[0] = PossiblePos[(RandomPos1)][0];
			GangsterHeight[0] = PossiblePos[(RandomPos1)][1];
			GangsterImgSize[0] = PossiblePos[(RandomPos1)][2];
			repaint();

		} else if (whoGotShot == 1) {
			TotalGangstersShot++;

			// Placing new guy back

			do {
				RandomPos2 = (int) (Math.random() * (6 - 0 + 1));
			} while (ActiveGangster1 == RandomPos2 || ActiveGangster3 == RandomPos2);

			ActiveGangster2 = (RandomPos2);

			GangsterWidth[1] = PossiblePos[(RandomPos2)][0];
			GangsterHeight[1] = PossiblePos[(RandomPos2)][1];
			GangsterImgSize[1] = PossiblePos[(RandomPos2)][2];
			repaint();

		} else if (whoGotShot == 2) {

			TotalGangstersShot++;

			// Placing new guy back

			do {
				RandomPos3 = (int) (Math.random() * (6 - 0 + 1));
			} while (ActiveGangster1 == RandomPos3 || ActiveGangster2 == RandomPos3);

			ActiveGangster3 = (RandomPos3);

			GangsterWidth[2] = PossiblePos[(RandomPos3)][0];
			GangsterHeight[2] = PossiblePos[(RandomPos3)][1];
			GangsterImgSize[2] = PossiblePos[(RandomPos3)][2];
			repaint();
		}

	}

	@Override
	public void paintComponent(Graphics comp) {
		super.paintComponent(comp);
		comp.drawImage(Saloon, 0, 0, this);
		comp.drawImage(BottomBar, 0, 920, this);
		comp.drawImage(Ammo[AmmoRemaining], 1750, 908, 140, 140, this);
		comp.drawImage(Health[(HealthRemaining) - 1], 5, 860, 500, 222, this);
		comp.drawImage(Gangster[0], GangsterWidth[0], GangsterHeight[0], GangsterImgSize[0], GangsterImgSize[0], this);
		comp.drawImage(Gangster[1], GangsterWidth[1], GangsterHeight[1], GangsterImgSize[1], GangsterImgSize[1], this);
		comp.drawImage(Gangster[2], GangsterWidth[2], GangsterHeight[2], GangsterImgSize[2], GangsterImgSize[2], this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
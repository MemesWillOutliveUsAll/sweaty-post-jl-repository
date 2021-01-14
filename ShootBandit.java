package sweaty_post;

import javax.swing.*;
import java.util.TimerTask; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class ShootBandit extends MouseAdapter  implements MouseListener  
{
	
	int bullets = 6;
	JFrame frame;
	JPanel contentPane;
	JLabel ammoFace;
	
	
	public ShootBandit()
	{
		
		/* Create and set up the frame */
		frame = new JFrame( "ShootBandit" );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/* Create a content pane with a BoxLayout and empty borders */
		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setBackground(Color.red);
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
		/* Create a label that shows a die face */
		ammoFace = new JLabel(new ImageIcon( "Ammo 1.png"));
		ammoFace.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		ammoFace.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		contentPane.add(ammoFace);
		ammoFace.addMouseListener(this);
		contentPane.addMouseListener(this);
		/////////////////////////////
		frame.setContentPane(contentPane);
		/* Size and then display the frame. */
		frame.pack();
		frame.setVisible(true);
	}
	
	public void mouseClicked(MouseEvent event) 
	{
		bullets = (bullets - 1);
		if (bullets == 5)
		{
			ammoFace.setIcon(new ImageIcon("Ammo 2.png"));
		}
		else if (bullets == 4)
		{
			ammoFace.setIcon(new ImageIcon("Ammo 3.png"));
		}
		else if (bullets == 3)
		{
			ammoFace.setIcon(new ImageIcon("Ammo 4.png"));
		}
		else if (bullets == 2)
		{
			ammoFace.setIcon(new ImageIcon("Ammo 5.png"));
		}
		else if (bullets == 1)
		{
			ammoFace.setIcon(new ImageIcon("Ammo 6.png"));
		}
		else if (bullets == 0)
		{
			//Timer timer = new Timer();
			ammoFace.setIcon(new ImageIcon("Ammo 7.png"));
			int delay = 2000; //milliseconds
			 ActionListener taskPerformer = new ActionListener() {
			      public void actionPerformed(ActionEvent evt) {
			          bullets = 6;
			          ammoFace.setIcon(new ImageIcon("Ammo 1.png"));
			          
			      }
			  };
			  Timer timer = new Timer(delay, taskPerformer);
			  timer.start();
			  timer.setRepeats(false);
			
		}
	}

	
	
	
	private static void runGUI()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		ShootBandit greeting = new ShootBandit();
	}
	
	
	public static void main(String[] args)
	{
	/* Methods that create and show a GUI should be
	run from an event-dispatching thread */
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{ 
				runGUI();
			}
		}
		);
	}

}

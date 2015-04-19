package game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Game extends JFrame {

	static String Pics[] = {"beaver.JPG", "dawgs.jfif",
		"ducks.jfif", "forky.jfif",
		"trees.jfif", "utes.jfif"};
	static JButton buttons[];
	ImageIcon closedIcon;
	int numButtons;
	ImageIcon icons[];
	int firstClick, secondClick, clickNumber;
	Timer myTimer;
	int openImages;

	public Game() {
		// Set the title.


		setTitle("Memory Game");

		// Specify an action for the close button.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create a BorderLayout manager.
		getContentPane().setLayout(new GridLayout(2, Pics.length));

		closedIcon = new ImageIcon("p12.jfif");
		numButtons = Pics.length * 2;
		buttons = new JButton[numButtons];
		icons = new ImageIcon[numButtons];
		for (int i = 0, j = 0; i < Pics.length; i++) {
			icons[j] = new ImageIcon(Pics[i]);
			buttons[j] = new JButton("");
			buttons[j].addActionListener(new Game.ImageButtonListener());
			buttons[j].setIcon(closedIcon);
			getContentPane().add(buttons[j++]);

			icons[j] = icons[j - 1];
			buttons[j] = new JButton("");
			buttons[j].addActionListener(new Game.ImageButtonListener());
			buttons[j].setIcon(closedIcon);
			getContentPane().add(buttons[j++]);
		}

		// randomize icons
		Random generator = new Random();
		for(int i=0 ; i<numButtons; i++)
		{
			int j = generator.nextInt(numButtons);
			ImageIcon temp = icons[i];
			icons[i] = icons[j];
			icons[j] = temp;
		}    
		// Pack and display the window.
		pack();
		setVisible(true);
		validate();
		myTimer = new Timer(1000, new TimerListener());
	}

	private class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			myTimer.stop();
			buttons[firstClick].setIcon(closedIcon);
			buttons[secondClick].setIcon(closedIcon);

		}
	}

	private class ImageButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			for (int i = 0; i < numButtons; i++) {
				if (e.getSource() == buttons[i]) {
					buttons[i].setIcon(icons[i]);
				}
			}
		}
	}

	public static void main(String[] args) {
		Game mem = new Game();
		mem.setSize(450,300);
		mem.setVisible(true);
		mem.setLocation(450, 220);
	}
}

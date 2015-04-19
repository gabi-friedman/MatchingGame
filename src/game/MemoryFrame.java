package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class MemoryFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton cards[][] = new JButton[4][6];
	private ImageIcon picsOnCards[][] = new ImageIcon[4][6];
	private ArrayList<ImageIcon> pics = new ArrayList<ImageIcon>();
	private ImageIcon selected;
	private int previ;
	private int prevj;
	private ImageIcon defaultPic = new ImageIcon("qmark.png");
	private int p1score;
	private int p2score;
	Random picNum = new Random();

	public MemoryFrame() {
		setSize(700,600);
		setTitle("Memory");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		
		Container board = getContentPane();
		board.setLayout(new BorderLayout());
		
		final JLabel background = new JLabel(new ImageIcon("./congrats2.png"));
		background.setLayout(new GridLayout(4,6));
		this.setContentPane(background);

		Container score = new Container();
		score.setLayout(new BorderLayout());
		JTextPane p1 = new JTextPane();
		p1.setText("Player 1: " + p1score);
		JTextPane p2 = new JTextPane();
		p2.setText("Player 2: " + p2score);
		score.add(p1, BorderLayout.WEST);
		score.add(p2, BorderLayout.EAST);
		board.add(score,BorderLayout.SOUTH);
		
		Container game = new Container();
		board.add(game, BorderLayout.CENTER);
		
		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				for(int i=0; i<cards.length;i++){
					for(int j=0;j<cards[i].length;j++){
						if(event.getSource().equals(cards[i][j])){
							//regardless turn over the card
							cards[i][j].setIcon(picsOnCards[i][j]);
							//if this is the first selection
							if(selected == null){
								selected = picsOnCards[i][j];
								previ = i;
								prevj = j;
							}
							//if second selection  & = to the 1st
							else if (selected.equals(picsOnCards[i][j])){
								try {
									Thread.sleep(700);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								cards[i][j].setText("MATCH!");
								cards[previ][prevj].setText("MATCH!");
								try {
									Thread.sleep(700);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								cards[i][j].setContentAreaFilled(false);
								cards[i][j].setBorderPainted(false);
								cards[i][j].setText("");
								cards[previ][prevj].setText("");
								cards[previ][prevj].setContentAreaFilled(false);
								cards[previ][prevj].setBorderPainted(false);
								selected = null;
							}
							//if second selection & != to the 1st
							else if (!selected.equals(picsOnCards[i][j])){
								try {
									Thread.sleep(2500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								selected = null;
								cards[i][j].setIcon(defaultPic);
								cards[previ][prevj].setIcon(defaultPic);
							}
						}
					}
				}
			}
		};

		//create buttons
		int counter = 0;
		for(int i=0; i<cards.length;i++){
			for(int j=0;j<cards[i].length;j++){
				counter++;
				cards[i][j] = new JButton();
				cards[i][j].setIcon(defaultPic);
				cards[i][j].addActionListener(listener);
				background.add(cards[i][j]);
			}
		}


	}

	public void makePics(){
		ImageIcon a0  = new ImageIcon("acm.png");
		ImageIcon a1  = new ImageIcon("android.png");
		ImageIcon a2  = new ImageIcon("androidstudio.png");
		ImageIcon a3  = new ImageIcon("apple.jpg");
		ImageIcon a4  = new ImageIcon("chrome.png");
		ImageIcon a5  = new ImageIcon("drjava.png");
		ImageIcon a6  = new ImageIcon("eclipse.jpg");
		ImageIcon a7  = new ImageIcon("java.png");
		ImageIcon a8  = new ImageIcon("linux.png");
		ImageIcon a9  = new ImageIcon("Octocat.jpg");
		ImageIcon a10 = new ImageIcon("stack overflow.png");
		ImageIcon a11 = new ImageIcon("windows.png");
		pics.add(a0); 
		pics.add(a1); 
		pics.add(a2); 
		pics.add(a3); 
		pics.add(a4); 
		pics.add(a5); 
		pics.add(a6);
		pics.add(a7); 
		pics.add(a8); 
		pics.add(a9); 
		pics.add(a10); 
		pics.add(a11);
		pics.add(a0); 
		pics.add(a1); 
		pics.add(a2); 
		pics.add(a3); 
		pics.add(a4); 
		pics.add(a5); 
		pics.add(a6);
		pics.add(a7); 
		pics.add(a8); 
		pics.add(a9); 
		pics.add(a10); 
		pics.add(a11);
	}

	public void placePics(){
		makePics();
		for(int i = 0; i < picsOnCards.length; i ++){
			for(int j = 0; j < picsOnCards[i].length; j++){
				//i think this random generates a # bt 1 and the length of the list
				int next = picNum.nextInt(pics.size());
				picsOnCards[i][j] = pics.get(next);
				pics.remove(next);
			}
		}		
	}

	public static void main(String[] args){
		MemoryFrame frame = new MemoryFrame();
		frame.setVisible(true);
	}

}
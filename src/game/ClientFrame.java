package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class ClientFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton cards[][] = new JButton[4][6];
	private ImageIcon[] images = new ImageIcon[24];
	private ImageIcon[][] bottomImage = new ImageIcon[4][6];
	private Random random = new Random();
	private int p1score = 0;
	private int p2score = 0;
	private ArrayList<String> choices = new ArrayList<String>();
	private int clickNum = 1;
	private int previ;
	private int prevj;
	private JLabel label1;
	private JLabel label2;
	private ImageIcon Q = new ImageIcon("./qmark.png");
	private ImageIcon firstCard;
	private ImageIcon secondCard;
	private String socketMessage = "GO!";
	private BufferedReader BR;
	private PrintStream PS;
	private int tempi;
	private int tempj;
	private PrintWriter pw;

	public ClientFrame() throws IOException {
		setSize(700, 600);
		setTitle("Memory");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container board = getContentPane();
		board.setLayout(new BorderLayout());

		final JLabel background = new JLabel(new ImageIcon("./congrats2.png"));
		board.add(background, BorderLayout.CENTER);
		background.setLayout(new GridLayout(4, 6));
		board.setBackground(Color.WHITE);
		Container players = new Container();
		players.setLayout(new GridLayout(1, 2));
		board.add(players, BorderLayout.SOUTH);
		label1 = new JLabel("Player 1:        " + p1score);
		label2 = new JLabel("Player 2:        " + p2score);
		label1.setBackground(Color.BLACK);
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Buxton Sketch", Font.BOLD, 25));
		label1.setOpaque(true);
		label2.setBackground(Color.BLACK);
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Buxton Sketch", Font.BOLD, 25));
		label2.setOpaque(true);
		players.add(label1);
		players.add(label2);

		Container top = new Container();
		top.setLayout(new BorderLayout());
		JLabel topMessage = new JLabel(socketMessage, JLabel.CENTER);
		top.add(topMessage, BorderLayout.CENTER);
		board.add(top, BorderLayout.NORTH);

		setUpPics();
		//fillPics();
		
		Client client = new Client(socketMessage, p1score, p2score, previ, prevj, tempi, tempj);
		

		/*
		 * try { ServerSocket serverSocket = new ServerSocket(5643); Socket
		 * socket = serverSocket.accept(); InputStreamReader inputStream = new
		 * InputStreamReader(socket.getInputStream()); BR = new
		 * BufferedReader(inputStream); socketMessage = BR.readLine(); if
		 * (socketMessage != null) { PS = new
		 * PrintStream(socket.getOutputStream()); } } catch (Exception e) {
		 * e.printStackTrace(); }
		 */

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				for (int i = 0; i < cards.length; i++) {
					for (int j = 0; j < cards[i].length; j++) {
						if (event.getSource().equals(cards[i][j])) {
							// Action Listener not ready yet!!!!!
							// PS.println("WAIT...");
							if (clickNum == 1) {
								cards[i][j].setIcon(bottomImage[i][j]);
								firstCard = (ImageIcon) cards[i][j].getIcon();
								previ = i;
								prevj = j;
								clickNum = 2;
							}
							if ((clickNum == 2) && (!cards[i][j].equals(cards[previ][prevj]))) {
								tempi = i;
								tempj = j;
								cards[i][j].setIcon(bottomImage[i][j]);
								secondCard = (ImageIcon) cards[i][j].getIcon();
								if (secondCard.getImage().equals(firstCard.getImage())) {
									cards[i][j].setContentAreaFilled(false);
									cards[i][j].setBorderPainted(false);
									cards[i][j].setIcon(new ImageIcon(""));
									cards[i][j].setEnabled(false);
									cards[previ][prevj].setContentAreaFilled(false);
									cards[previ][prevj].setBorderPainted(false);
									cards[previ][prevj].setIcon(new ImageIcon(""));
									cards[previ][prevj].setEnabled(false);
									label1.setText("Player 1:        " + (++p1score));
									clickNum = 1;
									break;
								} else {
									final Timer timer = new Timer(2000, new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent arg0) {
											cards[tempi][tempj].setIcon(Q);
											cards[previ][prevj].setIcon(Q);
										}
									});
									timer.setRepeats(false);
									timer.start();// Only execute once
									clickNum = 1;
									// PS.println("GO!");
								}
							}
							pw.writeObject(backOfCards);
							pw = client.getSocketThread().getOut();
							pw.println(socketMessage);
							pw.println(p1score);
							pw.println(p2score);
							pw.println(previ);
							pw.println(prevj);
							pw.println(tempi);
							pw.println(tempj);
							pw.flush();
						}
					}
				}
				
			}
		};

		// create buttons
		for (int i = 0; i < cards.length; i++) {
			for (int j = 0; j < cards[i].length; j++) {
				cards[i][j] = new JButton(Q);
				cards[i][j].setForeground(Color.GREEN);
				if (i % 2 == 0 && j % 2 == 0 || i % 2 != 0 && j % 2 != 0) {
					cards[i][j].setBackground(new Color(0, 102, 255));
				} else {
					cards[i][j].setBackground(new Color(0, 51, 204));
				}

				cards[i][j].addActionListener(listener);
				background.add(cards[i][j]);
			}
		}

	}

	public void setUpPics() {
		// add to array
		int counter = 1;
		for (int i = 0; i < images.length; i++) {
			images[i] = new ImageIcon("./pic" + (counter) + ".png");
			i++;
			images[i] = new ImageIcon("./pic" + (counter) + ".png");
			counter++;
		}

		String num;
		for (int i = 0; i < cards.length; i++) {
			for (int j = 0; j < cards[i].length; j++) {
				num = i + "" + j;
				choices.add(num);
			}
		}

	}

	public void fillPics() {
		for (int i = 0; i < images.length; i++) {
			int randomI;
			int randomJ;
			String location;
			do {
				randomI = random.nextInt(5);
				randomJ = random.nextInt(7);
				location = String.valueOf(randomI) + String.valueOf(randomJ);
			} while (!choices.contains(location));
			choices.remove(String.valueOf(randomI) + String.valueOf(randomJ));
			bottomImage[Integer.parseInt(String.valueOf(location.charAt(0)))][Integer.parseInt(String.valueOf(location
					.charAt(1)))] = images[i];
		}
	}

	public static void main(String[] args) throws IOException {
		ClientFrame frame = new ClientFrame();
		frame.setVisible(true);
	}

}
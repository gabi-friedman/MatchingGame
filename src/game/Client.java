package game;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

public class Client {

	private String socketMessage;
	private int p1score;
	private int p2score;
	private int previ;
	private int prevj;
	private int tempi;
	private int tempj;
	private SocketThread thread;

	public Client(String bottomImage, String socketMessage, int p1score, int p2score, int previ, int prevj, int tempi, int tempj) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 3232);
		this.socketMessage = socketMessage;
		this.p1score = p1score;
		this.p2score = p2score;
		this.previ = previ;
		this.prevj = prevj;
		this.tempi = tempi;
		this.tempj = tempj;
		
		if (socket != null) {
			thread = new SocketThread(socket, bottomImage, socketMessage, p1score, p2score, previ, prevj, tempi, tempj);
			thread.start();
		}
	}

	public SocketThread getSocketThread() {
		return thread;
	}
}

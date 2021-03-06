package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
public class Server{

	private ServerSocket serverSocket;
	private String bottomImage;
	private String socketMessage;
	private int p1score;
	private int p2score;
	private int previ;
	private int prevj;
	private int tempi;
	private int tempj;
	private SocketThread thread;

	public Server(String bottomImage, String socketMessage, int p1score, int p2score, int previ, int prevj, int tempi, int tempj) throws IOException {
		serverSocket = new ServerSocket(3232);
		this.bottomImage = bottomImage;
		this.socketMessage = socketMessage;
		this.p1score = p1score;
		this.p2score = p2score;
		this.previ = previ;
		this.prevj = prevj;
		this.tempi = tempi;
		this.tempj = tempj;

		Socket socket = serverSocket.accept();
		if (socket != null) {
			thread = new SocketThread(socket, bottomImage, socketMessage, p1score, p2score, previ, prevj, tempi, tempj);
			thread.start();
		}
	}

	
	public Server(ServerFrame serverFrame) throws IOException {
		serverSocket = new ServerSocket(3232);
		Socket socket = serverSocket.accept();
		if (socket != null) {
			thread = new SocketThread(serverFrame);
			thread.start();
		}
	}

	public SocketThread getSocketThread() {
		return thread;
	}
}

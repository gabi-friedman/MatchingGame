package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;

public class SocketThread extends Thread {

	private Socket socket;
//	private BufferedReader reader;
	private InputStream in;
	private String bottomImage;
	private String socketMessage;
	private int p1score;
	private int p2score;
	private int previ;
	private int prevj;
	private int tempi;
	private int tempj;
	private ObjectInputStream reader;

	public SocketThread(){//Socket socket, String bottomImage, String socketMessage, int p1score, int p2score, int previ, int prevj, int tempi, int tempj) {
		this.socket = socket;
		this.bottomImage = bottomImage;
		this.socketMessage = socketMessage;
		this.p1score = p1score;
		this.p2score = p2score;
		this.previ = previ;
		this.prevj = prevj;
		this.tempi = tempi;
		this.tempj = tempj;		
	}

	@Override
	public void run() {

		try {
			
			
			
			in = socket.getInputStream();
			
			reader = new ObjectInputStream(in);
			//out = (ObjectOutputStream) socket.getOutputStream();
			
			Command c = (Command) reader.readObject();
			c.command();
			c.command(cards);
			
			
			
			String inputLine;
			//while ((inputLine = reader.readLine()) != null) {
//				bottomImage = inputLine;
//				socketMessage = reader.readLine(); 
//				p1score = Integer.valueOf(reader.readLine());
//				p2score = Integer.valueOf(reader.readLine());
//				previ = Integer.valueOf(reader.readLine());
//				prevj = Integer.valueOf(reader.readLine());
//				tempi = Integer.valueOf(reader.readLine());
//				tempj = Integer.valueOf(reader.readLine());
				
				
		//	}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public ObjectOutputStream getOut() {
		return out;
	}

}

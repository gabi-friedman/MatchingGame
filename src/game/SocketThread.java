package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;

public class SocketThread extends Thread {

	private Socket socket;
	private BufferedReader reader;
	private PrintWriter out;
	private ImageIcon[][] bottomImage;
	private String socketMessage;
	private int p1score;
	private int p2score;
	private int previ;
	private int prevj;
	private int tempi;
	private int tempj;

	public SocketThread(ImageIcon[][] bottomImage, Socket socket, String socketMessage, int p1score, int p2score, int previ, int prevj, int tempi, int tempj) {
		this.socket = socket;
		
	}

	@Override
	public void run() {

		InputStream in;
		try {
			in = socket.getInputStream();
			
			reader = new ObjectInputStream();
			out = new PrintWriter(socket.getOutputStream(), true);
			
			Command c = ((Object) reader).readObject();
			c.command();
			
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				bottomImage = inputLine;
				socketMessage = reader.readLine(); 
				p1score = Integer.valueOf(reader.readLine());
				p2score = Integer.valueOf(reader.readLine());
				previ = Integer.valueOf(reader.readLine());
				prevj = Integer.valueOf(reader.readLine());
				tempi = Integer.valueOf(reader.readLine());
				tempj = Integer.valueOf(reader.readLine());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public PrintWriter getOut() {
		return out;
	}

}

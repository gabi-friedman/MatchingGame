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
	private ServerFrame frame;
	private ObjectInputStream reader;
	private ObjectOutputStream out;

	public SocketThread(ServerFrame frame){//Socket socket, String bottomImage, String socketMessage, int p1score, int p2score, int previ, int prevj, int tempi, int tempj) {
		this.frame = frame;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {

		try {
			
			
			
			in = socket.getInputStream();
//			
			reader = new ObjectInputStream(in);
		    out = (ObjectOutputStream) socket.getOutputStream();
//			
			Command c = (Command) reader.readObject();
			c.command(frame);
			
			
			
			/*String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				bottomImage = inputLine;
				socketMessage = reader.readLine(); 
				p1score = Integer.valueOf(reader.readLine());
				p2score = Integer.valueOf(reader.readLine());
				previ = Integer.valueOf(reader.readLine());
				prevj = Integer.valueOf(reader.readLine());
				tempi = Integer.valueOf(reader.readLine());
				tempj = Integer.valueOf(reader.readLine());
				*/
				
			//}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ObjectOutputStream getOut() {
		return null;
	}

}

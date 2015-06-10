package game;

import javax.swing.JButton;
import javax.swing.JLabel;

public interface Command {
	
	public void command(ServerFrame frame);
	public void command(ClientFrame frame);

	
	
}

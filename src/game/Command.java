package game;

import javax.swing.JButton;
import javax.swing.JLabel;

public interface Command {
	
	public void command();
	
	public void command(JButton[][] cards);
	
	public void command(JLabel p1, JLabel p2);

	
}

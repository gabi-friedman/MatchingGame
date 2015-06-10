package game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class PassCards implements Command{

	private ImageIcon[][] bottomImages;
	
	public PassCards(ImageIcon[][] bottomImages){
		this.bottomImages = bottomImages;
	}
	
	@Override
	public void command(JButton[][] cards) {
		for(int i = 0; i < bottomImages.length; i++){
			for(int j = 0; j < bottomImages[i].length; j++){
				cards[i][j].setIcon(bottomImages[i][j]);
			}
		}
	}

	@Override
	public void command() {
	}

	@Override
	public void command(JLabel p1, JLabel p2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void command(ServerFrame frame) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void command(ClientFrame frame) {
		// TODO Auto-generated method stub
		
	}

	
}

package game;

import java.util.Random;

import javax.swing.ImageIcon;

public class ServerCardSetup {
	
	private ImageIcon[][] bottomImages ;
	
	public ServerCardSetup( ImageIcon[][] bottomImages ) {
		this.bottomImages = bottomImages;
	}
	
	public void command(ClientFrame frame){
		ImageIcon[][] array = frame.getBottomImages();
		
		for(int i = 0; i < array.length;i++){
			for(int j = 0; j<array[i].length;j++){
				
			}
		}
	}

	

	

}



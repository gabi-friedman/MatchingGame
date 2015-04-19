package game;

import javax.swing.ImageIcon;

public class Picture{

	String name;
	ImageIcon pic;

	public Picture(String name, ImageIcon pic){
		this.name = name;
		this.pic = pic;
	}
	
	public ImageIcon getPic(){
		return this.pic;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getName(ImageIcon pic){
		return this.name;
	}
	
	public boolean equals(Picture p){
		if(p.getName().equals(name)){
			return true;
		}
		return false;
	}
}

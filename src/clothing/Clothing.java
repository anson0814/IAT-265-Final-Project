//abstract class for clothing objects, holds shared methods

package clothing;

import static util.ImageLoader.loadImage;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Clothing implements ClothingInterface{
	protected BufferedImage img;
	protected double xPos,yPos;
	protected double scale;
	
	public Clothing(double x, double y) {
		xPos = x;
		yPos = y;
		scale = 1;
	}
	
	public void setPos(double x, double y) {
		xPos = x;
		yPos = y;
	}
	
	public void decorate(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img,  -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(at);
	}
	
	public boolean clicked(double x, double y){
		boolean clicked = false;
		
		//numbers from lab 10
		if(x > (xPos - ((double) img.getWidth())/2*scale) && 
				x < (xPos + ((double) img.getWidth())/2*scale) && 
				y > (yPos - ((double) img.getHeight())/2*scale) && 
				y < (yPos + ((double) img.getHeight())/2*scale)) 
			clicked = true;
		
		return clicked;
	}
	
	
	
	

}

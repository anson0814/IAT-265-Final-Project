//Base pants class

package clothing;

import static util.ImageLoader.loadImage;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;

public class BasicPant extends Clothing implements ClothingInterface  {
	protected BufferedImage img;
	protected double xPos, yPos;
	protected double scale;
	
	
	public BasicPant(double x, double y) {
		super(x,y);
		img = ImageLoader.loadImage("assets/pants_Blue.png");
		xPos = x;
		yPos = y;
		scale = 0.6;
	}
	
	public void switchColor() {
		img = loadImage("assets/pants_Black.png");
	}
	
	public void switchColor1() {
		img = loadImage("assets/pants_Red.png");
	}
	
	public void switchColor2() {
		img = loadImage("assets/pants_White.png");
	}
	
	public void switchColor3() {
		img = loadImage("assets/pants_Blue.png");
	}
	

	@Override
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

	@Override
	public void setPos(double x, double y) {
		xPos = x;
		yPos = y;
	}
	

}


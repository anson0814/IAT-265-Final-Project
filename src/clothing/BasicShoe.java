//Base shoe class

package clothing;

import static util.ImageLoader.loadImage;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;

public class BasicShoe extends Clothing implements ClothingInterface {
	protected BufferedImage img;
	protected double xPos, yPos;
	protected double scale;
	
	
	public BasicShoe(double x, double y) {
		super(x,y);
		img = ImageLoader.loadImage("assets/shoes_Black.png");
		xPos = x;
		yPos = y;
		scale = 0.6;
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
	public void switchColor() {
		img = loadImage("assets/shoes_Blue.png");
	}
	
	public void switchColor1() {
		img = loadImage("assets/shoes_Red.png");
	}
	
	public void switchColor2() {
		img = loadImage("assets/shoes_White.png");
	}
	
	public void switchColor3() {
		img = loadImage("assets/shoes_Black.png");
	}


	@Override
	public void setPos(double x, double y) {
		xPos = x;
		yPos = y;
	}
	
	

}

